package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class RealEstateForm extends AbsEntity {

    private String clientName;

    private String phoneNumber;

    private String clientAddress;

    private String clientEmail;

    private String message;

//    private Date date;

//    private String time;

    @OneToOne
    private Order order;

    @ManyToOne
    private User requester;
}
