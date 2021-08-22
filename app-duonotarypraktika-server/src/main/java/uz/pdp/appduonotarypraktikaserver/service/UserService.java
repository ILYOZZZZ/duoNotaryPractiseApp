package uz.pdp.appduonotarypraktikaserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.County;
import uz.pdp.appduonotarypraktikaserver.entity.User;
import uz.pdp.appduonotarypraktikaserver.entity.UserZipCode;
import uz.pdp.appduonotarypraktikaserver.entity.ZipCode;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqChangeZipCode;
import uz.pdp.appduonotarypraktikaserver.payload.ReqPassword;
import uz.pdp.appduonotarypraktikaserver.payload.ReqUser;
import uz.pdp.appduonotarypraktikaserver.payload.ReqUserZipCode;
import uz.pdp.appduonotarypraktikaserver.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MailService mailService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ZipCodeRepository zipCodeRepository;

    @Autowired
    UserZipCodeRepository userZipCodeRepository;

    public ApiResponse changeActiveOrEnable(UUID userId, String field) {

//        if (field.equals("active")) {
//            boolean isActive = userRepository.changeActive(userId);
//            if (mailService.sendChangeActive(userId)) {
//                return new ApiResponse("User " + (isActive ? "activated" : "blocked"), true);
//            }else {
//                return new ApiResponse("There was a problem with sending a mail", false);
//
//            }
//        } else if(field.equals("enable")) {
//            return new ApiResponse("User " + (userRepository.changeEnable(userId) ? "enabled" : "disabled"), true);
//        }
         return null;
    }

    public ApiResponse changeOnline(User user){
//        if (user.isActive()) {
//            return new ApiResponse("Agent " + (userRepository.changeOnline(user.getId()) ? "online" : "offline"), true);
//        }
        return null;
    }


    public ApiResponse getUsersByRole(RoleName roleName, Pageable pageable) {
        try {
            return new ApiResponse("success", true, userRepository.findAllByRoles(roleName.name(), pageable));
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("error", false);

        }
    }

    public ApiResponse editPermissions(List<Integer> permissionIds, UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", userId));
        user.setPermissions(permissionRepository.findAllByIds(permissionIds));
        userRepository.save(user);
        return new ApiResponse("success", true);
    }

    public ApiResponse editUser(ReqUser reqUser) {

        User user = userRepository.findById(reqUser.getId()).orElseThrow(() -> new ResourceNotFound("User", "id", reqUser.getId()));
        user.setChangedEmail(reqUser.getEmail());
        user.setFirstName(reqUser.getFirstName());
        user.setLastName(reqUser.getLastName());
        user.setPhoneNumber(reqUser.getPhoneNumber());
        user.setAttachment(reqUser.getPhotoId() == null ? null : attachmentRepository.findById(reqUser.getPhotoId()).orElseThrow(() -> new ResourceNotFound("Attachment", "id", reqUser.getPhotoId())));
        User save = userRepository.save(user);
        mailService.sendVerificationCode(save, true);
        return new ApiResponse("success", true);

    }

    public ApiResponse changePassword(ReqPassword reqPassword, User user) {

        ApiResponse apiResponse = checkPassword(reqPassword, user);
        if (apiResponse.isSuccess()) {
            user.setPassword(passwordEncoder.encode(reqPassword.getNewPassword()));
            userRepository.save(user);


        }
        return apiResponse;
    }

    public ApiResponse checkPassword(ReqPassword reqPassword, User user) {

        if (passwordEncoder.matches(reqPassword.getOldPassword(), user.getPassword())) {
            if (reqPassword.getNewPassword().equals(reqPassword.getConfirmPassword())) {

                return new ApiResponse("Ok", true);
            }
            return new ApiResponse("NewPassword and confirmPassword not equals", false);


        }
        return new ApiResponse("Old password is wrong", false);


    }

    public ApiResponse editZipCode(ReqChangeZipCode reqChangeZipCode){
        User user = userRepository.findById(reqChangeZipCode.getUserId()).orElseThrow(() -> new ResourceNotFound("User", "id", reqChangeZipCode.getUserId()));
        List<ZipCode> allById = zipCodeRepository.findAllById(reqChangeZipCode.getZipCodeIds());
        List<UserZipCode> userZipCodes = new ArrayList<>();
        for (ZipCode zipCode : allById) {
            UserZipCode userZipCode = new UserZipCode();
            userZipCode.setUser(user);
            userZipCode.setZipCode(zipCode);
            userZipCodes.add(userZipCode);
        }
        userZipCodeRepository.saveAll(userZipCodes);
        return new ApiResponse("ok",true);
    }
}
