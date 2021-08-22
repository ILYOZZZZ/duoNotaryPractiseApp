package uz.pdp.appduonotarypraktikaserver.controller.team2Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqCounty;
import uz.pdp.appduonotarypraktikaserver.service.team2Service.CountyService;

import java.util.UUID;

@RestController
@RequestMapping("api/county")
public class CountyController {
    @Autowired
    CountyService countyService;

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping
    public HttpEntity<?> getAllCounties(@RequestParam Integer page , @RequestParam Integer size , @RequestParam UUID stateId , @RequestParam String countyName ){
        ApiResponse apiResponse = countyService.getAll(page, size,stateId,countyName);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping("/current")
    public HttpEntity<?> getCurrentCounty(@RequestParam UUID id){
        ApiResponse apiResponse = countyService.getCurrent(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping
    public HttpEntity<?> saveCounty(@RequestBody ReqCounty reqCounty){
        ApiResponse apiResponse = countyService.saveCounty(reqCounty);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/edit/{id}")
    public HttpEntity<?> editCounty(@RequestBody ReqCounty reqCounty,@PathVariable UUID id){
        ApiResponse apiResponse = countyService.editCounty(reqCounty,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/edit/active/{id}")
    public HttpEntity<?> editCountyActive(@PathVariable UUID id){
        ApiResponse apiResponse = countyService.editCountyActive(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteCounty(@PathVariable UUID id){
        ApiResponse apiResponse = countyService.deleteById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }




}
