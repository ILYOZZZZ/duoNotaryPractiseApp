package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.OrderAdditionalServicePrice;

import java.util.UUID;

public interface OrderAdditionalServicePriceRepository extends JpaRepository<OrderAdditionalServicePrice, UUID> {
}
