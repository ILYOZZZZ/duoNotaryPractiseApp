package uz.pdp.appduonotarypraktikaserver.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Feedback extends AbsEntity {

    private int rateAmount;

    private String comment;

    private boolean isAnswered;

    @ManyToOne
    private User user;

    @ManyToOne
    private Order order;
}
