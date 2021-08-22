package uz.pdp.appduonotarypraktikaserver.payload;


import lombok.Data;
import uz.pdp.appduonotarypraktikaserver.entity.County;

import java.util.UUID;

import java.util.List;
import java.util.UUID;

@Data
public class ReqChangeZipCode {

    private UUID userId;
    private String zipCode;

    private List<UUID> zipCodeIds;

}
