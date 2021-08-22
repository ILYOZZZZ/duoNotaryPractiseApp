package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

@Data
public class ReqUserZipCode {

    private String oldZipCode;

    private String newZipCode;

}
