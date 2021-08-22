package uz.pdp.appduonotarypraktikaserver.controller.team3Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqOutOfService;
import uz.pdp.appduonotarypraktikaserver.service.team3Servıce.OutOfSserviceServices;

import java.util.UUID;

@RestController
@RequestMapping("/api/OutOfService")
public class OutOfServiceController {

    @Autowired
    OutOfSserviceServices outOfSserviceServices;

    @GetMapping
    public HttpEntity<?> getAllOutOfService(@RequestParam Integer page, @RequestParam Integer size){
        ApiResponse apiResponse= outOfSserviceServices.getAllOutOfService(page,size);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping("/postOutOfService")
    public HttpEntity<?> postAndEditOutOfService(@RequestBody ReqOutOfService reqOutOfService){
      ApiResponse apiResponse=  outOfSserviceServices.postAndEditOutOfService(reqOutOfService);
        return ResponseEntity.status(apiResponse.isSuccess() ?apiResponse.getMessage().equals("Saved")?HttpStatus.CREATED: HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/id")
    public HttpEntity<?> deleteOutOfService(@RequestParam UUID id){
        ApiResponse apiResponse= outOfSserviceServices.deleteOutOfService(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(null);

    }

}
