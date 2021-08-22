package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.WeekDay;

import java.util.UUID;

public interface WeekDayRepository extends JpaRepository<WeekDay, UUID> {
}
