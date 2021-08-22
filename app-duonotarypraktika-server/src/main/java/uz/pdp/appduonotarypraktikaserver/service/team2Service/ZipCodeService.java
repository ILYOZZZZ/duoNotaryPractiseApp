package uz.pdp.appduonotarypraktikaserver.service.team2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.*;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqZipAgent;
import uz.pdp.appduonotarypraktikaserver.payload.ReqZipCode;
import uz.pdp.appduonotarypraktikaserver.repository.*;
import uz.pdp.appduonotarypraktikaserver.resModels.ResAgentsByZipCode;
import uz.pdp.appduonotarypraktikaserver.resModels.ResCounty;
import uz.pdp.appduonotarypraktikaserver.resModels.ResState;
import uz.pdp.appduonotarypraktikaserver.resModels.ResZipCodes;
import uz.pdp.appduonotarypraktikaserver.resModels.response.ResZipCodeToFrontend;
import uz.pdp.appduonotarypraktikaserver.utils.CommonUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ZipCodeService {
    @Autowired
    ZipCodeRepository zipCodeRepository;

    @Autowired
    CountyRepository countyRepository;

    @Autowired
    UserZipCodeRepository userZipCodeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    StateRepository stateRepository;

    public ApiResponse saveZipCode(ReqZipCode reqZipCode){
        County county = countyRepository.findById(reqZipCode.getCountyId()).orElseThrow(() -> new ResourceNotFound("County", "id", reqZipCode.getCountyId()));
        zipCodeRepository.save(new ZipCode(reqZipCode.getZipCode(), county));
        return new ApiResponse("success",true);
    }
    public ApiResponse editZipCode(ReqZipCode reqZipCode, UUID id){
        ZipCode zipCode = zipCodeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("ZipCode", "id", id));
        zipCode.setCounty(countyRepository.findById(reqZipCode.getCountyId()).orElseThrow(() -> new ResourceNotFound("County", "id", reqZipCode.getCountyId())));
        zipCode.setZipCode(reqZipCode.getZipCode());
        zipCodeRepository.save(zipCode);
        return new ApiResponse("success",true);
    }
    public ApiResponse deleteZipCode(UUID id){


        zipCodeRepository.delete( zipCodeRepository.findById(id).orElseThrow(() -> new ResourceNotFound("ZipCode", "id",id)));
        return new ApiResponse("success",true);
    }

    public ApiResponse getAllZipCode(Integer page , Integer size, String name){
        Pageable pageable = CommonUtils.createPageable(page, size);
        Page<ResState> allStates = stateRepository.getAllStates(name, pageable);


        return new ApiResponse("suc cessfully got",true,makeAsTree(allStates));
    }
    public ApiResponse getZipCodeById(UUID id){
        return new ApiResponse("successfully got by Id",true,zipCodeRepository.getCurrentZipCodes(id));
    }

    public List<ResZipCodeToFrontend> makeAsTree (Page<ResState> states){

        List<ResZipCodeToFrontend> zipCodes = new ArrayList<>();

        for (ResState resState : states.getContent()) {
            List<ResZipCodes> zipCodesOfState = zipCodeRepository.getZipCodesOfState(resState.getId());
            zipCodes.add(new ResZipCodeToFrontend(
                    states.getTotalElements(),
                    resState.getId(),
                    resState.getName(),
                    zipCodesOfState
            ));
        }
        return zipCodes;
    }

    @Transactional
    public ApiResponse deleteAgentFromZipCode(UUID agentId){
        userZipCodeRepository.deleteByAgentId(agentId);
        return new ApiResponse("success",true);
    }

    public ApiResponse getCurrentState(UUID stateId,UUID zipCodeId,String stateName){
        List<Object> stateAndCounty = new ArrayList<>();
        Pageable pageable = CommonUtils.createPageable(1, 5);
        Page<ResState> allStates = stateRepository.getAllStates(stateName, pageable);
        Page<ResCounty> allCountiesByState = countyRepository.getAllCountiesByState(stateId, "", pageable);
        ResCounty countyByZipCode = countyRepository.getCountyByZipCode(zipCodeId);
        stateAndCounty.add(allStates);
        stateAndCounty.add(allCountiesByState);
        stateAndCounty.add(countyByZipCode);
        return new ApiResponse("success",true,stateAndCounty);
    }

    public ApiResponse getZipsOfCounty(UUID countyId){


        List<ResZipCodes> zipCodesByCountyId = zipCodeRepository.getZipCodesByCountyId(countyId);

        return new ApiResponse("success",true,zipCodesByCountyId);
    }


    public Object getCurrentCounty(UUID countyId){
        return countyRepository.getCurrentCounty(countyId);
    }

    public ApiResponse getAgentsToLinkZipCode(UUID stateId , UUID zipCodeId){
        List<ResAgentsByZipCode> allAgentsToLinkZipCode = userRepository.getAllAgentsToLinkZipCode(stateId, zipCodeId);
        return new ApiResponse("success",true,allAgentsToLinkZipCode);
    }

    public ApiResponse saveAgentToZip(ReqZipAgent reqZipAgent){
        User user = userRepository.findById(reqZipAgent.getAgentId()).orElseThrow(() -> new ResourceNotFound("User", "id", reqZipAgent.getAgentId()));
        ZipCode zipCode = zipCodeRepository.findById(reqZipAgent.getZipCodeId()).orElseThrow(() -> new ResourceNotFound("ZipCode", "id", reqZipAgent.getZipCodeId()));
        userZipCodeRepository.save(new UserZipCode(user,zipCode));
        return new ApiResponse("success",true);
    }

}
