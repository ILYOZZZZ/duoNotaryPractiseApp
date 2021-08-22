package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.Attachment;
import uz.pdp.appduonotarypraktikaserver.entity.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    void deleteAttachmentContentByAttachment(Attachment attachment);

    AttachmentContent findByAttachment(Attachment attachment);


}
