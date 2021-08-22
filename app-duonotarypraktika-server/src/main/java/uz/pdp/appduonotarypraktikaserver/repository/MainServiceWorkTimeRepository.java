package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.MainServiceWorkTime;
import uz.pdp.appduonotarypraktikaserver.resModels.ResWorkHours;

import java.util.List;
import java.util.UUID;

public interface MainServiceWorkTimeRepository extends JpaRepository<MainServiceWorkTime, UUID> {

    @Query(value = "select ms.online as serviceName,mcwt.from_time as fromTime , mcwt.till_time as tillTime from main_service_work_time mcwt join main_service ms on mcwt.main_service_id = ms.id where mcwt.zip_code_id=:zipCodeId ",nativeQuery = true)
    List<ResWorkHours> getWorkHoursByZipCode(@Param(value = "zipCodeId") UUID uuid);


}
