package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.Role;
import uz.pdp.appduonotarypraktikaserver.entity.User;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;
import uz.pdp.appduonotarypraktikaserver.resModels.ResAgentsByZipCode;
import uz.pdp.appduonotarypraktikaserver.resModels.ResUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByPhoneNumberOrEmail(String phoneNumber, String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);

    @Query(value = "select cast(u.id as varchar) as id , u.first_name as firstName  , u.last_name as lastName, u.phone_number as phoneNumber  , u.email as email , u.address as address , u.active as active, u.online as online , u.online_agent as onlineAgent, u.discount_sum as discountSum ,  cast(u.attachment_id as varchar) as attachmentId, cast(u.referer_user_id as varchar) as refererId  from users u  join user_role ur ON u.id=ur.user_id where ur.role_id in (select id from role where name=:roleName)",nativeQuery = true)
    Page<ResUser> findAllByRoles(@Param(value = "roleName") String roleName, Pageable pageable);

    @Query(value = "select (select name from role as r where r.id = ur.role_id ) from user_role ur where ur.user_id:id",nativeQuery = true)
    String getRoleOfUser(@Param(value = "id") UUID id);


    @Query(value = "select cast(u.id as varchar) as id,u.first_name as firstName, u.last_name as lastName , u.address as address,0 as orderCount  from users u join user_role ur on u.id = ur.user_id join certificate c on u.id = c.user_id where 'ROLE_AGENT' in (select name from role where role.id=ur.role_id) and :zipCodeId NOT IN (select user_zip_code.zip_code_id  from user_zip_code where user_zip_code.user_id=u.id ) and :stateId=c.state_id",nativeQuery = true)
    List<ResAgentsByZipCode> getAllAgentsToLinkZipCode(@Param(value = "stateId")UUID stateId, @Param(value = "zipCodeId")UUID zipCodeId);

}

