package uz.pdp.appduonotarypraktikaserver.service.team2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.Feedback;
import uz.pdp.appduonotarypraktikaserver.entity.Order;
import uz.pdp.appduonotarypraktikaserver.entity.User;
import uz.pdp.appduonotarypraktikaserver.entity.enums.OrderStatus;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqFeedback;
import uz.pdp.appduonotarypraktikaserver.payload.ReqSendFeedbackAnswer;
import uz.pdp.appduonotarypraktikaserver.repository.*;
import uz.pdp.appduonotarypraktikaserver.resModels.ResFeedback;
import uz.pdp.appduonotarypraktikaserver.resModels.ResOrderDetailsForFeedbackAnswer;
import uz.pdp.appduonotarypraktikaserver.resModels.response.ResFeedbackToFrontend;
import uz.pdp.appduonotarypraktikaserver.service.MailService;
import uz.pdp.appduonotarypraktikaserver.utils.CheckUserRoleUtil;
import uz.pdp.appduonotarypraktikaserver.utils.CommonUtils;

import java.util.UUID;

@Service
public class FeedbackService {
    @Autowired
    FeedbackRepository feedbackRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;


    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    UserZipCodeRepository userZipCodeRepository;

    @Autowired
    MailService mailService;

    @Autowired
    ServicePriceRepository servicePriceRepository;


    //save feedback
    public ApiResponse saveFeedback(ReqFeedback reqFeedback,User user){
        Order order = orderRepository.findById(reqFeedback.getOrderId()).orElseThrow(() -> new ResourceNotFound("Order", "id", reqFeedback.getOrderId()));
        feedbackRepository.save(new Feedback(reqFeedback.getRateAmount(),reqFeedback.getComment(),false,user,order));
        order.setOrderStatus(OrderStatus.CLOSED);
        orderRepository.save(order);
        String adminOfZipCode = userZipCodeRepository.getAdminOfZipCode(order.getServicePrice().getZipCode().getId(), RoleName.ROLE_ADMIN);
        if(reqFeedback.getRateAmount()<=3){
            if(CheckUserRoleUtil.isRoleCustomer(user.getRoles())){
                mailService.sendBadFeedbackToZipCodeAdmin(adminOfZipCode,"customer",user.getFirstName() + " " + user.getLastName(),order.getAgent().getFirstName() + " " + order.getAgent().getLastName() , user.getFirstName() + " " + user.getLastName(),reqFeedback.getRateAmount(),reqFeedback.getComment());
            }
            if(CheckUserRoleUtil.isRoleAgent(user.getRoles())){
                mailService.sendBadFeedbackToZipCodeAdmin(adminOfZipCode,"agent",order.getAgent().getFirstName() + " " + order.getAgent().getLastName(),order.getAgent().getFirstName() + " " + order.getAgent().getLastName() , user.getFirstName() + " " + user.getLastName(),reqFeedback.getRateAmount(),reqFeedback.getComment());
            }
        }
        return new ApiResponse("successfully saved", true);
    }
    //delete
    public ApiResponse deleteFeedback(UUID id){
        feedbackRepository.deleteById(id);
        return new ApiResponse("successfully saved", true);
    }
    //edit
    public ApiResponse editFeedback(ReqFeedback reqFeedback,UUID id){
        Feedback feedback = feedbackRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Feedback", "id", id));
        feedback.setComment(reqFeedback.getComment());
        feedback.setRateAmount(reqFeedback.getRateAmount());
        feedbackRepository.save(feedback);
        return new ApiResponse("successfully saved", true);
    }
    //get
    public ApiResponse getFeedbacksUser(Integer page , Integer size, boolean type){
        //type-true customer else type-false agent
        Pageable pageable = CommonUtils.createPageable(page, size);
        if (type){
            Page<ResFeedback> allFeedbacksOfCustomer = feedbackRepository.getAllFeedbacksOfCustomer(pageable);
            return new ApiResponse("Successfully get",true,allFeedbacksOfCustomer.getContent(),allFeedbacksOfCustomer.getTotalElements());
        }else {
            Page<ResFeedback> allFeedbacksOfAgents = feedbackRepository.getAllFeedbacksOfAgent(pageable);
            return new ApiResponse("Successfully get",true,allFeedbacksOfAgents.getContent(),allFeedbacksOfAgents.getTotalElements());
        }
    }

    public ApiResponse sendFeedbackAnswer(ReqSendFeedbackAnswer reqSendFeedbackAnswer){

        if(reqSendFeedbackAnswer.isCustomer()){
            mailService.sendAnswerOfFeedback(reqSendFeedbackAnswer.getUserEmail(),reqSendFeedbackAnswer.getUserFullName(),reqSendFeedbackAnswer.getUserComment(),"customer",reqSendFeedbackAnswer.getComment());
        }else{
            mailService.sendAnswerOfFeedback(reqSendFeedbackAnswer.getUserEmail(),reqSendFeedbackAnswer.getUserFullName(),reqSendFeedbackAnswer.getUserComment(),"agent",reqSendFeedbackAnswer.getComment());
        }
        Feedback feedback = feedbackRepository.findById(reqSendFeedbackAnswer.getFeedbackId()).orElseThrow(() -> new ResourceNotFound("feedback", "id", reqSendFeedbackAnswer.getFeedbackId()));
        feedback.setAnswered(true);
        feedbackRepository.save(feedback);
        return new ApiResponse("Successfully Sent",true);
    }





}
