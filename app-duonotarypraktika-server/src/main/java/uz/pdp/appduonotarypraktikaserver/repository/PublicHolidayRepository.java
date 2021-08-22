package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appduonotarypraktikaserver.entity.PublicHoliday;

import java.util.List;
import java.util.UUID;

public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, UUID> {
    @Query(value = "select * from public_holiday order by created_at asc ",nativeQuery = true)
    List<PublicHoliday> getByCA();
}
