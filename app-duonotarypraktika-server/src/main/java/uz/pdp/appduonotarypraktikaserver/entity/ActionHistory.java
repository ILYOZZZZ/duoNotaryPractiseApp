package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import uz.pdp.appduonotarypraktikaserver.config.JpaConverterJson;
import uz.pdp.appduonotarypraktikaserver.entity.enums.OperationName;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActionHistory {
    @Id
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @CreatedBy
    @Column(name = "created_by_id")
    private UUID createdBy;

    @OrderBy
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;

    private String tableName;

    private UUID objectId;

    @Column(columnDefinition = "text")
    @Convert(converter = JpaConverterJson.class)
    private Object object;

    @Enumerated(EnumType.STRING)
    private OperationName operationName;

    public ActionHistory(String tableName, UUID objectId, OperationName operationName,Object object){
        this.tableName=tableName;
        this.objectId=objectId;
        this.operationName=operationName;
        this.object=object;
    }


    public enum  WeekDayName {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }
}
