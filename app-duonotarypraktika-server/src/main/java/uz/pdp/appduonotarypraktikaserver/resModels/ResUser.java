package uz.pdp.appduonotarypraktikaserver.resModels;

import org.springframework.beans.factory.annotation.Value;
import uz.pdp.appduonotarypraktikaserver.entity.Permission;

import java.util.List;
import java.util.UUID;

public interface ResUser{

    UUID getId();
    String getFirstName();
    String getLastName();
    String getPhoneNumber();
    String getEmail();
    String getAddress();
    boolean getActive() ;
    boolean getOnline();
    boolean getOnlineAgent();
    double getDiscountSum();
    UUID   getAttachmentId();
    UUID getRefererId();

    @Value("#{@permissionRepository.getPermissionListByUserId(target.id)}")
    List<ResPermission> getPermissions();

    @Value("#{@zipCodeRepository.getZipCodeByUserId(target.id)}")
    List<ResZipCode> getZipCodes();

    @Value("#{@idCardRepository.findByUserChopildi(target.id)}")
    ResIdCard getIdCard();

    @Value("#{@certificateRepository.findAllByFozil(target.id)}")
    List<ResCertificate> getCertificate();

    @Value("#{@roleRepository.findRolesByUserId(target.id)}")
    List<ResRole> getRole();


}

