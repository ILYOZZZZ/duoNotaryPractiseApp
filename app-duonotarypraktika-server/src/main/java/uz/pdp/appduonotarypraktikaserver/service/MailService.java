package uz.pdp.appduonotarypraktikaserver.service;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import uz.pdp.appduonotarypraktikaserver.entity.User;
import uz.pdp.appduonotarypraktikaserver.repository.UserRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    Configuration configuration;
    @Autowired
    UserRepository userRepository;

    public void sendSimpleMail() {
        SimpleMailMessage smm = new SimpleMailMessage();
        smm.setTo("jamshidjon.vahobov@gmail.com",
                "sedrik.greys8@gmail.com",
                "alisherabuhabiba@gmail.com",
                "namazovabbos16@gmail.com",
                "allitamhar10@gmail.com",
                "bobonazarovhasanboy5@gmail.com",
                "davlatyorovfozil2003@gmail.com",
                "asliddinmuhiddin@gmail.com",
                "kozimovziyoxiddin1415@gmail.com");
        smm.setSubject("Testing mail");
        smm.setText("Hello, I am sending this message from spring boot server. CHOPILDI Fozil uchun!");

        javaMailSender.send(smm);
    }

    public String getContentFromTemplate(Map<String,Object> model,String templateName){
        StringBuffer content = new StringBuffer();
        try {
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(configuration.getTemplate(templateName), model));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    public boolean sendChangeActive(UUID userId){
        User user = userRepository.getOne(userId);
        MimeMessage mmm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmmhelper = new MimeMessageHelper(mmm);

        UUID uuid = UUID.randomUUID();
        Map<String,Object> model  = new HashMap<>();
        model.put("firstName",user.getFirstName());
        model.put("lastName",user.getLastName());
        model.put("status",user.isActive()?"activated":"blocked");

        try {
            mmmhelper.setTo(user.getEmail());
            mmmhelper.setText(getContentFromTemplate(model,"changeActiveTemplate.html"),true);
            mmmhelper.setSubject("Account " + (user.isActive()?"activated":"blocked"));
            javaMailSender.send(mmm);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean sendVerificationCode(User user,boolean changing){
        MimeMessage mmm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmmhelper = new MimeMessageHelper(mmm);

        UUID uuid = UUID.randomUUID();
        Map<String,Object> model  = new HashMap<>();
        model.put("firstName",user.getFirstName());
        model.put("lastName",user.getLastName());
        model.put("userId",user.getId());
        model.put("verificationCode",uuid);
        model.put("changing",changing);

        try {
            mmmhelper.setTo(user.getEmail());
            mmmhelper.setText(getContentFromTemplate(model,"verificationTemplate.html"),true);
            javaMailSender.send(mmm);
            user.setEmailCode(uuid.toString());
            userRepository.save(user);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean sendBadFeedbackToZipCodeAdmin(String email ,String role, String ratedUser  ,String agentName , String customerName , Integer rate , String comment ){
        MimeMessage mmm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmmhelper = new MimeMessageHelper(mmm);
        Map<String,Object> model = new HashMap<>();
        model.put("agentName",agentName);
        model.put("customerName",customerName);
        model.put("rate",rate);
        model.put("comment",comment);
        model.put("ratedUser",ratedUser);
        model.put("role",role);
        try {
            mmmhelper.setTo(email);
            mmmhelper.setText(getContentFromTemplate(model,"badFeedbackTemplate.html"),true);
            mmmhelper.setSubject("Order with id" + "" + "is rated under 3");
            javaMailSender.send(mmm);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean sendAnswerOfFeedback(String userEmail,String userFullName , String userComment , String userType  ,String answer  ){
        MimeMessage mmm = javaMailSender.createMimeMessage();
        MimeMessageHelper mmmhelper = new MimeMessageHelper(mmm);
        Map<String,Object> model = new HashMap<>();
        model.put("userFullName",userFullName);
        model.put("userComment",userComment);
        model.put("answer",answer);
        model.put("userType",userType);
        try {
            mmmhelper.setTo(userEmail);
            mmmhelper.setText(getContentFromTemplate(model,"feedbackAnswerTemplate.html"),true);
            mmmhelper.setSubject("Feedback Answer!");
            javaMailSender.send(mmm);
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}