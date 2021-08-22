package uz.pdp.appduonotarypraktikaserver.resModels;

import java.util.UUID;

public interface ResState {

    UUID getId();
    String getName();
    String getDescription();
    boolean getActive();

}
