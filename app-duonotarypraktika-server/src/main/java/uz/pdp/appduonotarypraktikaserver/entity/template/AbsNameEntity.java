package uz.pdp.appduonotarypraktikaserver.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbsNameEntity extends AbsEntity {

    private String name, description;

    private Boolean Active;
}
