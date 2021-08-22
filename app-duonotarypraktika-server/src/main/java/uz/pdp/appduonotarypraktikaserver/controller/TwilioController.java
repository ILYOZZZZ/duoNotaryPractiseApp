package uz.pdp.appduonotarypraktikaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ReqSms;
import uz.pdp.appduonotarypraktikaserver.service.TwilioService;

@RestController
@RequestMapping("/api/sms")
public class TwilioController {
    @Autowired
    TwilioService twilioService;



    @PostMapping
    public HttpEntity<?> checkSms( @RequestBody ReqSms reqSms){
        if (reqSms.getVerifyCode()==0){
            return ResponseEntity.ok(twilioService.sendSms(reqSms.getPhoneNumber()));
        }else{
            return ResponseEntity.ok(twilioService.checkSms(reqSms));
        }
    }
}
