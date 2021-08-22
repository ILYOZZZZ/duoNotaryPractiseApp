package uz.pdp.appduonotarypraktikaserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appduonotarypraktikaserver.service.DocVerifyService;

@RestController
@RequestMapping("/api/docverify")
public class DocVerifyController {

    @Autowired
    DocVerifyService docVerifyService;

    @GetMapping
   public HttpEntity<?> getDoc(@RequestParam(value = "orderId",defaultValue = "null") String orderId,@RequestParam boolean download,@RequestParam (value = "docId",defaultValue = "all") String docId){
        if (orderId.equals("all")) {
            return ResponseEntity.ok(docVerifyService.getPacket(docId));
        }
        return docVerifyService.getDoc(download,orderId,docId);
    }


}


