package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsNameEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicHoliday extends AbsNameEntity {

    public Date date;

    @ManyToOne
    public MainService mainService;
}
