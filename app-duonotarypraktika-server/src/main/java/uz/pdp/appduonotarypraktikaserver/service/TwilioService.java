package uz.pdp.appduonotarypraktikaserver.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.PhoneVerify;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqSms;
import uz.pdp.appduonotarypraktikaserver.repository.PhoneVerifyRepository;

import java.util.Optional;
import java.util.Random;

@Service
public class TwilioService {

    @Autowired
    PhoneVerifyRepository phoneVerifyRepository;

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.trial.phone.number}")
    private String trialPhoneNumber;

    public ApiResponse sendSms(String phoneNumber){
        try {
            String checkedPhoneNumber = (phoneNumber.startsWith("+")?phoneNumber.replace(" ",""):"+"+phoneNumber.replace(" ",""));
            Twilio.init(accountSid,authToken);
            int code = generateCode();
            Message message = Message.creator(new PhoneNumber(checkedPhoneNumber),new PhoneNumber(trialPhoneNumber),"Your verification code: "+code+" BootCamp G7 dan Salom. Twilioni ishlatdik Alisher aka !!! ").create();
            Optional<PhoneVerify> byPhoneNumber = phoneVerifyRepository.findByPhoneNumber(phoneNumber);
            if (byPhoneNumber.isPresent()){
                PhoneVerify phoneVerify = byPhoneNumber.get();
                phoneVerify.setVerifyCode(code);
                phoneVerify.setVerify(false);
                phoneVerifyRepository.save(phoneVerify);
            }else {
                PhoneVerify phoneVerify = new PhoneVerify();
                phoneVerify.setPhoneNumber(phoneNumber);
                phoneVerify.setVerifyCode(code);
                phoneVerify.setVerify(false);
                phoneVerifyRepository.save(phoneVerify);
            }
            return new ApiResponse("success",true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ApiResponse("error",false);
    }

    public int generateCode(){
        return new Random().nextInt(900000)+100000;
    }


    public ApiResponse checkSms(ReqSms reqSms){
        Optional<PhoneVerify> byPhoneNumber = phoneVerifyRepository.findByPhoneNumber(reqSms.getPhoneNumber());
        if (byPhoneNumber.isPresent()){
            PhoneVerify phoneVerify = byPhoneNumber.get();
            if (phoneVerify.getVerifyCode()==reqSms.getVerifyCode()){
                phoneVerify.setVerify(true);
                phoneVerifyRepository.save(phoneVerify);
                return new ApiResponse("success",true);
            }else {
                return new ApiResponse("error",false);
            }
        }else {
            return new ApiResponse("error",false);
        }
    }
}
