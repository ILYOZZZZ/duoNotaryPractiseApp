package uz.pdp.appduonotarypraktikaserver.controller.team3Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqServices;
import uz.pdp.appduonotarypraktikaserver.service.team3Servıce.ServiceServices;

import java.util.UUID;

@RestController
@RequestMapping("api/Services")
public class ServiceController {
    @Autowired
    ServiceServices serviceServices;


    @GetMapping
    public HttpEntity<?> getServices() {
        ApiResponse apiResponse = serviceServices.getServices();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    @PostMapping
    public HttpEntity<?> saveOrEditServices(@RequestBody ReqServices reqServices){
        ApiResponse apiResponse=serviceServices.saveOrEditServices(reqServices);
        return ResponseEntity.status(apiResponse.isSuccess() ?apiResponse.getMessage().equals("Saved")?HttpStatus.CREATED: HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        ApiResponse apiResponse= serviceServices.changeActive(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteMainService(@PathVariable UUID id){
        ApiResponse apiResponse= serviceServices.deleteMainService(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(null);
    }

}
