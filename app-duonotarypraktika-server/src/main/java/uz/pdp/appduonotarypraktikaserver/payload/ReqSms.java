package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

@Data
public class ReqSms {
    private String phoneNumber;

    private int verifyCode;
}
