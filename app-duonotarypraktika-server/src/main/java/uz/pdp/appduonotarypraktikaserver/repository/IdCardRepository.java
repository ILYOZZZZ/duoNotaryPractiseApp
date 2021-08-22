package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.IdCard;
import uz.pdp.appduonotarypraktikaserver.resModels.ResIdCard;

import java.util.UUID;

public interface IdCardRepository extends JpaRepository<IdCard, UUID> {


    @Query(value = "select cast(i.id as varchar) as id, i.id_code as idCode, i.issue_date as issueDate, i.expire_date as expireDate, i.active as active, cast(i.attachment_id as varchar) as attachmentId from id_card i where i.user_id=:userId",nativeQuery = true)
    ResIdCard findByUserChopildi(@Param(value = "userId") UUID userId);
}
