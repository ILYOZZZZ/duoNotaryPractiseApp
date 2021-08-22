package uz.pdp.appduonotarypraktikaserver.service.team2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.Attachment;
import uz.pdp.appduonotarypraktikaserver.entity.Testimonials;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqTestimonials;
import uz.pdp.appduonotarypraktikaserver.repository.AttachmentRepository;
import uz.pdp.appduonotarypraktikaserver.repository.TestimonialsRepository;
import uz.pdp.appduonotarypraktikaserver.utils.CommonUtils;

import java.io.IOException;
import java.util.UUID;

@Service
public class TestimonialsService {
    @Autowired
    TestimonialsRepository testimonialsRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentService attachmentService;

    public ApiResponse saveTestimonials(ReqTestimonials reqTestimonials) throws IOException {
        Attachment attachment = attachmentService.saveAttachment(reqTestimonials.getFile());
        Testimonials save = testimonialsRepository.save(new Testimonials(reqTestimonials.getFirstName() , reqTestimonials.getLastName() , reqTestimonials.getCompanyInfo() , reqTestimonials.getText() ,attachment ));
        return new ApiResponse("success", true);
    }
    public ApiResponse deleteTestimonials(UUID id) {
        //incorrect rep
        testimonialsRepository.deleteById(id);
        return new ApiResponse("success", true);
    }

    public ApiResponse editTestimonials(ReqTestimonials reqTestimonials,UUID id) throws IOException {
        Testimonials testimonials = testimonialsRepository.findById(id).get();
        testimonials.setCompanyInfo(reqTestimonials.getCompanyInfo());
        testimonials.setFirstName(reqTestimonials.getFirstName());
        testimonials.setLastName(reqTestimonials.getLastName());
        testimonials.setText(reqTestimonials.getText());

        if(reqTestimonials.getFile()!=null){
            Attachment attachment = testimonials.getAttachment();
            Attachment attachment1 = attachmentService.saveAttachment(reqTestimonials.getFile());
            testimonials.setAttachment(attachment1);
            testimonialsRepository.save(testimonials);
            attachmentService.deleteAttachment(attachment);
        } else{
            testimonialsRepository.save(testimonials);
        }


        return new ApiResponse("success", true);
    }


    //pageable
    public ApiResponse getAllTestimonials(){
        return new ApiResponse("successfully got" ,true,testimonialsRepository.getTestimonialsRandomly());
    }
}
