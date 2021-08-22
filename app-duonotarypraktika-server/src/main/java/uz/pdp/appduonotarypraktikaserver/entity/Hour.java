package uz.pdp.appduonotarypraktikaserver.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Hour extends AbsEntity {

    private LocalTime fromHour;

    private LocalTime tillHour;

    @ManyToOne(fetch = FetchType.LAZY)
    private AgentSchedule agentSchedule;

    public Hour(LocalTime fromHour, LocalTime tillHour){
        this.fromHour=fromHour;
        this.tillHour=tillHour;
    }
}
