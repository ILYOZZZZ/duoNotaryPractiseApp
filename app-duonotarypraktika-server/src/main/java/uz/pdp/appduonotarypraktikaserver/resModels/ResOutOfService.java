package uz.pdp.appduonotarypraktikaserver.resModels;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface ResOutOfService {

    UUID getId();
    String  getClientEmail();


    @Value("#{@zipCodeRepository.getResZipCodeByOutOfServiceId(target.id)}")
    ResZipCodes getResZipCode();



}
