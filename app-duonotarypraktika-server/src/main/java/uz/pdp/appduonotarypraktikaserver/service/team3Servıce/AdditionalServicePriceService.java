package uz.pdp.appduonotarypraktikaserver.service.team3Servıce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.AdditionalService;
import uz.pdp.appduonotarypraktikaserver.entity.AdditionalServicePrice;
import uz.pdp.appduonotarypraktikaserver.entity.Services;
import uz.pdp.appduonotarypraktikaserver.entity.ZipCode;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqAdditionalServicePrice;
import uz.pdp.appduonotarypraktikaserver.repository.AdditionalServicePriceRepository;
import uz.pdp.appduonotarypraktikaserver.repository.AdditionalServiceRepository;
import uz.pdp.appduonotarypraktikaserver.repository.ServiceRepository;
import uz.pdp.appduonotarypraktikaserver.repository.ZipCodeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AdditionalServicePriceService {

    @Autowired
    AdditionalServicePriceRepository additionalServicePriceRepository;

    @Autowired
    ZipCodeRepository zipCodeRepository;

    @Autowired
    AdditionalServiceRepository additionalServiceRepository;

    @Autowired
    ServiceRepository serviceRepository;

    public ApiResponse getAdditionalServicePrice() {
        try {
            List<AdditionalServicePrice> all = additionalServicePriceRepository.findAll();
            return new ApiResponse("success", true, all);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("error", false);
        }
    }

    public ApiResponse editOrSaveAdditionalServicePrice(ReqAdditionalServicePrice reqAdditionalServicePrice) {

        try {
            if (reqAdditionalServicePrice.getId() != null) {
                AdditionalServicePrice additionalServicePrice = additionalServicePriceRepository.findById(reqAdditionalServicePrice.getId()).orElseThrow(() -> new ResourceNotFound("AdditionalServicePrice", "id", reqAdditionalServicePrice.getId()));
                additionalServicePrice.setPrice(reqAdditionalServicePrice.getPrice());
                additionalServicePrice.setActive(reqAdditionalServicePrice.isActive());
                additionalServicePriceRepository.save(additionalServicePrice);
            } else {
                List<ZipCode> zipCodes = zipCodeRepository.findAllById(reqAdditionalServicePrice.getZipCodesId());
                AdditionalService additionalService = additionalServiceRepository.findById(reqAdditionalServicePrice.getAdditionalServiceId()).orElseThrow(() -> new ResourceNotFound("AdditionalService", "id", reqAdditionalServicePrice.getAdditionalServiceId()));
                Services services = serviceRepository.findById(reqAdditionalServicePrice.getServiceId()).orElseThrow(() -> new ResourceNotFound("Service", "id", reqAdditionalServicePrice.getServiceId()));

                for (ZipCode zipCode : zipCodes) {
                    AdditionalServicePrice additionalServicePrice = new AdditionalServicePrice();
                    additionalServicePrice.setZipCode(zipCode);
                    additionalServicePrice.setPrice(reqAdditionalServicePrice.getPrice());
                    additionalServicePrice.setAdditionalService(additionalService);
                    additionalServicePrice.setService(services);
                    additionalServicePrice.setActive(reqAdditionalServicePrice.isActive());
                    additionalServicePriceRepository.save(additionalServicePrice);
                }
            }
            return new ApiResponse(reqAdditionalServicePrice.getId() != null ? "Edited" : "Saved", true);
        } catch (ResourceNotFound resourceNotFound) {

            return new ApiResponse("error", false);
        }
    }

    public ApiResponse changeActive(UUID id) {
        AdditionalServicePrice additionalServicePrice = additionalServicePriceRepository.findById(id).orElseThrow(() -> new ResourceNotFound("AdditionalServicePrice", "id", id));
        additionalServicePrice.setActive(!additionalServicePrice.isActive());
        additionalServicePriceRepository.save(additionalServicePrice);
        return new ApiResponse(additionalServicePrice.isActive() ? "activated" : "blocked", true, additionalServicePrice.isActive());
    }

    public ApiResponse deleteAdditionalServicePrice(UUID id) {
        try {
            additionalServicePriceRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("error", false);

        }
    }

}
