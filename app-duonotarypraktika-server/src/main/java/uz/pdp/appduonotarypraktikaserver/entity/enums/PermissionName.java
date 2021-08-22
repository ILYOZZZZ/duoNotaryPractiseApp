package uz.pdp.appduonotarypraktikaserver.entity.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum PermissionName {

    SAVE_ADMIN("Save new admin", Collections.singletonList(RoleName.ROLE_SUPER_ADMIN)),
    CREATE_DISCOUNT_TYPE("Create new discount type",Arrays.asList(RoleName.ROLE_ADMIN,RoleName.ROLE_SUPER_ADMIN)),

    CHANGE_USER_ACTIVE("Change user active status", Arrays.asList(RoleName.ROLE_SUPER_ADMIN,RoleName.ROLE_ADMIN)),
    CHANGE_USER_ENABLE("Change user enable", Arrays.asList(RoleName.ROLE_SUPER_ADMIN,RoleName.ROLE_ADMIN)),
    CHANGE_AGENT_ONLINE("Change online status", Collections.singletonList(RoleName.ROLE_AGENT)),
    CHANGE_ADMIN_PERMISSION("Change admin permission", Collections.singletonList(RoleName.ROLE_SUPER_ADMIN)),
    CHANGE_AGENT_AND_CUSTOMER_PERMISSION("Change agent and customer permission", Arrays.asList(RoleName.ROLE_SUPER_ADMIN,RoleName.ROLE_ADMIN)),
    CHANGE_DISCOUNT_TYPE("Change discount type",Arrays.asList(RoleName.ROLE_SUPER_ADMIN,RoleName.ROLE_ADMIN)),

    GET_ADMINS("Get admins", Collections.singletonList(RoleName.ROLE_SUPER_ADMIN)),
    GET_AGENTS_AND_CUSTOMERS("Get agents and customers", Arrays.asList(RoleName.ROLE_SUPER_ADMIN,RoleName.ROLE_ADMIN)),
    GET_DISCOUNT_TYPE("Get discount type", Arrays.asList(RoleName.ROLE_SUPER_ADMIN,RoleName.ROLE_ADMIN));



    public List<RoleName> roleNames;
    public String name;

    PermissionName(String name, List<RoleName> roleNames) {
        this.roleNames = roleNames;
        this.name = name;
    }
}
