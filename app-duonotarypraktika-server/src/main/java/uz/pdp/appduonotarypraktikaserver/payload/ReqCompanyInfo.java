package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqCompanyInfo {
    private UUID id;

    private String companyName;

    private String phoneNumbers;

    private String address;

    private String email;

    private String fax;

    private UUID logoId;

}
