package uz.pdp.appduonotarypraktikaserver.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.entity.User;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqChangeZipCode;
import uz.pdp.appduonotarypraktikaserver.payload.ReqPassword;
import uz.pdp.appduonotarypraktikaserver.payload.ReqUser;
import uz.pdp.appduonotarypraktikaserver.payload.ReqChangeZipCode;
import uz.pdp.appduonotarypraktikaserver.security.CurrentUser;
import uz.pdp.appduonotarypraktikaserver.service.UserService;
import uz.pdp.appduonotarypraktikaserver.utils.AppConstants;
import uz.pdp.appduonotarypraktikaserver.utils.CommonUtils;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/me")
    public HttpEntity<?> userMe(@CurrentUser User user){
        return ResponseEntity.status(user!=null?HttpStatus.OK:HttpStatus.CONFLICT).body(user);
    }

    //    @PreAuthorize("hasAuthority('CHANGE_USER_ACTIVE')")
    @GetMapping("/changeActive")
    public HttpEntity<?> changeActive(@RequestParam UUID userId) {
        ApiResponse apiResponse = userService.changeActiveOrEnable(userId, "active");
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    ///   @PreAuthorize("hasAuthority('CHANGE_USER_ENABLE')")
    @GetMapping("/changeEnable")
    public HttpEntity<?> changeEnable(@RequestParam UUID userId) {
        ApiResponse apiResponse = userService.changeActiveOrEnable(userId, "enable");
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    ///   @PreAuthorize("hasAuthority('CHANGE_AGENT_ONLINE')")
    @GetMapping("/changeOnline")
    public HttpEntity<?> changeOnline(@CurrentUser User user) {
        ApiResponse apiResponse = userService.changeOnline(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    ///   @PreAuthorize("hasAuthority('GET_ADMINS')")
    @GetMapping("/getAdmins")
    public HttpEntity<?> getAdmins(@RequestParam (value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                   @RequestParam (value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                   @RequestParam (value = "sort",defaultValue = "all") String sort,
                                   @RequestParam (value = "sortType",defaultValue = "ASC") String sortType) {
        ApiResponse apiResponse = userService.getUsersByRole(RoleName.ROLE_ADMIN,sort.equals("all")?CommonUtils.createPageable(page,pageSize):CommonUtils.createPageableBySort(page,pageSize,sort,sortType));
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    ///   @PreAuthorize("hasAuthority('GET_AGENTS_AND_CUSTOMERS')")
    @GetMapping("/getClients")
    public HttpEntity<?> getClients(@RequestParam (value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                    @RequestParam (value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                    @RequestParam (value = "sort",defaultValue = "all") String sort,
                                    @RequestParam (value = "sortType",defaultValue = "ASC") String sortType ) {
        ApiResponse apiResponse = userService.getUsersByRole(RoleName.ROLE_CUSTOMER,sort.equals("all")?CommonUtils.createPageable(page,pageSize):CommonUtils.createPageableBySort(page,pageSize,sort,sortType));
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    ///   @PreAuthorize("hasAuthority('GET_AGENTS_AND_CUSTOMERS')")
    @GetMapping("/getAgents")
    public HttpEntity<?> getAgents(@RequestParam (value = "page",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                   @RequestParam (value = "pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
                                   @RequestParam (value = "sort",defaultValue = "all") String sort,
                                   @RequestParam (value = "sortType",defaultValue = "ASC") String sortType ) {
        ApiResponse apiResponse = userService.getUsersByRole(RoleName.ROLE_AGENT,sort.equals("all")?CommonUtils.createPageable(page,pageSize):CommonUtils.createPageableBySort(page,pageSize,sort,sortType));
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    ///   @PreAuthorize("hasAuthority('CHANGE_ADMIN_PERMISSIONS')")
    @PostMapping("/changeAdminPermission")
    public HttpEntity<?> changeAdminPermission(@RequestBody List<Integer> permissionIds,@RequestParam UUID userId ){
        ApiResponse apiResponse = userService.editPermissions(permissionIds,userId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    ///   @PreAuthorize("hasAuthority('CHANGE_AGENT_AND_CUSTOMER_PERMISSION')")
    @PostMapping("/changeUserPermission")
    public HttpEntity<?> changeUserPermission(@RequestBody List<Integer> permissionIds,@RequestParam UUID userId ){
        ApiResponse apiResponse = userService.editPermissions(permissionIds,userId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/editUser")
    public HttpEntity<?> editUser(@RequestBody ReqUser reqUser){
        ApiResponse apiResponse = userService.editUser(reqUser);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping("/changePassword")
    public HttpEntity<?> changePasword(@RequestBody ReqPassword reqPassword, @CurrentUser User user){
        ApiResponse apiResponse=userService.changePassword(reqPassword,user);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);

    }

    @PostMapping("/editZipCode")
    public HttpEntity<?> editZipCode(@RequestBody ReqChangeZipCode reqChangeZipCode){
        ApiResponse apiResponse = userService.editZipCode(reqChangeZipCode);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


}
