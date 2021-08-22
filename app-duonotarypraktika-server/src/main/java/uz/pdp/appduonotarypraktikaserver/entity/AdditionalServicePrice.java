package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AdditionalServicePrice extends AbsEntity {

    private double price;

    private boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private ZipCode zipCode;

    @OneToOne
    private AdditionalService additionalService;

    @ManyToOne
    private Services service;

}
