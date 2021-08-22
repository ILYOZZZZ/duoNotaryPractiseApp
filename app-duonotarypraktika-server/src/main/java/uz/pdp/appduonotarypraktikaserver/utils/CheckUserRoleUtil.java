package uz.pdp.appduonotarypraktikaserver.utils;

import uz.pdp.appduonotarypraktikaserver.entity.Role;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;

import java.util.Set;


public class CheckUserRoleUtil {
    public static boolean isRoleAgent(Set<Role> roles){
        for (Role role : roles) {
            if(role.getName().equals(RoleName.ROLE_AGENT)){
                return true;
            }
        }
        return false;
    }


    public static boolean isRoleCustomer(Set<Role> roles){
        for (Role role : roles) {
            if(role.getName().equals(RoleName.ROLE_CUSTOMER)){
                return true;
            }
        }
        return false;
    }

    public static boolean isRoleAdmin(Set<Role> roles){
        for (Role role : roles) {
            if(role.getName().equals(RoleName.ROLE_ADMIN)){
                return true;
            }
        }
        return false;
    }

    public static boolean isRoleSuperAdmin(Set<Role> roles){
        for (Role role : roles) {
            if(role.getName().equals(RoleName.ROLE_SUPER_ADMIN)){
                return true;
            }
        }
        return false;
    }
}
