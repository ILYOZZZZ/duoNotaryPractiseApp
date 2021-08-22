package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.AdditionalService;
import uz.pdp.appduonotarypraktikaserver.entity.AdditionalServicePrice;
import uz.pdp.appduonotarypraktikaserver.entity.Services;
import uz.pdp.appduonotarypraktikaserver.entity.ServicePrice;

import java.util.List;
import java.util.UUID;

public interface AdditionalServicePriceRepository extends JpaRepository<AdditionalServicePrice, UUID> {

    List< AdditionalServicePrice> findAllByService(Services service);
}
