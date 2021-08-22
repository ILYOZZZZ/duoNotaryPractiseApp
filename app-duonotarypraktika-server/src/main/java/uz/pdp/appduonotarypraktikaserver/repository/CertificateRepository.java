package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.Certificate;
import uz.pdp.appduonotarypraktikaserver.resModels.ResCertificate;

import java.util.List;
import java.util.UUID;

public interface CertificateRepository extends JpaRepository<Certificate, UUID> {


    @Query(value = "select cast(c.id as varchar ) as id, c.issue_date as issueDate, c.expire_date as expireDate, c.active as active, c.certificate_number as certificateNumber, cast(c.attachment_id as varchar) as attachmentId, cast(c.state_id as varchar) as stateId from certificate c where c.user_id=:userId",nativeQuery = true)
    List<ResCertificate> findAllByFozil(@Param(value = "userId") UUID userId);

}
