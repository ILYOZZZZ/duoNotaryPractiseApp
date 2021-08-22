package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appduonotarypraktikaserver.entity.Services;

import java.util.List;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<Services, UUID> {
    @Query(value = "select * from services order by created_at asc",nativeQuery = true)
    List<Services> getByCA();
}
