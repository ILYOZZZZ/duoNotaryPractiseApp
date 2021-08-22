package uz.pdp.appduonotarypraktikaserver.resModels;

import java.util.UUID;

public interface ResBlog {
    UUID getId();
    String getTitle();
    String getDescription();
    UUID getAttachmentId();
}
