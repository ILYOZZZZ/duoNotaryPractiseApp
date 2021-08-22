package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.ServicePrice;
import uz.pdp.appduonotarypraktikaserver.entity.ZipCode;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ServicePriceRepository extends JpaRepository<ServicePrice, UUID> {

     List<ServicePrice> findAllByZipCodeZipCode(String zipCode);
}
