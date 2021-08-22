package uz.pdp.appduonotarypraktikaserver.resModels;

import java.math.BigInteger;
import java.util.UUID;

public interface ResAgentsByZipCode {

    UUID getId();
    String getFirstName();
    String getLastName();
    String getAddress();
    BigInteger getOrderCount();
//    String agentOnlineTime();
//    UUID attachmentId();

}
