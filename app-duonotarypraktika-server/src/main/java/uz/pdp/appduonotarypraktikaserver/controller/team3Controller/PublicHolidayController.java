package uz.pdp.appduonotarypraktikaserver.controller.team3Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqPublicHoliday;
import uz.pdp.appduonotarypraktikaserver.service.team3Servıce.PublicHolidayService;

import java.util.UUID;

@RestController
@RequestMapping("api/publicHoliday")
public class PublicHolidayController {
    @Autowired
    PublicHolidayService publicHolidayService;

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse apiResponse = publicHolidayService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess()?(HttpStatus.OK):(HttpStatus.CONFLICT)).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> saveOrEdit(ReqPublicHoliday reqPublicHoliday){
        ApiResponse apiResponse = publicHolidayService.saveOrEdit(reqPublicHoliday);
        return ResponseEntity.status(apiResponse.isSuccess()?(HttpStatus.OK):(HttpStatus.CONFLICT)).body(apiResponse);
    }

    @PutMapping("/active/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        ApiResponse apiResponse = publicHolidayService.changeActive(id);
        return ResponseEntity.status(apiResponse.isSuccess()?(HttpStatus.OK):(HttpStatus.CONFLICT)).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> delete(@PathVariable UUID id){
        ApiResponse apiResponse = publicHolidayService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?(HttpStatus.OK):(HttpStatus.CONFLICT)).body(apiResponse);
    }

}
