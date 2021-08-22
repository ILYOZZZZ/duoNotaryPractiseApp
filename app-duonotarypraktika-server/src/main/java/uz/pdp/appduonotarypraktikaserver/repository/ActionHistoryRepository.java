package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.appduonotarypraktikaserver.entity.ActionHistory;
import uz.pdp.appduonotarypraktikaserver.entity.enums.OperationName;

import java.util.UUID;

public interface ActionHistoryRepository extends JpaRepository<ActionHistory, UUID> {
    Page<ActionHistory> findAllByTableName(String tableName, Pageable pageable);
    Page<ActionHistory> findAllByObjectId(UUID objectId, Pageable pageable);
    Page<ActionHistory> findAllByTableNameAndOperationName(String tableName, OperationName operation, Pageable pageable);
    Page<ActionHistory> findAllByObjectIdAndOperationName(UUID objectId, OperationName operation, Pageable pageable);
    Page<ActionHistory> findAllByOperationName(OperationName operation, Pageable pageable);

    @Transactional
    @Modifying
    @Query(value = "update action_history as ld set object_id=cll.objectId from (select id, cast(((cast(object as json)) ->>'id') as uuid) as objectId from action_history where object_id is null)as cll (id,objectId)where cll.id=ld.id", nativeQuery = true)
    public void updateHistoryObjectIsNull();

    @Query(nativeQuery = true,
            value = "select * from action_history where object_id=:objectId order by created_at desc  limit 1")
    ActionHistory findForAdminNotification(@Param("objectId") UUID objectId);
}
