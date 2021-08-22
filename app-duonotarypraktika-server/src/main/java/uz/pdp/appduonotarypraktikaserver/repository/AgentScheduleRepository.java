package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.AgentSchedule;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface AgentScheduleRepository extends JpaRepository<AgentSchedule, UUID> {

    @Query(value = "select * from agent_schedule asch " +
            "join users u  on u.id=asch.user_id " +
            "join user_zip_code uzc  on uzc.user_id=asch.user_id " +
            "join user_role ur on ur.user_id=asch.user_id " +
            "join role r on ur.role_id = r.id " +
            "join week_day wd on asch.week_day_id = wd.id " +
            "where uzc.zip_code_id=:zipCode and wd.name=:weekday and asch.day_off=false and r.name = 'ROLE_AGENT' " +
            "and u.enabled=true and u.active=true and u.online_agent=false",nativeQuery = true)
    List<AgentSchedule> findByZipCodeAndWeekday(@Param(value = "zipCode") UUID zipCodeId, @Param(value = "weekday") String weekday);

    @Query(value = "select * from agent_schedule asch " +
            "join users u  on u.id=asch.user_id " +
            "join user_zip_code uzc  on uzc.user_id=asch.user_id " +
            "join user_role ur on ur.user_id=asch.user_id " +
            "join role r on ur.role_id = r.id " +
            "join week_day wd on asch.week_day_id = wd.id " +
            "join hour h on asch.id = h.agent_schedule_id " +
            "where uzc.zip_code_id=:zipCode " +
            "and wd.name=:weekday " +
            "and (h.from_hour<=:from and h.till_hour>=:till) " +
            "and asch.day_off=false " +
            "and r.name = 'ROLE_AGENT' " +
            "and u.enabled=true " +
            "and u.active=true " +
            "and u.online_agent=false",
            nativeQuery = true)
    List<AgentSchedule> findByZipCodeAndWeekdayAndTime(@Param(value = "zipCode") UUID zipCodeId,
                                                       @Param(value = "weekday") String weekday,
                                                       @Param(value = "from")LocalTime from,
                                                       @Param(value = "till")LocalTime till);

}
