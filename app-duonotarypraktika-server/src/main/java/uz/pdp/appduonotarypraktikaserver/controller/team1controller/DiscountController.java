package uz.pdp.appduonotarypraktikaserver.controller.team1controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.entity.User;
import uz.pdp.appduonotarypraktikaserver.security.CurrentUser;
import uz.pdp.appduonotarypraktikaserver.service.team1service.DiscountService;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqDiscountType;

import java.util.UUID;

@RestController
@RequestMapping("/api/discount")
public class DiscountController {

    @Autowired
    DiscountService discountService; 

    ///   @PreAuthorize("hasAuthority('CREATE_DISCOUNT_TYPE')")
    @PostMapping("/createDiscountType")
    public HttpEntity<?> createDiscountType(@RequestBody ReqDiscountType reqDiscountType, @CurrentUser User user){
        ApiResponse apiResponse = discountService.crateDiscountType(reqDiscountType, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    ///   @PreAuthorize("hasAuthority('GET_DISCOUNT_TYPE')")
    @GetMapping("/getDiscountType")
    public HttpEntity<?> getDiscountType(){
        ApiResponse apiResponse = discountService.getDiscountType();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    ///   @PreAuthorize("hasAuthority('CHANGE_DISCOUNT_TYPE')")
    @PutMapping("/editDiscountType")
    public HttpEntity<?> editDiscountType(@RequestBody ReqDiscountType reqDiscountType){
        ApiResponse apiResponse = discountService.editDiscountType(reqDiscountType);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/deleteDiscountType")
        public HttpEntity<?> deleteDiscountType(@RequestParam UUID discountTypeId){
        ApiResponse apiResponse = discountService.deleteDiscountType(discountTypeId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/changeActive")
    public HttpEntity<?> changeActive(@RequestParam UUID discountTypeId){
        ApiResponse apiResponse = discountService.changeActive(discountTypeId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


}
