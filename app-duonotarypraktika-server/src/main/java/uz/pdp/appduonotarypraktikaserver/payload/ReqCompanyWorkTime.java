package uz.pdp.appduonotarypraktikaserver.payload;

import java.sql.Time;
import java.util.UUID;
import lombok.Data;

@Data
public class ReqCompanyWorkTime {
    private UUID id;

    private String fromTime;

    private String tillTime;

    private int chargePercent;

    private boolean active;

    private UUID mainServiceId;
}
