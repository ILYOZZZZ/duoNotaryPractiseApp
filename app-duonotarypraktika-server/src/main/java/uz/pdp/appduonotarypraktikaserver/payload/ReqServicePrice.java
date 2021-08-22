package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReqServicePrice {
    private UUID id;

    private double price;

    private int chargeMinute;

    private boolean active;

    private double chargePercent;

    private List<UUID> zipCodeIds;

    private UUID serviceId;

}
