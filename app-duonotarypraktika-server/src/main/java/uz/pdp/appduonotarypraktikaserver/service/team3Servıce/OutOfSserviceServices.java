package uz.pdp.appduonotarypraktikaserver.service.team3Servıce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.OutOfService;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqOutOfService;
import uz.pdp.appduonotarypraktikaserver.repository.OutOfServiceRepository;
import uz.pdp.appduonotarypraktikaserver.repository.ZipCodeRepository;
import uz.pdp.appduonotarypraktikaserver.resModels.ResOutOfService;

import java.util.UUID;

import static uz.pdp.appduonotarypraktikaserver.utils.CommonUtils.validatePageAndSize;

@Service
public class OutOfSserviceServices {

    @Autowired
    OutOfServiceRepository outOfServiceRepository;

    @Autowired
    ZipCodeRepository zipCodeRepository;

    public ApiResponse getAllOutOfService(Integer page,Integer size) {
        validatePageAndSize(page,size);
        Pageable pageable=  PageRequest.of(page-1,10);
        Page<ResOutOfService> all=outOfServiceRepository.getZipCode(pageable);
        return new ApiResponse("success",true,all);
    }


    public ApiResponse postAndEditOutOfService(ReqOutOfService reqOutOfService) {

        try {
            OutOfService outOfService= new OutOfService();

            if (reqOutOfService.getClientEmail()==null){

                outOfService.setWaitingZipCode(zipCodeRepository.findById(reqOutOfService.getWaitingZipCodeId()).orElseThrow( () -> new ResourceNotFound("ZipCode","id",reqOutOfService.getWaitingZipCodeId())));


            }else{
                outOfService.setClientEmail(reqOutOfService.getClientEmail());
                outOfService.setWaitingZipCode(zipCodeRepository.findById(reqOutOfService.getWaitingZipCodeId()).orElseThrow( () -> new ResourceNotFound("ZipCode","id",reqOutOfService.getWaitingZipCodeId())));
            }
             outOfServiceRepository.save(outOfService);
            return new ApiResponse( reqOutOfService.getClientEmail()==null?"OutOFService saved without client email":"OutOfService saved client email", true);

        } catch (ResourceNotFound resourceNotFound) {

            return new ApiResponse("error", false);


        }

    }

    public ApiResponse deleteOutOfService(UUID id) {

        try {
            outOfServiceRepository.deleteById(id);
            return new ApiResponse("successfully",true);
        } catch (Exception e) {
            return new ApiResponse("error",false);
        }
    }
}
