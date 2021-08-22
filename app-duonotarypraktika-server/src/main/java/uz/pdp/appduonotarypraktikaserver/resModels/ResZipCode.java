package uz.pdp.appduonotarypraktikaserver.resModels;

import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

public interface ResZipCode {

    UUID getId();

    String getZipCode();

    @Value("#{@zipCodeRepository.getCountyByZipCodeId(target.id)}")
    ResCounty getCounty();

    @Value("#{@zipCodeRepository.getStateByZipCodeId(target.id)}")
    ResState getState();

}
