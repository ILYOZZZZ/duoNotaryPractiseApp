package uz.pdp.appduonotarypraktikaserver.controller.team2Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sun.awt.IconInfo;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqZipAgent;
import uz.pdp.appduonotarypraktikaserver.payload.ReqZipCode;
import uz.pdp.appduonotarypraktikaserver.service.team2Service.ZipCodeService;

import java.util.UUID;

@RestController
@RequestMapping("api/zip")
public class ZipCodeController {
    @Autowired
    ZipCodeService zipCodeService;

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping
    public HttpEntity<?> saveZipCode(@RequestBody ReqZipCode reqZipCode){
        ApiResponse apiResponse = zipCodeService.saveZipCode(reqZipCode);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping
    public HttpEntity<?> getAllZipCodes(@RequestParam Integer page , @RequestParam Integer size , @RequestParam String name){
        ApiResponse apiResponse = zipCodeService.getAllZipCode(page, size,name);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping("{id}")
    public HttpEntity<?>  getZipCodeById(@PathVariable UUID id){
        ApiResponse apiResponse = zipCodeService.getZipCodeById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping("/state")
    public HttpEntity<?> getStatesToEditZip(@RequestParam UUID stateId , @RequestParam UUID zipCodeId , @RequestParam String stateName ){
        ApiResponse apiResponse = zipCodeService.getCurrentState(stateId,zipCodeId,stateName);


        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping("/county")
    public HttpEntity<?> getZipsOfCounty(@RequestParam UUID countyId){
        ApiResponse apiResponse = zipCodeService.getZipsOfCounty(countyId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping("/agent")
    public HttpEntity<?> getAgentsToLinkZipCode(@RequestParam UUID stateId , @RequestParam UUID zipCodeId){
        ApiResponse apiResponse = zipCodeService.getAgentsToLinkZipCode(stateId, zipCodeId);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }



    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteZipCode(@PathVariable UUID id){
        ApiResponse apiResponse = zipCodeService.deleteZipCode(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PutMapping("{id}")
    public HttpEntity<?> editZipCode(@RequestBody ReqZipCode reqZipCode , @PathVariable UUID id){
        ApiResponse apiResponse = zipCodeService.editZipCode(reqZipCode,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @DeleteMapping("/user/{id}")
    public HttpEntity<?> removeAgent(@PathVariable UUID id){
        ApiResponse apiResponse = zipCodeService.deleteAgentFromZipCode(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/agent/save")
    public HttpEntity<?> saveAgentToZip(@RequestBody ReqZipAgent reqZipAgent){
        ApiResponse apiResponse = zipCodeService.saveAgentToZip(reqZipAgent);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }



}
