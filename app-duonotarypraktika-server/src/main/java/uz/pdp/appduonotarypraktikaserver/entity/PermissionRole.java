package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Role role;

    @ManyToOne
    private Permission permission;


    public PermissionRole(Role role, Permission permission) {
        this.role = role;
        this.permission = permission;
    }
}
