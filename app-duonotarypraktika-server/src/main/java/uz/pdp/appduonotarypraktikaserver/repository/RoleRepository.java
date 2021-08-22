package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.Role;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;
import uz.pdp.appduonotarypraktikaserver.resModels.ResRole;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role,UUID> {
    Set<Role> findAllByNameIn(List<RoleName> name);

    @Query(value = "select id from role where name=:roleName ",nativeQuery = true)
    Integer getIdByRoleName(@Param(value="roleName") String roleName);

    List<Role> findAllByName(RoleName roleName);

    @Query(value = "select  r.id as  id, r.name as roleName from role r join user_role ur ON r.id=ur.role_id where ur.user_id=:userId ",nativeQuery = true)
    List<ResRole>  findRolesByUserId(@Param(value = "userId")UUID userId);
}
