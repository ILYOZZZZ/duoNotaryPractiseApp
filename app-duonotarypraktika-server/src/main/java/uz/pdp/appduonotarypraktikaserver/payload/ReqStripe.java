package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

@Data
public class ReqStripe {
    String email;
    String token;
    Integer amount;
}
