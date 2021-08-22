package uz.pdp.appduonotarypraktikaserver.resModels.team1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckedTime {

    private String time;

    private boolean avalaible;

    public CheckedTime(String time, Boolean available) {
        this.time = time;
        this.avalaible = available;
    }
}
