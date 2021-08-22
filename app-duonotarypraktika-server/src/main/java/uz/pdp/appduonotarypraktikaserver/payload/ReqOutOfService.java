package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class ReqOutOfService {


    private String clientEmail;

    private UUID waitingZipCodeId;
}
