package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.appduonotarypraktikaserver.entity.Testimonials;
import uz.pdp.appduonotarypraktikaserver.resModels.ResTestimonials;

import java.util.List;
import java.util.UUID;

public interface TestimonialsRepository extends JpaRepository<Testimonials, UUID> {

    @Query(value = "select first_name as firstName , last_name as lastName , company_info as companyInfo, text as text,cast(attachment_id as varchar) as attachmentId from testimonials order by RANDOM() limit 6",nativeQuery = true)
    List<ResTestimonials> getTestimonialsRandomly();
}
