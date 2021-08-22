package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.TimeTable;
import uz.pdp.appduonotarypraktikaserver.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TimeTableRepository extends JpaRepository<TimeTable, UUID> {

    @Query(value = "select * from time_table tt where " +
            "((tt.from_time<=:from and tt.till_time>:from) "+
            "or (tt.from_time<:till and tt.till_time>=:till)"+
            "or (tt.from_time>=:from and tt.till_time<=:till)) " +
            "and tt.agent_id=:agentId", nativeQuery = true)
    Optional<TimeTable> getTimeTableByAgent(@Param(value = "from")LocalDateTime from,
                                            @Param(value = "till")LocalDateTime till,
                                            @Param(value = "agentId") UUID agentId);

    int countAllByAgentAndFromTimeBetween(User agent, LocalDateTime date1, LocalDateTime date2);


}
