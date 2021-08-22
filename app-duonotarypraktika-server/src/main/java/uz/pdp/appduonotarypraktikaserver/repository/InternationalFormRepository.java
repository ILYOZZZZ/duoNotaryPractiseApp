package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.InternationalForm;

import java.util.UUID;

public interface InternationalFormRepository extends JpaRepository<InternationalForm, UUID> {

}
