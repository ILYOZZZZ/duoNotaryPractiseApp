package uz.pdp.appduonotarypraktikaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.service.StripeService;


@RestController
@RequestMapping("api/stripe")
public class StripeController {

    @Autowired
    StripeService stripeService;


    @PostMapping
    public String getClientSecret() {
        return stripeService.getClientSecret();
    }
}
