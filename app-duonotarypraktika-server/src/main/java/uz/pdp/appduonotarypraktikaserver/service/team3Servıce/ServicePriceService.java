package uz.pdp.appduonotarypraktikaserver.service.team3Servıce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.ServicePrice;
import uz.pdp.appduonotarypraktikaserver.entity.Services;
import uz.pdp.appduonotarypraktikaserver.entity.ZipCode;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqServicePrice;
import uz.pdp.appduonotarypraktikaserver.repository.ServicePriceRepository;
import uz.pdp.appduonotarypraktikaserver.repository.ServiceRepository;
import uz.pdp.appduonotarypraktikaserver.repository.ZipCodeRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ServicePriceService {

    @Autowired
    ServicePriceRepository servicePriceRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    ZipCodeRepository zipCodeRepository;


    public ApiResponse getPrice() {

        List<ServicePrice> allPrice = servicePriceRepository.findAll();
        return new ApiResponse("Ok", true, allPrice);
    }

    public ApiResponse saveOrEditPrice(ReqServicePrice reqServicePrice) {
        try {
            ServicePrice servicePrice;
            if (reqServicePrice.getId() != null) {
                servicePrice = servicePriceRepository.findById(reqServicePrice.getId()).orElseThrow(() -> new ResourceNotFound("servicePrice", "id", reqServicePrice.getId()));
                servicePrice.setPrice(reqServicePrice.getPrice());
                servicePrice.setChargeMinute(reqServicePrice.getChargeMinute());
                servicePrice.setChargePercent(reqServicePrice.getChargePercent());
                servicePrice.setActive(reqServicePrice.isActive());
                servicePrice.setService(serviceRepository.findById(reqServicePrice.getServiceId()).orElseThrow(() -> new ResourceNotFound("Service","Id",reqServicePrice.getServiceId())));
                servicePriceRepository.save(servicePrice);
            } else {
                List<ZipCode> zipCodeList = zipCodeRepository.findAllById(reqServicePrice.getZipCodeIds());
                Services services = serviceRepository.findById(reqServicePrice.getServiceId()).orElseThrow(() -> new ResourceNotFound("Service", "Id", reqServicePrice.getServiceId()));
                for (ZipCode zipCode : zipCodeList) {
                    servicePrice = new ServicePrice();
                    servicePrice.setPrice(reqServicePrice.getPrice());
                    servicePrice.setChargeMinute(reqServicePrice.getChargeMinute());
                    servicePrice.setChargePercent(reqServicePrice.getChargePercent());
                    servicePrice.setActive(reqServicePrice.isActive());
                    servicePrice.setZipCode(zipCode);
                    servicePrice.setService(services);
                    servicePriceRepository.save(servicePrice);
                }
            }
            return new ApiResponse(reqServicePrice.getId() != null ? "Edited" : "Saved", true);
        } catch (ResourceNotFound resourceNotFound) {
            return new ApiResponse("error", false);
        }
    }

    public ApiResponse changeActive(UUID id) {
        ServicePrice servicePrice = servicePriceRepository.findById(id).orElseThrow(() -> new ResourceNotFound("ServicePrice", "Id", id));
        servicePrice.setActive(!servicePrice.isActive());
        servicePriceRepository.save(servicePrice);
        return new ApiResponse(servicePrice.isActive() ? "Activated" : "Blocked", true);
    }

    public ApiResponse deletePrice(UUID id) {
        try {
            servicePriceRepository.deleteById(id);
            return new ApiResponse("ok", true);
        } catch (Exception e) {
            return new ApiResponse("error", false);

        }

    }


}
