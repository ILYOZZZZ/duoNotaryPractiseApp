package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appduonotarypraktikaserver.entity.MainService;

import java.util.List;
import java.util.UUID;

public interface MainServiceRepository extends JpaRepository<MainService, UUID> {
    @Query(value = "select * from main_service order by show_number asc ",nativeQuery = true)
    List<MainService> getByShN();
}
