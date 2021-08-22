package uz.pdp.appduonotarypraktikaserver.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class DiscountType  extends AbsNameEntity {

    private double percent;

    private double maxDiscountSum;

    @ManyToOne
    private ZipCode zipCode;



}
