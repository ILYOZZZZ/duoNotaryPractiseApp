package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.AgentHourOff;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AgentHourOffRepository extends JpaRepository<AgentHourOff, UUID> {

    @Query(value = "select * from agent_hour_off aho  " +
                   "where ((aho.off_from<=:from and aho.off_till>:from)  " +
                   "or (aho.off_from<=:till and aho.off_till>=:till)  " +
                   "or (aho.off_from>=:from and aho.off_till<=:till)) " +
                   "and aho.agent_id=:agentId", nativeQuery = true)
    Optional<AgentHourOff> getAgentOffById(@Param(value = "from")LocalDateTime from,
                                           @Param(value = "till")LocalDateTime till,
                                           @Param(value = "agentId")UUID agentId);
}
