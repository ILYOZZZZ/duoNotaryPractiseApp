package uz.pdp.appduonotarypraktikaserver.service.team3Servıce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.DocAmountPricing;
import uz.pdp.appduonotarypraktikaserver.entity.ServicePrice;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqDocAmountPricing;
import uz.pdp.appduonotarypraktikaserver.repository.DocAmountPricingRepository;
import uz.pdp.appduonotarypraktikaserver.repository.ServicePriceRepository;

import java.util.UUID;

@Service
public class DocAmountPricingService {

    @Autowired
    DocAmountPricingRepository docAmountPricingRepository;

    @Autowired
    ServicePriceRepository servicePriceRepository;

    public ApiResponse getDocAmountPricing(Integer page, Integer size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size);
            Page<DocAmountPricing> all = docAmountPricingRepository.findAll(pageable);
            return new ApiResponse("success", true, all);
        } catch (Exception e) {
            return new ApiResponse("error", false);
        }
    }

    public ApiResponse saveDocAmountPricing(ReqDocAmountPricing reqDocAmountPricing) {
        DocAmountPricing save = new DocAmountPricing();
        ServicePrice servicePrice = servicePriceRepository.findById(reqDocAmountPricing.getServicePriceId()).orElseThrow(() -> new ResourceNotFound("ServicePrice", "id", reqDocAmountPricing.getServicePriceId()));
        save.setPrice(reqDocAmountPricing.getPrice());
        save.setFromCount(reqDocAmountPricing.getFromCount());
        save.setTillCount(reqDocAmountPricing.getTillCount());
        save.setEveryCount(reqDocAmountPricing.getEveryCount());
        save.setService(servicePrice.getService());
        docAmountPricingRepository.save(save);
        return new ApiResponse(reqDocAmountPricing.getId() != null ? "Edited" : "Saved", true);
    }

    public ApiResponse editDocAmountPricing(ReqDocAmountPricing reqDocAmountPricing) {
        DocAmountPricing save = new DocAmountPricing();
        ServicePrice servicePrice = servicePriceRepository.findById(reqDocAmountPricing.getServicePriceId()).orElseThrow(() -> new ResourceNotFound("ServicePrice", "id", reqDocAmountPricing.getServicePriceId()));
        save = docAmountPricingRepository.findById(reqDocAmountPricing.getId()).orElseThrow(() -> new ResourceNotFound("DocAmountPricing", "id", reqDocAmountPricing.getId()));
        save.setPrice(reqDocAmountPricing.getPrice());
        save.setFromCount(reqDocAmountPricing.getFromCount());
        save.setTillCount(reqDocAmountPricing.getTillCount());
        save.setEveryCount(reqDocAmountPricing.getEveryCount());
        save.setService(servicePrice.getService());
        docAmountPricingRepository.save(save);
        return new ApiResponse(reqDocAmountPricing.getId() != null ? "Edited" : "Saved", true);
    }

    public ApiResponse deleteDocAmountPricing(UUID id) {
        try {
            docAmountPricingRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        } catch (Exception e) {
            return new ApiResponse("error", false);
        }
    }
}
