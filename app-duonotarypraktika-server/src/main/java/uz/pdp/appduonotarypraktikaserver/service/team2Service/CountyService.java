package uz.pdp.appduonotarypraktikaserver.service.team2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.County;
import uz.pdp.appduonotarypraktikaserver.entity.State;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqCounty;
import uz.pdp.appduonotarypraktikaserver.repository.CountyRepository;
import uz.pdp.appduonotarypraktikaserver.repository.StateRepository;
import uz.pdp.appduonotarypraktikaserver.resModels.ResCountry;
import uz.pdp.appduonotarypraktikaserver.resModels.ResCounty;
import uz.pdp.appduonotarypraktikaserver.utils.CommonUtils;

import java.util.UUID;

@Service
public class CountyService {
    @Autowired
    CountyRepository countyRepository;

    @Autowired
    StateRepository stateRepository;

    public ApiResponse saveCounty(ReqCounty reqCounty){
        County county = new County();
        State state = stateRepository.findById(reqCounty.getStateId()).orElseThrow(() -> new ResourceNotFound("State", "id", reqCounty.getStateId()));
        county.setName(reqCounty.getName());
        county.setActive(reqCounty.isActive());
        county.setDescription(reqCounty.getDescription());
        county.setState(state);
        countyRepository.save(county);
        return new ApiResponse("successfully saved",true);
    }

    public ApiResponse editCounty(ReqCounty reqCounty,UUID id){
        County county = countyRepository.findById(id).orElseThrow(() -> new ResourceNotFound("County", "id", id));
        if(reqCounty.getStateId()!=null){
            State state = stateRepository.findById(reqCounty.getStateId()).orElseThrow(() -> new ResourceNotFound("State", "id", reqCounty.getStateId()));
            county.setState(state);
        }
        county.setDescription(reqCounty.getDescription());
        county.setActive(reqCounty.isActive());
        county.setName(reqCounty.getName());
        countyRepository.save(county);
        return new ApiResponse("successfully edited",true);
    }

    public ApiResponse deleteById(UUID id){
        countyRepository.deleteById(id);
        return new ApiResponse("successfully deleted",true);
    }

    public ApiResponse getAll(Integer page , Integer size , UUID stateId , String countyName){
        Pageable pageable = CommonUtils.createPageable(page, size);
        return new ApiResponse("succesfully got",true,countyRepository.getAllCountiesByState(stateId,countyName, pageable));
    }


    public ApiResponse editCountyActive(UUID id){
        County county = countyRepository.findById(id).orElseThrow(() -> new ResourceNotFound("County", "id", id));
        county.setActive(!county.getActive());
        countyRepository.save(county);
        return new ApiResponse("Successfully edited",true);
    }


    public ApiResponse getCurrent(UUID id) {
        ResCounty currentCounty = countyRepository.getCurrentCounty(id);
        return new ApiResponse("Successfully got",true,currentCounty);

    }
}
