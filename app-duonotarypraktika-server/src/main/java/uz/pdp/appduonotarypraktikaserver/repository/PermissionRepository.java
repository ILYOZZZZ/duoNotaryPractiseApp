package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.Permission;
import uz.pdp.appduonotarypraktikaserver.resModels.ResPermission;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission,Integer> {
    @Query(value = "select * from permission where id in(select permission_id from permission_role where role_id=(select id from role where name=:roleName))", nativeQuery = true)
    List<Permission> findAllByRoleName(@Param("roleName") String roleName);

    @Query(value = "select p.id as id , p.permission_name as permissionName from permission p join user_permission up on p.id = up.permission_id and up.user_id=:userId", nativeQuery = true)
    List<ResPermission> getPermissionListByUserId(@Param(value = "userId") UUID userId);

    @Query(value = "select * from permission where id in (:permissionIds)",nativeQuery = true)
    Set<Permission> findAllByIds(@Param(value = "permissionIds") List<Integer> permissionIds);

}