package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Testimonials extends AbsEntity {

    private String firstName;

    private String lastName;

    private String companyInfo;

    private String text;

    @OneToOne
    private Attachment attachment;
}
