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
public class OrderAdditionalServicePrice extends AbsEntity {

    private double currentPrice;

    private int count;

    @ManyToOne
    private Order order;

    @ManyToOne
    private AdditionalServicePrice additionalServicePrice;
}
