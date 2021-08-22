package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.TimeDuration;

import java.util.UUID;

public interface TimeDurationRepository extends JpaRepository<TimeDuration, Integer> {
}
