package uz.pdp.appduonotarypraktikaserver.resModels;

import java.util.UUID;

public interface ResCountry {
    UUID getId();
    Boolean getActive();
    Boolean getEmbassy();
    String getDescription();
    String getName();
}
