package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.Hour;

import java.time.LocalTime;
import java.util.UUID;

public interface HourRepository extends JpaRepository<Hour, UUID> {

    @Query(value = "select count(*) from hour h ", nativeQuery = true)
 boolean checkTimeByAgentId(@Param(value = "agentId")UUID agentId,@Param(value = "time") LocalTime time);
}
