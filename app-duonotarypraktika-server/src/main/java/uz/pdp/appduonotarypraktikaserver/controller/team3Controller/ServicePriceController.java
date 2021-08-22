package uz.pdp.appduonotarypraktikaserver.controller.team3Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqServicePrice;
import uz.pdp.appduonotarypraktikaserver.service.team3Servıce.ServicePriceService;

import java.util.UUID;

@RestController
@RequestMapping("/api/servicePrice")
public class ServicePriceController {

    @Autowired
    ServicePriceService servicePriceService;

    @GetMapping("/getPrice")
    public HttpEntity<?> getPrice() {
        ApiResponse price = servicePriceService.getPrice();
        return ResponseEntity.status(price.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(price);
    }

    @PostMapping("/savePrice")
    public HttpEntity<?> postPrice(@RequestBody ReqServicePrice reqServicePrice) {
        ApiResponse apiResponse = servicePriceService.saveOrEditPrice(reqServicePrice);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/changeActive/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        ApiResponse apiResponse = servicePriceService.changeActive(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deletePrice(@PathVariable UUID id) {
        ApiResponse apiResponse = servicePriceService.deletePrice(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
