package uz.pdp.appduonotarypraktikaserver.service.team3Servıce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.CompanyWorkTime;
import uz.pdp.appduonotarypraktikaserver.entity.MainService;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqMainService;
import uz.pdp.appduonotarypraktikaserver.repository.CompanyWorkTimeRepository;
import uz.pdp.appduonotarypraktikaserver.repository.MainServiceRepository;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MainServiceService {

    @Autowired
    MainServiceRepository mainServiceRepository;
    @Autowired
    CompanyWorkTimeRepository companyWorkTimeRepository;

    public ApiResponse getAll() {
        try {
            List<MainService> all = mainServiceRepository.getByShN();
            return new ApiResponse("success", true, all);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("error", false);
        }
    }

    public ApiResponse saveOrEdit(ReqMainService reqMainService) {
        try {
            MainService mainService = new MainService();
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Time fromTime = new Time(dateFormat.parse(reqMainService.getFromTime()).getTime());
            Time tillTime = new Time(dateFormat.parse(reqMainService.getTillTime()).getTime());

            if (reqMainService.getId() != null) {
                mainService = mainServiceRepository.findById(reqMainService.getId()).orElseThrow(() -> new ResourceNotFound("MainService", "id", reqMainService.getId()));
            }
            mainService.setName(reqMainService.getName());
            mainService.setDescription(reqMainService.getDescription());
            mainService.setOnline(reqMainService.isOnline());
            mainService.setActive(reqMainService.isActive());
            mainService.setFromTime(fromTime);
            mainService.setTillTime(tillTime);
            mainService.setShowNumber(reqMainService.getShowNumber());
            mainServiceRepository.save(mainService);
            return new ApiResponse(reqMainService.getId() != null ? "Edited" : "Saved", true);
        } catch (Exception e) {

            return new ApiResponse("error", false);
        }
    }

    public ApiResponse changeActive(UUID id) {
        try {
            MainService mainService = mainServiceRepository.findById(id).orElseThrow(() -> new ResourceNotFound("MainService", "id",id));
            mainService.setActive(!mainService.getActive());
            mainServiceRepository.save(mainService);
            return new ApiResponse("changed",true,mainService.getActive());
        } catch (ResourceNotFound resourceNotFound) {
            resourceNotFound.printStackTrace();
            return new ApiResponse("couldn't change",false);
        }
    }

    public ApiResponse delete(UUID id) {
        try {
            mainServiceRepository.deleteById(id);
            return new ApiResponse("deleted",true);
        } catch (Exception e) {
            return new ApiResponse("couldn't delete",false);
        }
    }

    public ApiResponse changeOnline(UUID id) {
        try {
            MainService mainService = mainServiceRepository.findById(id).orElseThrow(() -> new ResourceNotFound("MainService", "id",id));
            mainService.setOnline(!mainService.isOnline());
            mainServiceRepository.save(mainService);
            return new ApiResponse("changed",true,mainService.isOnline());
        } catch (ResourceNotFound resourceNotFound) {
            resourceNotFound.printStackTrace();
            return new ApiResponse("couldn't change",false);
        }
    }
}
