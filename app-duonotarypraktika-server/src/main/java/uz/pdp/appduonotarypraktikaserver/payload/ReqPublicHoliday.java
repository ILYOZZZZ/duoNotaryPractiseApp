package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqPublicHoliday {
    public UUID id;
    public Date date;
    public String name;
    public String description;
    public boolean active;
    public UUID mainServiceId;
}
