package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

@Data
public class ReqPassword {

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;

}
