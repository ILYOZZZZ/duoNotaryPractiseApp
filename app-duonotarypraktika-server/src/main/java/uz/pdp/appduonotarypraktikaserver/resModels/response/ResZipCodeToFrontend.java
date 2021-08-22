package uz.pdp.appduonotarypraktikaserver.resModels.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.resModels.ResZipCodes;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResZipCodeToFrontend {
    private Long totalElements;
    private UUID countyId;
    private String countyName;
    private List<ResZipCodes> zipCodes;

}
