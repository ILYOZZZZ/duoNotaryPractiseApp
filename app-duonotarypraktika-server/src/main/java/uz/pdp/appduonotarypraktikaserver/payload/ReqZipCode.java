package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
public class ReqZipCode {
    private String zipCode;
    private UUID countyId;
}
