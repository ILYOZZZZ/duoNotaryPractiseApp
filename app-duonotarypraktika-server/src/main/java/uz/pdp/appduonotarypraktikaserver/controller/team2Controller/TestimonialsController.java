package uz.pdp.appduonotarypraktikaserver.controller.team2Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqTestimonials;
import uz.pdp.appduonotarypraktikaserver.service.team2Service.TestimonialsService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/testimonials")
public class    TestimonialsController {
    @Autowired
    TestimonialsService testimonialsService;
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping
    public HttpEntity<?> saveTestimonials(@ModelAttribute ReqTestimonials reqTestimonials) throws IOException {
        ApiResponse apiResponse = testimonialsService.saveTestimonials(reqTestimonials);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteTestimonials(@PathVariable UUID id){
        ApiResponse apiResponse = testimonialsService.deleteTestimonials(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/edit/{id}")
    public HttpEntity<?> editTestimonials(@ModelAttribute ReqTestimonials reqTestimonials, @PathVariable UUID id) throws IOException {
        ApiResponse apiResponse = testimonialsService.editTestimonials(reqTestimonials,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
    @GetMapping
    public HttpEntity<?> getAllTestimonials(){
        ApiResponse apiResponse = testimonialsService.getAllTestimonials();
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
