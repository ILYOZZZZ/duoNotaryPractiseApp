package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;
import uz.pdp.appduonotarypraktikaserver.entity.MainService;

import java.util.UUID;

@Data
public class ReqServices {

    private UUID id;

    private String name, description;

    public boolean active;

    private int initialCount;

    private int initialSpendingTime;

    private int everyCount;

    private int everySpendingTime;



    private UUID mainServiceId;


}
