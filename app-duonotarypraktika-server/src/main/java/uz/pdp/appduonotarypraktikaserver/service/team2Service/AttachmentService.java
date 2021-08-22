package uz.pdp.appduonotarypraktikaserver.service.team2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.appduonotarypraktikaserver.entity.Attachment;
import uz.pdp.appduonotarypraktikaserver.entity.AttachmentContent;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.repository.AttachmentContentRepository;
import uz.pdp.appduonotarypraktikaserver.repository.AttachmentRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Service
public class AttachmentService {
    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Attachment saveAttachment(MultipartFile file) throws IOException {
        Attachment attachment = attachmentRepository.save(new Attachment(file.getName(), file.getSize(), file.getContentType()));
        AttachmentContent content = attachmentContentRepository.save(new AttachmentContent(file.getBytes(), attachment));
        return attachment;
    }

    public void deleteAttachment(Attachment attachment){
        attachmentContentRepository.deleteAttachmentContentByAttachment(attachment);
        attachmentRepository.delete(attachment);
    }
    public HttpEntity<?> getAttachmentContent(UUID attachmentId, HttpServletResponse response) {
        Attachment attachment = attachmentRepository.findById(attachmentId).orElseThrow(() -> new ResourceNotFound("Attachment", "id", attachmentId));
        AttachmentContent byAttachment = attachmentContentRepository.findByAttachment(attachment);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachment.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getName() + "\"")
                .body(byAttachment.getBytes());
    }
}
