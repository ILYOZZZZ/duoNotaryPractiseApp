package uz.pdp.appduonotarypraktikaserver.controller.team3Controller;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqAdditionalService;
import uz.pdp.appduonotarypraktikaserver.service.team3Servıce.AdditionalServiceService;

import java.util.UUID;

@RestController
@RequestMapping("api/additionalService")
public class AdditionalServiceController {

    @Autowired
    AdditionalServiceService additionalServiceService;

    @GetMapping
    public HttpEntity<?> getAdditionalService(){
        ApiResponse apiResponse=additionalServiceService.getAdditionalService();
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> saveAdditionalService(@RequestBody ReqAdditionalService reqAdditionalService){
        ApiResponse apiResponse=additionalServiceService.saveAdditionalService(reqAdditionalService);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping
    public HttpEntity<?> editAdditionalService(@RequestBody ReqAdditionalService reqAdditionalService){
        ApiResponse apiResponse = additionalServiceService.editAdditionalService(reqAdditionalService);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/editActive")
    public HttpEntity<?> editActive(@RequestBody ReqAdditionalService reqAdditionalService){
        ApiResponse apiResponse = additionalServiceService.changeActive(reqAdditionalService);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping()
    public HttpEntity<?> deleteAdditionService(@RequestBody ReqAdditionalService reqAdditionalService){
        ApiResponse apiResponse = additionalServiceService.deleteAdditionService(reqAdditionalService);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

}
