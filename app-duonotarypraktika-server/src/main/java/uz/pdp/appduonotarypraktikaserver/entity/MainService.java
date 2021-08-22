package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import java.sql.Time;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class MainService extends AbsNameEntity {

    private boolean online;

    private Time fromTime;

    private Time tillTime;

    private int showNumber;

    public MainService(String name,String description,boolean active){
        this.setActive(active);
        this.setName(name);
        this.setDescription(description);
    }
}
