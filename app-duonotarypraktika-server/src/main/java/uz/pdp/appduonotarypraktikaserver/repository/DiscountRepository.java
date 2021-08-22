package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.appduonotarypraktikaserver.entity.Discount;

import java.util.UUID;

public interface DiscountRepository extends JpaRepository<Discount, UUID> {

    @Transactional
    @Query(value = "update discount set active = not active where id=:dId returning active",
            nativeQuery = true)
    boolean changeActiveById(@Param(value = "dId") UUID id);

}
