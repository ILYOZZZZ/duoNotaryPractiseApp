package uz.pdp.appduonotarypraktikaserver.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqState {

    private String name;

    private String description;

    private boolean active;

}
