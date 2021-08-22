package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqAdditionalService {
    private UUID id;
    private String name,description;
    private Boolean active;
}
