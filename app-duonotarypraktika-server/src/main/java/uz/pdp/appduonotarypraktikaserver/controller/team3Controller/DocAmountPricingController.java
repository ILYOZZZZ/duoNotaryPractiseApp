package uz.pdp.appduonotarypraktikaserver.controller.team3Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqDocAmountPricing;
import uz.pdp.appduonotarypraktikaserver.service.team3Servıce.DocAmountPricingService;

import java.util.UUID;

@RestController
@RequestMapping("api/docAmountPricing")
public class DocAmountPricingController {

    @Autowired
    DocAmountPricingService docAmountPricingService;

    @GetMapping
    public HttpEntity<?> getDocAmountPricing(@RequestParam Integer page,@RequestParam Integer size){
        ApiResponse apiResponse=docAmountPricingService.getDocAmountPricing(page,size);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> saveDocAmountPricing(@RequestBody ReqDocAmountPricing reqDocAmountPricing){
        ApiResponse apiResponse=docAmountPricingService.saveDocAmountPricing(reqDocAmountPricing);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping
    public HttpEntity<?> editDocAmountPricing(@RequestBody ReqDocAmountPricing reqDocAmountPricing){
        ApiResponse apiResponse=docAmountPricingService.editDocAmountPricing(reqDocAmountPricing);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteDocAmountPricing(@PathVariable UUID id){
        ApiResponse apiResponse=docAmountPricingService.deleteDocAmountPricing(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }
}
