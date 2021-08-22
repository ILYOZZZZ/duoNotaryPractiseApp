package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ServicePrice extends AbsEntity {

    private double price;

    private boolean active;

    private int chargeMinute;

    private double chargePercent;

    @ManyToOne(fetch = FetchType.LAZY)
    private ZipCode zipCode;

    @OneToOne
    private Services service;

}
