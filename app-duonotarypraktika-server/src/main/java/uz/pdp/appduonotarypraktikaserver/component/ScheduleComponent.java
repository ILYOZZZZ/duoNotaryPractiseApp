package uz.pdp.appduonotarypraktikaserver.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import uz.pdp.appduonotarypraktikaserver.repository.ActionHistoryRepository;

//@EnableScheduling
@Component
public class ScheduleComponent {

    @Autowired
    ActionHistoryRepository historyRepository;

    /**
     * BU METHOD LOGGA @PREPERSIST BULGANDA INSERT ROWLARNI YOZIB BORADI
     **/
//    @Scheduled(cron = "*/5 * * * * *")
//    public void updatePostNull() {
//        historyRepository.updateHistoryObjectIsNull();
//    }
}