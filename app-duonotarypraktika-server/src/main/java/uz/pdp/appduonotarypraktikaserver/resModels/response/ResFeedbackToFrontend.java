package uz.pdp.appduonotarypraktikaserver.resModels.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import uz.pdp.appduonotarypraktikaserver.resModels.ResFeedback;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class  ResFeedbackToFrontend {
    private Page<ResFeedback> resFeedbackList;
    private Integer totalAmountOfFeedbacks;
}
