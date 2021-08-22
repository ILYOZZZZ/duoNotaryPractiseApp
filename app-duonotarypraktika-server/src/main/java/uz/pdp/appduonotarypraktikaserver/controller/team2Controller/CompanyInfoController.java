package uz.pdp.appduonotarypraktikaserver.controller.team2Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqCompanyInfo;
import uz.pdp.appduonotarypraktikaserver.service.team2Service.CompanyInfoService;

@RestController
@RequestMapping("api/companyInfo")
public class CompanyInfoController {
    
    @Autowired
    CompanyInfoService companyInfoService;

//    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping
    public HttpEntity<?> getAllCompanyInfo(@RequestParam Integer page , @RequestParam Integer size){
        ApiResponse apiResponse = companyInfoService.getAllCompany(page, size);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping
    public HttpEntity<?> saveCompanyInfo(@RequestBody ReqCompanyInfo reqCompanyInfo){
        ApiResponse apiResponse = companyInfoService.saveCompanyInfo(reqCompanyInfo);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/edit")
    public HttpEntity<?> editCompanyInfo(@RequestBody ReqCompanyInfo reqCompanyInfo){
        ApiResponse apiResponse = companyInfoService.editCompanyInfo(reqCompanyInfo);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @DeleteMapping
    public HttpEntity<?> deleteCompanyInfo(@RequestBody ReqCompanyInfo reqCompanyInfo){
        ApiResponse apiResponse = companyInfoService.deleteCompanyInfo(reqCompanyInfo.getId());
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);

    }



}
