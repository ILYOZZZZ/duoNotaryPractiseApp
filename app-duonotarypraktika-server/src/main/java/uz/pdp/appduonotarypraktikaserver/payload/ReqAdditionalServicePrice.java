package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReqAdditionalServicePrice {
    private UUID id;
    private double price;
    private List<UUID> zipCodesId;
    private UUID additionalServiceId;
    private UUID serviceId;
    private boolean active;
}
