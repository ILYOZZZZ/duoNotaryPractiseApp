package uz.pdp.appduonotarypraktikaserver.service.team3Servıce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.AdditionalService;
import uz.pdp.appduonotarypraktikaserver.entity.MainService;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqAdditionalService;
import uz.pdp.appduonotarypraktikaserver.repository.AdditionalServiceRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AdditionalServiceService {

    @Autowired
    AdditionalServiceRepository additionalServiceRepository;


    public ApiResponse getAdditionalService() {
        List<AdditionalService> all = additionalServiceRepository.findAll();
        return new ApiResponse("success", true, all);
    }


    public ApiResponse editAdditionalService(ReqAdditionalService reqAdditionalService) {
        AdditionalService additionalService = additionalServiceRepository.findById(reqAdditionalService.getId()).orElseThrow(() -> new ResourceNotFound("AdditionalService", "id", reqAdditionalService.getId()));
        additionalService.setName(reqAdditionalService.getName());
        additionalService.setActive(reqAdditionalService.getActive());
        additionalService.setDescription(reqAdditionalService.getDescription());
        additionalServiceRepository.save(additionalService);
        return new ApiResponse(reqAdditionalService.getId() != null ? "edited" : "saved", true);
    }

    public ApiResponse saveAdditionalService(ReqAdditionalService reqAdditionalService) {
        AdditionalService additionalService = new AdditionalService();
        additionalService.setName(reqAdditionalService.getName());
        additionalService.setDescription(reqAdditionalService.getDescription());
        additionalService.setActive(reqAdditionalService.getActive());
        additionalServiceRepository.save(additionalService);
        return new ApiResponse(reqAdditionalService.getId() != null ? "edited" : "saved", true);
    }


    public ApiResponse deleteAdditionService(ReqAdditionalService reqAdditionalService) {
        try {
            additionalServiceRepository.deleteById(reqAdditionalService.getId());
            return new ApiResponse("deleted", true);
        } catch (Exception e) {
            return new ApiResponse("error", false);
        }
    }

    public ApiResponse changeActive(ReqAdditionalService reqAdditionalService) {
        AdditionalService additionalService = additionalServiceRepository.findById(reqAdditionalService.getId()).orElseThrow(() -> new ResourceNotFound("AdditionalService", "id", reqAdditionalService.getId()));
        if (!additionalService.getActive()) {
            additionalService.setActive(true);
        } else {
            additionalService.setActive(false);
        }
        additionalServiceRepository.save(additionalService);
        return new ApiResponse("successfully", true, additionalService.getActive());
    }
}
