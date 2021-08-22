package uz.pdp.appduonotarypraktikaserver.service.team3Servıce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.Services;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqServices;
import uz.pdp.appduonotarypraktikaserver.repository.MainServiceRepository;
import uz.pdp.appduonotarypraktikaserver.repository.ServiceRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ServiceServices {

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    MainServiceRepository mainServiceRepository;

    public ApiResponse getServices() {

        List<Services> all = serviceRepository.getByCA();
        return new ApiResponse("success",true,all);

    }

    public ApiResponse saveOrEditServices(ReqServices reqServices) {

        try {
            Services services= new Services();
            if (reqServices.getId()!=null){
                 services = serviceRepository.findById(reqServices.getId()).orElseThrow(() -> new ResourceNotFound("Services", "id", reqServices.getId()));
            }

            services.setName(reqServices.getName());
            services.setDescription(reqServices.getDescription());
            services.setActive(reqServices.isActive());
            services.setInitialCount(reqServices.getInitialCount());
            services.setInitialSpendingTime(reqServices.getInitialSpendingTime());
            services.setEveryCount(reqServices.getEveryCount());
            services.setEverySpendingTime(reqServices.getEverySpendingTime());
            services.setMainService(mainServiceRepository.findById(reqServices.getMainServiceId()).orElseThrow( () -> new ResourceNotFound("MainService","id",reqServices.getMainServiceId())));
            Services save = serviceRepository.save(services);
            return new ApiResponse(reqServices.getId() != null ? "Edited" : "Saved", true,save);

        } catch (ResourceNotFound resourceNotFound) {

            return new ApiResponse("error", false);

        }


    }

    public ApiResponse changeActive(UUID id) {

        Services services = serviceRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Services", "id", id));
        if(!services.getActive()){
            services.setActive(true);
        }else{
            services.setActive(false);
        }
        serviceRepository.save(services);
        return new ApiResponse("successfully",true,services.getActive());

    }

    public ApiResponse deleteMainService(UUID id) {

        try {
            serviceRepository.deleteById(id);
            return new ApiResponse("successfully",true);
        } catch (Exception e) {
            return new ApiResponse("error",false);
        }
    }
}
