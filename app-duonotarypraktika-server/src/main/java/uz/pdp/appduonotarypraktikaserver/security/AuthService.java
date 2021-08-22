package uz.pdp.appduonotarypraktikaserver.security;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.*;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqCertificate;
import uz.pdp.appduonotarypraktikaserver.payload.ReqUser;
import uz.pdp.appduonotarypraktikaserver.repository.*;
import uz.pdp.appduonotarypraktikaserver.service.MailService;

import java.util.*;

@Service
public class AuthService implements UserDetailsService {
    private final UserRepository userRepository;

    private final MessageSource messageSource;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final ZipCodeRepository zipCodeRepository;
    private final UserZipCodeRepository userZipCodeRepository;
    private final IdCardRepository idCardRepository;
    private final AttachmentRepository attachmentRepository;
    private final MailService mailService;
    private final CertificateRepository certificateRepository;
    private final StateRepository stateRepository;

    public AuthService(@Lazy UserRepository userRepository, MessageSource messageSource, PasswordEncoder passwordEncoder, RoleRepository roleRepository, PermissionRepository permissionRepository, ZipCodeRepository zipCodeRepository, UserZipCodeRepository userZipCodeRepository, IdCardRepository idCardRepository, AttachmentRepository attachmentRepository, MailService mailService, CertificateRepository certificateRepository, StateRepository stateRepository) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.zipCodeRepository = zipCodeRepository;
        this.userZipCodeRepository = userZipCodeRepository;
        this.idCardRepository = idCardRepository;
        this.attachmentRepository = attachmentRepository;
        this.mailService = mailService;
        this.certificateRepository = certificateRepository;
        this.stateRepository = stateRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumberOrEmail) throws UsernameNotFoundException {
        return userRepository.findByPhoneNumberOrEmail(phoneNumberOrEmail, phoneNumberOrEmail).orElseThrow(() -> new UsernameNotFoundException(phoneNumberOrEmail));
    }

    public UserDetails loadUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("User id not found: " + userId));
    }

    public ResponseEntity saveUser(ReqUser reqUser, Boolean admin) {
        try {
            ApiResponse apiResponse = checkPhoneNumberOrEmail(reqUser.getPhoneNumber(), reqUser.getEmail());
            if (!apiResponse.isSuccess()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(apiResponse);
            }
            if (reqUser.getIdCard() != null) {

                ApiResponse checkIdCard = checkCertificateOrIdCard(reqUser.getIdCard());
                if (!checkIdCard.isSuccess()) {
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(checkIdCard);
                }
            }
            if (reqUser.getReqCertificateList() != null) {
                for (ReqCertificate reqCertificate : reqUser.getReqCertificateList()) {
                    ApiResponse checkCertificate = checkCertificateOrIdCard(reqCertificate);
                    if (!checkCertificate.isSuccess()) {
                        return ResponseEntity.status(HttpStatus.CONFLICT).body(checkCertificate);
                    }
                }
            }
            User savedUser = makeUser(reqUser, admin);
            if (reqUser.getZipCodeIds() != null) {
                for (UUID zipCodeId : reqUser.getZipCodeIds()) {
                    UserZipCode userZipCode = new UserZipCode();
                    userZipCode.setUser(savedUser);
                    ZipCode zipCode = zipCodeRepository.findById(zipCodeId).orElseThrow(() -> new ResourceNotFound("ZipCode", "Id", zipCodeId));
                    userZipCode.setZipCode(zipCode);
                    userZipCodeRepository.save(userZipCode);
                }
            }
            if (reqUser.getIdCard() != null) {
                IdCard idCard = new IdCard();
                idCard.setIdCode(reqUser.getIdCard().getCertificateNumber());
                idCard.setIssueDate(reqUser.getIdCard().getIssueDate());
                idCard.setExpireDate(reqUser.getIdCard().getIssueDate());
                idCard.setUser(savedUser);
                if (reqUser.getIdCard().getAttachmentId() != null) {
                    Attachment attachment = attachmentRepository.findById(reqUser.getIdCard().getAttachmentId()).orElseThrow(() -> new ResourceNotFound("Attachment", "Id", reqUser.getIdCard().getAttachmentId()));
                    idCard.setAttachment(attachment);
                }
                idCardRepository.save(idCard);
            }
            if (reqUser.getReqCertificateList() != null) {
                for (ReqCertificate reqCertificate : reqUser.getReqCertificateList()) {
                    Certificate certificate = new Certificate();
                    if (reqCertificate.getAttachmentId() != null) {
                        certificate.setAttachment(attachmentRepository.findById(reqCertificate.getAttachmentId()).orElseThrow(() -> new ResourceNotFound("Attachment", "Id", reqCertificate.getAttachmentId())));
                    }
                    certificate.setIssueDate(reqCertificate.getIssueDate());
                    certificate.setExpireDate(reqCertificate.getExpireDate());
                    certificate.setCertificateNumber(reqCertificate.getCertificateNumber());
                    certificate.setUser(savedUser);
                    certificate.setState(stateRepository.findById(reqCertificate.getStateId()).orElseThrow(() -> new ResourceNotFound("State", "Id", reqCertificate.getStateId())));
                    certificateRepository.save(certificate);
                }
            }
            mailService.sendVerificationCode(savedUser, false);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse("Saved", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse("Error", false));
        }
    }

    public ApiResponse checkPhoneNumberOrEmail(String phoneNumber, String email) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            return new ApiResponse("This phonenumber is already exist", false);
        } else if (userRepository.existsByEmail(email)) {
            return new ApiResponse("This email is already exist", false);
        } else {
            return new ApiResponse("Ok", true);
        }
    }

    public User makeUser(ReqUser reqUser, Boolean admin) {
        Set<Permission> permissionSet = new HashSet<>();
        if (admin != null) {
            if (admin) {
                for (Integer permissionId : reqUser.getPermissionIds()) {
                    permissionSet.add(permissionRepository.findById(permissionId).orElseThrow(() -> new ResourceNotFound("Permission", "Id", permissionId)));
                }
            } else {
                permissionSet.addAll(new HashSet<Permission>(permissionRepository.findAllByRoleName(RoleName.ROLE_AGENT.name())));
            }
        } else {
            permissionSet.addAll(new HashSet<Permission>(permissionRepository.findAllByRoleName(RoleName.ROLE_CUSTOMER.name())));
        }
        User user = new User(reqUser.getFirstName(), reqUser.getLastName(), reqUser.getPhoneNumber(), reqUser.getAddress(), passwordEncoder.encode(reqUser.getPassword()), reqUser.getEmail(),
                admin != null ? admin ? roleRepository.findAllByNameIn(Collections.singletonList(RoleName.ROLE_ADMIN)) :
                        roleRepository.findAllByNameIn(Collections.singletonList(RoleName.ROLE_AGENT)) :
                        roleRepository.findAllByNameIn(Collections.singletonList(RoleName.ROLE_CUSTOMER))
                , permissionSet);
        if (reqUser.getPhotoId() != null) {
            Attachment attachment = attachmentRepository.findById(reqUser.getPhotoId()).orElseThrow(() -> new ResourceNotFound("Photo", "Id", reqUser.getPhotoId()));
            user.setAttachment(attachment);
        }
        if (reqUser.getRefererUserId() != null) {
            user.setRefererUser(userRepository.findById(reqUser.getRefererUserId()).orElseThrow(() -> new ResourceNotFound("User", "Id", reqUser.getRefererUserId())));
        }
        return userRepository.save(user);
    }

    public ApiResponse checkCertificateOrIdCard(ReqCertificate reqCertificate) {
        if (reqCertificate.getIssueDate().after(reqCertificate.getExpireDate())) {
            return new ApiResponse("Certificate issue date is not valid", false);
        } else if (reqCertificate.getExpireDate().before(new Date())) {
            return new ApiResponse("Certificate expire date is not valid", false);
        } else {
            return new ApiResponse("Ok", true);
        }
    }

    public ApiResponse verifyEmail(boolean check, UUID userId, String verificationCode, boolean changing) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFound("User", "id", userId));

        if (check) {

            if (user.getEmailCode().equals(verificationCode)) {

                if (!changing) {
                    user.setEnabled(true);
                } else {
                    user.setEmail(user.getChangedEmail());
                    user.setChangedEmail(null);

                }
                userRepository.save(user);
                return new ApiResponse(changing ? "changed " : "verifycated", true);

            }
            return new ApiResponse("invalid verifycation code", false);

        } else {
            if (changing) {
                user.setChangedEmail(null);
                userRepository.save(user);
                return new ApiResponse("Verify changingEmail canceled", false);
            } else {
                userRepository.delete(user);
                return new ApiResponse("Email Verifycation is canceled", false);
            }
        }

    }
}
