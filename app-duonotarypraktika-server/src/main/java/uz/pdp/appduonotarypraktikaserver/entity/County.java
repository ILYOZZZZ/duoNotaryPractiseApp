package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
@Entity
public class County extends AbsNameEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private State state;

    public County(String name,String description,boolean active,State state){
        this.setActive(active);
        this.setName(name);
        this.setDescription(description);
        this.state=state;
    }
}
