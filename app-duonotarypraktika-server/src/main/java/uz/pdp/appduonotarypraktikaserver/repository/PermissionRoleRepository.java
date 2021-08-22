package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.Permission;
import uz.pdp.appduonotarypraktikaserver.entity.PermissionRole;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;

import java.util.List;
import java.util.Set;

public interface PermissionRoleRepository extends JpaRepository<PermissionRole,Integer> {
    Set<PermissionRole> findAllByRoleId(@Param("roleId") Integer roleId);
}
