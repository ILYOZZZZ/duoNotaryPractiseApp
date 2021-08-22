package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Services extends AbsNameEntity {

    private int initialCount;

    private int initialSpendingTime;

    private int everyCount;

    private int everySpendingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private MainService mainService;

    public Services(String name, String description, boolean active, int initialCount, int initialSpendingTime, int everyCount, int everySpendingTime, MainService mainService) {
        super(name, description, active);
        this.initialCount = initialCount;
        this.initialSpendingTime = initialSpendingTime;
        this.everyCount = everyCount;
        this.everySpendingTime = everySpendingTime;
        this.mainService = mainService;
    }


}
