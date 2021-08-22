package uz.pdp.appduonotarypraktikaserver.controller.team3Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.entity.ApostilleDocType;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqMainService;
import uz.pdp.appduonotarypraktikaserver.service.team3Servıce.MainServiceService;

import java.util.UUID;

@RestController
@RequestMapping("api/mainServices")
public class MainServiceController {
    @Autowired
    MainServiceService mainServiceService;

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse apiResponse= mainServiceService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PostMapping
    public HttpEntity<?> saveOrEdit(@RequestBody ReqMainService reqMainService){
        ApiResponse apiResponse = mainServiceService.saveOrEdit(reqMainService);
        return ResponseEntity.status(apiResponse.isSuccess() ?apiResponse.getMessage().equals("Saved")?HttpStatus.CREATED: HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/active/{id}")
    public HttpEntity<?> changeActive(@PathVariable UUID id){
        ApiResponse apiResponse= mainServiceService.changeActive(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PutMapping("/online/{id}")
    public HttpEntity<?> changeOnline(@PathVariable UUID id){
        ApiResponse apiResponse = mainServiceService.changeOnline(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> delete(@PathVariable UUID id){
        ApiResponse apiResponse= mainServiceService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
