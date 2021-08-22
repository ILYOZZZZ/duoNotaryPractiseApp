package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReqSendFeedbackAnswer {
    private String comment;
    private String userEmail ;
    private String userFullName ;
    private String userComment;
    private UUID feedbackId;
    private boolean isCustomer;

}
