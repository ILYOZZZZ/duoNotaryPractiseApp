package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ReqDiscountType {

    private UUID id;

    private String name;

    private double percent;

    private double maxDiscountSum;
}
