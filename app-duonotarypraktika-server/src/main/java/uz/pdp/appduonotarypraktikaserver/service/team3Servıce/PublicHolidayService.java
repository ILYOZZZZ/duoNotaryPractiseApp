package uz.pdp.appduonotarypraktikaserver.service.team3Servıce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.MainService;
import uz.pdp.appduonotarypraktikaserver.entity.PublicHoliday;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqPublicHoliday;
import uz.pdp.appduonotarypraktikaserver.repository.MainServiceRepository;
import uz.pdp.appduonotarypraktikaserver.repository.PublicHolidayRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PublicHolidayService {
    @Autowired
    PublicHolidayRepository publicHolidayRepository;
    @Autowired
    MainServiceRepository mainServiceRepository;

    public ApiResponse getAll() {
        try {
            List<PublicHoliday> holidays = publicHolidayRepository.getByCA();
            return new ApiResponse("true", true, holidays);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("holidays not found", true);
        }
    }

    public ApiResponse saveOrEdit(ReqPublicHoliday reqPublicHoliday) {
        try {
            PublicHoliday publicHoliday = new PublicHoliday();
            Optional<MainService> optionalMainService = mainServiceRepository.findById(reqPublicHoliday.getMainServiceId());
            if (reqPublicHoliday.getId()!=null){
                Optional<PublicHoliday> optionalPublicHoliday = publicHolidayRepository.findById(reqPublicHoliday.getId());
                if (optionalPublicHoliday.isPresent()){
                    publicHoliday = optionalPublicHoliday.get();
                }
            }
            publicHoliday.setName(reqPublicHoliday.getName());
            publicHoliday.setDescription(reqPublicHoliday.getDescription());
            publicHoliday.setActive(reqPublicHoliday.isActive());
            publicHoliday.setDate(reqPublicHoliday.getDate());
            if (optionalMainService.isPresent()) {
                MainService mainService = optionalMainService.get();
                publicHoliday.setMainService(mainService);
            }
            publicHolidayRepository.save(publicHoliday);
            return new ApiResponse(reqPublicHoliday.getId() != null ? ("edited") : ("saved"), true);

        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("error", false);
        }
    }

    public ApiResponse changeActive(UUID id) {
        try {
            Optional<PublicHoliday> optionalPublicHoliday = publicHolidayRepository.findById(id);
            if (optionalPublicHoliday.isPresent()) {
                PublicHoliday publicHoliday = optionalPublicHoliday.get();
                publicHoliday.setActive(!publicHoliday.getActive());
                publicHolidayRepository.save(publicHoliday);
            } else {
                return new ApiResponse(id + "holiday with such id doesn't exist", false);
            }
            return new ApiResponse("changed", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("error", false);
        }
    }

    public ApiResponse delete(UUID id) {
        try {
            publicHolidayRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse("error", false);
        }
    }
}
