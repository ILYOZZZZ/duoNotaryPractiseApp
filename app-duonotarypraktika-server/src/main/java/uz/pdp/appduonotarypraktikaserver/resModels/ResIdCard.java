package uz.pdp.appduonotarypraktikaserver.resModels;

import java.sql.Date;
import java.util.UUID;

public interface ResIdCard{
    UUID getId();
    String getIdCode();
    Date getIssueDate();
    Date getExpireDate();
    boolean getActive();
    UUID getAttachmentId();


}
