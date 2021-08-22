package uz.pdp.appduonotarypraktikaserver.controller.team2Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqBlog;
import uz.pdp.appduonotarypraktikaserver.service.team2Service.AttachmentService;
import uz.pdp.appduonotarypraktikaserver.service.team2Service.BlogService;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    BlogService blogService;


    @Autowired
    AttachmentService attachmentService;

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping
    public HttpEntity<?> saveBlog(@ModelAttribute ReqBlog reqBlog) throws IOException {
        ApiResponse apiResponse = blogService.saveBlog(reqBlog);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteBlog(@PathVariable UUID id) {
        ApiResponse apiResponse = blogService.deleteBlog(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @PostMapping("/edit/{id}")
    public HttpEntity<?> editBlog(@RequestBody ReqBlog reqBlog,@PathVariable UUID id) throws IOException {
        ApiResponse apiResponse = blogService.editBlog(reqBlog,id);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }


    @PreAuthorize("hasRole({'ROLE_ADMIN'})")
    @GetMapping
    public HttpEntity<?> getAllBlogs(@RequestParam Integer page,@RequestParam Integer size, @RequestParam String title){
        ApiResponse apiResponse = blogService.getAllBlogs(page, size,title);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
//    //single blog
//    @GetMapping("/blog")
//    public HttpEntity<?> getAllBlogs(@RequestParam UUID id){
//        ApiResponse apiResponse = blogService.getBlogById(id);
//        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
//    }





}
