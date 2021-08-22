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
public class DocAmountPricing extends AbsEntity {

    private double price;

    private int fromCount;

    private int tillCount;

    private int everyCount;

    @ManyToOne
    private Services service;

    @ManyToOne
    private ZipCode zipCode;

}
