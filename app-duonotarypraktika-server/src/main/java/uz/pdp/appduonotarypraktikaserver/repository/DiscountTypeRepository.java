package uz.pdp.appduonotarypraktikaserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.appduonotarypraktikaserver.entity.DiscountType;

import java.util.UUID;

public interface DiscountTypeRepository extends JpaRepository<DiscountType, UUID> {

    boolean existsByNameIgnoreCase(String name);

    @Transactional
    @Query(value = "update discount_type set active = not active where id=:dtId returning active",
            nativeQuery = true)
    boolean changeActiveById(@Param(value = "dtId") UUID id);


    @Transactional
    @Modifying
    @Query(value = "update discount_type set name=?1, percent=?2, max_discount_sum=?3 where id=?4",nativeQuery = true)
   int editDiscountTypeById(String name, double percent, double maxDiscountSum, UUID id);

}