package uz.pdp.appduonotarypraktikaserver.controller.team2Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqState;
import uz.pdp.appduonotarypraktikaserver.service.team2Service.StateService;

import java.util.UUID;

@RestController
@RequestMapping("api/state")
public class StateController {

    @Autowired
    StateService stateService;

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping()
    public HttpEntity<?> getAllState(@RequestParam Integer page, @RequestParam Integer size , @RequestParam String stateName) {
        ApiResponse apiResponse = stateService.getAllState(page, size , stateName);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping("/state")
    public HttpEntity<?> getOneState(@RequestParam UUID id){
        ApiResponse apiResponse = stateService.getStateById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping
    public HttpEntity<?> saveState(@RequestBody ReqState reqState) {
        ApiResponse apiResponse = stateService.saveState(reqState);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/edit/{id}")
    public HttpEntity<?> editState(@RequestBody ReqState reqState , @PathVariable UUID id) {
        ApiResponse apiResponse = stateService.editState(reqState,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteState(@PathVariable UUID id) {
        ApiResponse apiResponse = stateService.deleteState(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/edit/active/{id}")
    public HttpEntity<?> editStateActive(@PathVariable UUID id){
        ApiResponse apiResponse = stateService.editStateActive(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
