package uz.pdp.appduonotarypraktikaserver.resModels;

import uz.pdp.appduonotarypraktikaserver.entity.Attachment;

import java.util.UUID;


public interface ResTestimonials {


    String getFirstName();
    String getLastName();
    String getCompanyInfo();
    String getText();
    UUID getAttachmentId();

}
