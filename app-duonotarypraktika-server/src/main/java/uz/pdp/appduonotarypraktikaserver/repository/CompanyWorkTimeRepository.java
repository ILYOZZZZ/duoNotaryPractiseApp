package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appduonotarypraktikaserver.entity.CompanyWorkTime;

import java.util.List;
import java.util.UUID;

public interface CompanyWorkTimeRepository extends JpaRepository<CompanyWorkTime, UUID> {
    @Query(value = "select * from company_work_time order by created_at asc",nativeQuery = true)
    List<CompanyWorkTime> getByCA();
}