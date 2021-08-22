package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.RealEstateForm;

import java.util.UUID;

public interface RealEstateFormRepository extends JpaRepository<RealEstateForm, UUID> {

}
