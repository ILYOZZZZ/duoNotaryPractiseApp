package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.ApostilleDocType;

import java.util.UUID;

public interface ApostilleDocTypeRepository extends JpaRepository<ApostilleDocType, UUID> {
}
