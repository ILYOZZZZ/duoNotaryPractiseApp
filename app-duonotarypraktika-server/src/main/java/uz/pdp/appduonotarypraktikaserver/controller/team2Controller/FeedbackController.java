package uz.pdp.appduonotarypraktikaserver.controller.team2Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.entity.User;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqFeedback;
import uz.pdp.appduonotarypraktikaserver.payload.ReqSendFeedbackAnswer;
import uz.pdp.appduonotarypraktikaserver.security.CurrentUser;
import uz.pdp.appduonotarypraktikaserver.service.team2Service.FeedbackService;

import java.util.UUID;

@RestController
@RequestMapping("api/feedback")
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

//save
//    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping
    public HttpEntity<?> saveFeedback(@RequestBody ReqFeedback reqFeedback , @CurrentUser User user){
        ApiResponse apiResponse = feedbackService.saveFeedback(reqFeedback,user);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
//delete

//    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteFeedback(@PathVariable UUID id){
        ApiResponse apiResponse = feedbackService.deleteFeedback(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
//edit

//    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/edit/{id}")
    public HttpEntity<?> editFeedback(@RequestBody ReqFeedback reqFeedback , @PathVariable UUID id){
        ApiResponse apiResponse = feedbackService.editFeedback(reqFeedback,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping
    public HttpEntity<?> getFeedbackByAgent(@RequestParam Integer page,@RequestParam Integer size,@RequestParam boolean type){
        ApiResponse apiResponse = feedbackService.getFeedbacksUser(page, size, type);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping("/send")
    public HttpEntity<?> sendFeedbackAnswer(@RequestBody ReqSendFeedbackAnswer reqSendFeedbackAnswer){
        ApiResponse apiResponse = feedbackService.sendFeedbackAnswer(reqSendFeedbackAnswer);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
