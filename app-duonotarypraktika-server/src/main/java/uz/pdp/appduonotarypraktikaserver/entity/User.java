package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.pdp.appduonotarypraktikaserver.component.ActionHistoryListener;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Users")
@EntityListeners(ActionHistoryListener.class)
public class User extends AbsEntity implements UserDetails {

    private String firstName;
    private String lastName;

    @Column(unique = true,nullable = false)
    private String phoneNumber;


    @Column(unique = true,nullable = false)
    private String email;

    @Column(unique = true)
    private String changedEmail;


    private String emailCode;

    private String address;

    private boolean active=true;

    private boolean online;

    private boolean onlineAgent;

    private double discountSum;

    @ManyToOne(fetch = FetchType.LAZY)
    private User refererUser;

    @OneToOne(fetch = FetchType.LAZY)
    private Attachment attachment;


    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_permission", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id")})
    private Set<Permission> permissions;


    public User(String firstName,String lastName,String phoneNumber,String address, String password, String email,Set<Role> roles,Set<Permission> permissions,boolean onlineAgent){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.password=password;
        this.email=email;
        this.roles=roles;
        this.permissions=permissions;
        this.onlineAgent=onlineAgent;
    }

    public User(String firstName,String lastName,String phoneNumber,String address, String password, String email,Set<Role> roles,Set<Permission> permissions){
        this.firstName=firstName;
        this.lastName=lastName;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.password=password;
        this.email=email;
        this.roles=roles;
        this.permissions=permissions;
    }



    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> grantedAuthorityList = new HashSet<>();
        if (permissions != null)
            grantedAuthorityList.addAll(permissions);
        grantedAuthorityList.addAll(roles);
        return grantedAuthorityList;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }




}
