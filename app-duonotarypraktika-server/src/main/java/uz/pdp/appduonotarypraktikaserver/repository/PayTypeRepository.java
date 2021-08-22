package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.PayType;

import java.util.UUID;

public interface PayTypeRepository extends JpaRepository<PayType, UUID> {
}
