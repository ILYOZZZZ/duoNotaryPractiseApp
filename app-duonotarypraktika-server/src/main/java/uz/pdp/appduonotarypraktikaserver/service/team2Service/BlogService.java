package uz.pdp.appduonotarypraktikaserver.service.team2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.Attachment;
import uz.pdp.appduonotarypraktikaserver.entity.Blog;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqBlog;
import uz.pdp.appduonotarypraktikaserver.repository.BlogRepository;
import uz.pdp.appduonotarypraktikaserver.utils.CommonUtils;

import java.io.IOException;
import java.util.UUID;

@Service
public class BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Autowired
    AttachmentService attachmentService;


    public ApiResponse saveBlog(ReqBlog reqBlog) throws IOException {
        Attachment attachment = attachmentService.saveAttachment(reqBlog.getFile());
        blogRepository.save(new Blog(reqBlog.getTitle(),reqBlog.getDescription(),attachment));
        return new ApiResponse("success", true);
    }


    public ApiResponse deleteBlog(UUID id) {
        Blog blog = blogRepository.findById(id).get();
        attachmentService.deleteAttachment(blog.getAttachment());
        blogRepository.deleteById(id);
        return new ApiResponse("success", true);
    }

    public ApiResponse editBlog(ReqBlog reqBlog,UUID id) throws IOException {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Blog", "id",id));
        blog.setDescription(reqBlog.getDescription());
        blog.setTitle(reqBlog.getTitle());
        attachmentService.deleteAttachment(blog.getAttachment());
        blog.setAttachment(attachmentService.saveAttachment(reqBlog.getFile()));
        blogRepository.save(blog);
        return new ApiResponse("success", true);
    }

    public ApiResponse getAllBlogs(Integer page , Integer size , String title){
        Pageable pageable = CommonUtils.createPageable(page, size);
        return new ApiResponse("succesfully get" , true,blogRepository.getAllBlogs(title,pageable));
    }

    public ApiResponse getBlogById(UUID id){
        return new ApiResponse("successfully getted current blog",true,blogRepository.findById(id).orElseThrow(()->new ResourceNotFound("Blog","id",id)));
    }
}

