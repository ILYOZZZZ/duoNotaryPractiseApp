package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.Data;
import org.omg.CORBA.TIMEOUT;

import java.sql.Time;
import java.util.List;
import java.util.UUID;

@Data
public class ReqMainService {
    private UUID id;
    private String name,description;
    private String fromTime,tillTime;
    private boolean online;
    private boolean active;
    private int showNumber;
}
