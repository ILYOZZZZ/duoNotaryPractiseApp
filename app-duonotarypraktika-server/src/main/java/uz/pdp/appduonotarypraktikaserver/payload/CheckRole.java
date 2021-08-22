package uz.pdp.appduonotarypraktikaserver.payload;

import uz.pdp.appduonotarypraktikaserver.entity.Role;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;


import javax.print.attribute.standard.NumberUp;
import java.util.Set;

public class CheckRole {

    public RoleName isSuperAdmin(Set<Role> roles) {
        for (Role role : roles) {
            if (role.getName() == RoleName.ROLE_SUPER_ADMIN) {
                return RoleName.ROLE_SUPER_ADMIN;
            }
        }
        return null;

    }

    public RoleName isAdmin(Set<Role> roles) {
        for (Role role : roles) {
            if (role.getName() == RoleName.ROLE_ADMIN) {
                return RoleName.ROLE_ADMIN;
            }
        }
        return null;
    }

    public RoleName isAgent(Set<Role> roles) {
        for (Role role : roles) {
            if (role.getName() == RoleName.ROLE_AGENT) {
                return RoleName.ROLE_AGENT;
            }
        }
        return null;
    }

    public RoleName isCustomer(Set<Role> roles) {
        for (Role role : roles) {
            if (role.getName() == RoleName.ROLE_CUSTOMER) {
                return  RoleName.ROLE_CUSTOMER;
            }
        }
        return null;
    }





    }
