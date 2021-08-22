package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReqUser {

    private UUID id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private String address;

    private String password;

    private UUID photoId;

    private List<Integer> permissionIds;

    private List<UUID> zipCodeIds;

    private List<ReqCertificate> reqCertificateList;

    private ReqCertificate idCard;

    private UUID refererUserId;


}
