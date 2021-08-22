package uz.pdp.appduonotarypraktikaserver.controller.team2Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqCountry;
import uz.pdp.appduonotarypraktikaserver.payload.ReqType;
import uz.pdp.appduonotarypraktikaserver.service.team2Service.CountryService;

import java.util.UUID;

@RestController
@RequestMapping("api/country")
public class CountryController {

    @Autowired
    CountryService countryService;

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping
    public HttpEntity<?> getAllCountry(@RequestParam Integer page, @RequestParam Integer size , @RequestParam String name) {
        ApiResponse apiResponse = countryService.getAllCountry(page, size,name);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping("/current")
    public HttpEntity<?> getCurrentCountry(@RequestParam UUID id) {
        ApiResponse apiResponse = countryService.getCurrentCountry(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping
    public HttpEntity<?> saveCountry(@RequestBody ReqCountry reqCountry) {
        ApiResponse apiResponse = countryService.saveCountry(reqCountry);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteCountry(@PathVariable UUID id) {
        ApiResponse apiResponse = countryService.deleteCountry(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/edit/{id}")
    public HttpEntity<?> editCountry(@RequestBody ReqCountry reqCountry,@PathVariable UUID id) {
        ApiResponse apiResponse = countryService.editCountry(reqCountry,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/edit/active/{id}")
    public HttpEntity<?> editCountryEmbassyOrActive(@RequestBody ReqType reqType,@PathVariable UUID id){
        ApiResponse apiResponse = countryService.editCountryActiveOrEmbassy(reqType.getType(), id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

}
