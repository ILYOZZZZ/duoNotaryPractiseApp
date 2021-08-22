package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.CompanySocialLink;

import java.util.UUID;

public interface CompanySocialLinkRepository extends JpaRepository<CompanySocialLink, UUID> {

}
