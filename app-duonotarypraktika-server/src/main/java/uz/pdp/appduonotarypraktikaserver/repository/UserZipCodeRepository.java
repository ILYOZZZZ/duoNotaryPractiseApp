package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.User;
import uz.pdp.appduonotarypraktikaserver.entity.UserZipCode;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;
import uz.pdp.appduonotarypraktikaserver.resModels.ResAgentsByZipCode;

import java.util.List;
import java.util.UUID;

public interface UserZipCodeRepository extends JpaRepository<UserZipCode, UUID> {
    @Query(value = "select u.email from user_zip_code uzd join users u on uzd.user_id = u.id where uzd.zip_code_id=:zipCodeId and :userRole in (select (select name from role r where r.id=ur.role_id) from user_role ur where ur.user_id = u.id)" , nativeQuery = true)
    String getAdminOfZipCode(@Param(value = "zipCodeId") UUID zipCodeId , @Param(value = "userRole") RoleName name);

    @Query(value = "select cast(uzd.zip_code_id as varchar ) from user_zip_code uzd where uzd.user_id=:adminId",nativeQuery = true)
    UUID getZipCodeOfAdmin(@Param(value = "adminId") UUID adminId);

    @Query(value = "select cast(u.id as varchar) as id ,u.first_name as firstName , u.last_name as lastName , u.address as address , (select count(o.id) from orders o where o.agent_id=u.id ) as orderCount  from user_zip_code join users u on user_zip_code.user_id = u.id join user_role ur on u.id = ur.user_id join role r on ur.role_id = r.id  where r.name='ROLE_AGENT' and user_zip_code.zip_code_id=:zipCodeId" ,nativeQuery = true)
    List<ResAgentsByZipCode> getAgentsOfZipCode(@Param(value = "zipCodeId") UUID id);


    @Modifying
    @Query(value = "delete from user_zip_code where user_id=:agentId",nativeQuery = true)
    void deleteByAgentId(@Param(value = "agentId") UUID agentId);



}
