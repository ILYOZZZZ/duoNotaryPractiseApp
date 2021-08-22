package uz.pdp.appduonotarypraktikaserver.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;

import javax.persistence.Entity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PhoneVerify extends AbsEntity {

    private String phoneNumber;

    private int verifyCode;

    private boolean verify;
}
