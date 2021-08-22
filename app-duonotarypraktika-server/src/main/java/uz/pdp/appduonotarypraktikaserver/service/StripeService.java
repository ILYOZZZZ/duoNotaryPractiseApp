package uz.pdp.appduonotarypraktikaserver.service;

import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.repository.OrderRepository;

@Service
public class StripeService {
    //payment service

    @Value("${stripe.keys.secret}")
    private String secretKey;

    @Autowired
    OrderRepository orderRepository;


    public String getClientSecret() {
        Stripe.apiKey = secretKey;

        PaymentIntentCreateParams createParams = new PaymentIntentCreateParams.Builder()
                .setCurrency("usd")
                .setAmount((long) 150 * 100)
                .build();
        try {
            PaymentIntent paymentIntent = PaymentIntent.create(createParams);

            return paymentIntent.getClientSecret();
        } catch (StripeException e) {
            e.printStackTrace();
        }


        return null;
    }

}


