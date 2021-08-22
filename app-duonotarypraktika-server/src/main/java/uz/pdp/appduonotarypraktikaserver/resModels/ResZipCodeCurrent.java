package uz.pdp.appduonotarypraktikaserver.resModels;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface ResZipCodeCurrent {


    UUID getId();
    String getZipCode();
    String getStateName();
    UUID getStateId();

    @Value("#{@mainServiceWorkTimeRepository.getWorkHoursByZipCode(target.id)}")
    List<ResWorkHours> getWorkHours();

    @Value("#{@userZipCodeRepository.getAgentsOfZipCode(target.id)}")
    List<ResAgentsByZipCode> getAgents();


}
