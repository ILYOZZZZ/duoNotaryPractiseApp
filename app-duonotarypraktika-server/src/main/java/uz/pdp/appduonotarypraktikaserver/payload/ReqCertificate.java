package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;
import uz.pdp.appduonotarypraktikaserver.entity.Attachment;
import uz.pdp.appduonotarypraktikaserver.entity.State;
import uz.pdp.appduonotarypraktikaserver.entity.User;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class ReqCertificate {

    private String certificateNumber;

    private Date issueDate;

    private Date expireDate;

    private UUID stateId;

    private UUID attachmentId;


}
