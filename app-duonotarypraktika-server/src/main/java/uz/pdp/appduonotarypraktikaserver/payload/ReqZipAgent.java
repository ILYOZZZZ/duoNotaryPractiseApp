package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class ReqZipAgent {
    private UUID zipCodeId;
    private UUID agentId;
}
