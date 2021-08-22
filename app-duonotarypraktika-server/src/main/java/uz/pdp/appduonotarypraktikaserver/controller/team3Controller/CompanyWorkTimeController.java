package uz.pdp.appduonotarypraktikaserver.controller.team3Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.entity.CompanyWorkTime;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqCompanyWorkTime;
import uz.pdp.appduonotarypraktikaserver.repository.CompanyWorkTimeRepository;
import uz.pdp.appduonotarypraktikaserver.repository.ZipCodeRepository;
import uz.pdp.appduonotarypraktikaserver.service.team3Servıce.CompanyWorkTimeService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/companyWorkTime")
public class CompanyWorkTimeController {
    @Autowired
    CompanyWorkTimeService companyWorkTimeService;

    @GetMapping
    public HttpEntity<?> getAll(){
        ApiResponse apiResponse= companyWorkTimeService.getAllMSWorkTimes();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> saveOrEdit(@RequestBody ReqCompanyWorkTime reqCompanyWorkTime){
        ApiResponse apiResponse= companyWorkTimeService.saveOrEdit(reqCompanyWorkTime);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("active/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        ApiResponse apiResponse = companyWorkTimeService.changeActive(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteMSWorkTime(@PathVariable UUID id){
        ApiResponse apiResponse= companyWorkTimeService.deleteMSWorkTime(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
