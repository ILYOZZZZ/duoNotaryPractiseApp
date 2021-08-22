package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appduonotarypraktikaserver.entity.Attachment;

import java.util.UUID;

@Data
public class ReqTestimonials {
    private String firstName;
    private String lastName;
    private String companyInfo;
    private String text;
    private MultipartFile file;
}
