package uz.pdp.appduonotarypraktikaserver.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.pdp.appduonotarypraktikaserver.entity.ActionHistory;
import uz.pdp.appduonotarypraktikaserver.entity.enums.OperationName;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;
import uz.pdp.appduonotarypraktikaserver.repository.ActionHistoryRepository;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Component
public class ActionHistoryListener {
    @Autowired
    ActionHistoryRepository actionHistoryRepository;

    @PrePersist
    public void insert(Object object) { saveLogDuo(object, OperationName.INSERT);
    }

    //BU METHOD TABLEGA UPDATE BULGANDA QAYD QILIB BORADI
    @PreUpdate
    public void update(Object object) {
        saveLogDuo(object, OperationName.UPDATE);
    }

    //BU METHOD TABLEGA DELETE BULGANDA QAYD QILIB BORADI
    @PreRemove
    public void remove(Object object) {
        saveLogDuo(object, OperationName.DELETE);
    }

    public void saveLogDuo(Object object, OperationName operationName) {
        String table = object.toString();
        AbsEntity absEntity = (AbsEntity) object;
        actionHistoryRepository.save(new ActionHistory(
                table.substring(0, table.indexOf("(")),
                absEntity.getId(),
                operationName,
                object
        ));
    }

}
