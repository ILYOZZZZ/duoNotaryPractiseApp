package uz.pdp.appduonotarypraktikaserver.resModels;

import java.util.UUID;

public interface ResFeedback {

    UUID getId();
    UUID getOrderId();
    String getAgent();
    String getCustomer();
    String getEmail();
    String getPhoneNumber();
    Double getAmount();
    Integer getRating();
    String getComment();
    Boolean getIsAnswered();
}
