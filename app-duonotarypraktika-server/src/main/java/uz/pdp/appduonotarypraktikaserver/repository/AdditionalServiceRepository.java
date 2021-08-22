package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.AdditionalService;

import java.util.UUID;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, UUID> {
}
