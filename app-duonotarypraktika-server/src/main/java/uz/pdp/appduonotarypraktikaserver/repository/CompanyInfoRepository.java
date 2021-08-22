package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.CompanyInfo;

import java.util.UUID;

public interface CompanyInfoRepository extends JpaRepository<CompanyInfo, UUID> {
}
