package uz.pdp.appduonotarypraktikaserver.service.team3Servıce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.CompanyWorkTime;
import uz.pdp.appduonotarypraktikaserver.entity.MainService;
import uz.pdp.appduonotarypraktikaserver.entity.ZipCode;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqCompanyWorkTime;
import uz.pdp.appduonotarypraktikaserver.repository.CompanyWorkTimeRepository;
import uz.pdp.appduonotarypraktikaserver.repository.MainServiceRepository;
import uz.pdp.appduonotarypraktikaserver.repository.ZipCodeRepository;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyWorkTimeService {
    @Autowired
    CompanyWorkTimeRepository companyWorkTimeRepository;
    @Autowired
    ZipCodeRepository zipCodeRepository;
    @Autowired
    MainServiceRepository mainServiceRepository;

    public ApiResponse getAllMSWorkTimes() {
        List<CompanyWorkTime> allWorkTimes = companyWorkTimeRepository.getByCA();
        return new ApiResponse("success", true, allWorkTimes);
    }

    public ApiResponse saveOrEdit(ReqCompanyWorkTime reqCompanyWorkTime) {
        try {
            CompanyWorkTime companyWorkTime = new CompanyWorkTime();
            DateFormat dateFormat = new SimpleDateFormat("hh:mm");
            Time fromTime = new Time(dateFormat.parse(reqCompanyWorkTime.getFromTime()).getTime());
            Time tillTime = new Time(dateFormat.parse(reqCompanyWorkTime.getTillTime()).getTime());
            if (reqCompanyWorkTime.getId()!=null){
                Optional<CompanyWorkTime> optionalCompanyWorkTime = companyWorkTimeRepository.findById(reqCompanyWorkTime.getId());
                if (optionalCompanyWorkTime.isPresent()){
                    companyWorkTime = optionalCompanyWorkTime.get();
                }
            }
            Optional<MainService> optionalMainService = mainServiceRepository.findById(reqCompanyWorkTime.getMainServiceId());
            if (optionalMainService.isPresent()) {
                MainService mainService = optionalMainService.get();
                companyWorkTime.setFromTime(fromTime);
                companyWorkTime.setTillTime(tillTime);
                companyWorkTime.setChargePercent(reqCompanyWorkTime.getChargePercent());
                companyWorkTime.setMainService(mainService);
                companyWorkTime.setActive(reqCompanyWorkTime.isActive());
                companyWorkTimeRepository.save(companyWorkTime);
            } else {
                return new ApiResponse("couldn't find such main service", false,reqCompanyWorkTime.getMainServiceId());
            }
            return new ApiResponse("saved", true, companyWorkTime);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("error", false);
        }
    }
    public ApiResponse changeActive(UUID id){
        try {
            Optional<CompanyWorkTime> optionalCompanyWorkTime = companyWorkTimeRepository.findById(id);
            if (optionalCompanyWorkTime.isPresent()){
                CompanyWorkTime companyWorkTime = optionalCompanyWorkTime.get();
                companyWorkTime.setActive(!companyWorkTime.isActive());
                companyWorkTimeRepository.save(companyWorkTime);
            }
            return new ApiResponse("changed",true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("error",false);
        }
    }

    public ApiResponse deleteMSWorkTime(UUID id) {
        try {
            companyWorkTimeRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        } catch (Exception e) {
            return new ApiResponse("error", false);
        }
    }
}
