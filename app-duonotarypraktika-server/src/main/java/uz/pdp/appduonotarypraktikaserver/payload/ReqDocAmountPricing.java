package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class ReqDocAmountPricing {
    private UUID id;
    private double price;
    private int fromCount;
    private int tillCount;
    private int everyCount;
    private UUID servicePriceId;
}
