package uz.pdp.appduonotarypraktikaserver.controller.team3Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqAdditionalServicePrice;
import uz.pdp.appduonotarypraktikaserver.service.team3Servıce.AdditionalServicePriceService;

import java.util.UUID;

@RestController
@RequestMapping("api/additionalServicePrice")
public class AdditionalServicePriceController {

    @Autowired
    AdditionalServicePriceService additionalServicePriceService;

    @GetMapping
    public HttpEntity<?> getAdditionalServicePrices() {
        ApiResponse apiResponse = additionalServicePriceService.getAdditionalServicePrice();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> saveOrEditAdditionalServicePrice(@RequestBody ReqAdditionalServicePrice reqAdditionalServicePrice){
        ApiResponse apiResponse=additionalServicePriceService.editOrSaveAdditionalServicePrice(reqAdditionalServicePrice);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/changeActive/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        ApiResponse apiResponse=additionalServicePriceService.changeActive(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteAdditionalServicePrice(@PathVariable UUID id){
        ApiResponse apiResponse=additionalServicePriceService.deleteAdditionalServicePrice(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

}
