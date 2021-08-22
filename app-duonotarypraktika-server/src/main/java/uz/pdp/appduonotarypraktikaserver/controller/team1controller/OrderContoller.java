package uz.pdp.appduonotarypraktikaserver.controller.team1controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.entity.ServicePrice;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponseModel;
import uz.pdp.appduonotarypraktikaserver.repository.ServicePriceRepository;
import uz.pdp.appduonotarypraktikaserver.resModels.team1.CheckedTime;
import uz.pdp.appduonotarypraktikaserver.service.team1service.OrderService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderContoller {


    @Autowired
    OrderService orderService;

    @Autowired
    ServicePriceRepository servicePriceRepository;

    @GetMapping("/getServicePriceByZipCode")
    public ApiResponseModel getServicePriceByZipCode(@RequestParam String zipCode){
       return orderService.getServicePriceByZipCode(zipCode);
    }

    @GetMapping("/getAdditionalServicePriceByServicePrice")
    public ApiResponseModel getAdditionalServicePriceByServicePrice(@RequestParam UUID servicePriceId){
        return orderService.getAdditionalServicePriceByServicePrice(servicePriceId);
    }

    @GetMapping("/getDocAmountPricingByService")
    public ApiResponseModel getDocAmountPricingByService(@RequestParam UUID servicePriceId,@RequestParam int docAmount){
        return orderService.getDocAmountPricingByService(servicePriceId,docAmount);
    }

    @GetMapping("/getTimes")
    public ApiResponseModel getDocAmountPricingByService(@RequestParam String date, @RequestParam UUID servicePriceId, @RequestParam int docAmount){
        ServicePrice one = servicePriceRepository.getOne(servicePriceId);
        LocalDate parse = LocalDate.parse(date);
        List<CheckedTime> availableTimes = orderService.getAvailableTimes(parse, one, docAmount);
        return new ApiResponseModel(true,"Avalaible times to order",availableTimes);
    }

}
