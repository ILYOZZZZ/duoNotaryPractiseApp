package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.appduonotarypraktikaserver.entity.enums.PermissionName;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   @Enumerated(EnumType.STRING)
    private PermissionName permissionName;

    public Permission(PermissionName permissionName) {
        this.permissionName=permissionName;
    }


    @Override
    public String getAuthority() {
        return permissionName.name;
    }
}
