package uz.pdp.appduonotarypraktikaserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqBlog {


    private String title;
    private String description;
    private MultipartFile file;

}
