package uz.pdp.appduonotarypraktikaserver.payload;


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReqSignIn {
    @NotBlank
    private String phoneNumberOrEmail;

    @NotBlank
    private String password;


    public String getPhoneNumberOrEmail() {
        return phoneNumberOrEmail;
    }

    public void setPhoneNumberOrEmail(String phoneNumberOrEmail) {
        this.phoneNumberOrEmail = phoneNumberOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
