package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.OutOfService;
import uz.pdp.appduonotarypraktikaserver.resModels.ResOutOfService;
import uz.pdp.appduonotarypraktikaserver.resModels.ResZipCode;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

public interface OutOfServiceRepository extends JpaRepository<OutOfService, UUID> {


    @Query(value = "select cast(os.id as varchar ) as id, os.client_email as clientEmail from out_of_service os  ",nativeQuery = true)
   Page<ResOutOfService>  getZipCode(Pageable pageable);



}
