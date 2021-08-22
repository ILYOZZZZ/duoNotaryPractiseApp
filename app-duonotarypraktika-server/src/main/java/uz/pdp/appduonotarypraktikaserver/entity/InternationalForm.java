package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class InternationalForm extends AbsEntity {

    private boolean isEmbassy;

    private String clientFirstName;

    private String clientLastName;

    private String clientPhoneNumber;

    private String clientEmail;

//    private Date date;

//    private String time;

    private String lan;

    private String lat;

    private int docCount;

    private String message;

    @ManyToOne
    private Country country;

    @ManyToOne
    private ApostilleDocType apostilleDocType;

    @ManyToOne
    private User requester;
}
