package uz.pdp.appduonotarypraktikaserver.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;
import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AgentSchedule extends AbsEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private WeekDay weekDay;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "agentSchedule")
    private List<Hour> hours;

    private boolean dayOff;

    public AgentSchedule(User user, WeekDay weekDay, boolean dayOff){
        this.user=user;
        this.weekDay=weekDay;
        this.dayOff=dayOff;
    }
}
