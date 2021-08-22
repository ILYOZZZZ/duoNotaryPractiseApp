package uz.pdp.appduonotarypraktikaserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appduonotarypraktikaserver.entity.PhoneVerify;

import java.util.Optional;
import java.util.UUID;

public interface PhoneVerifyRepository extends JpaRepository<PhoneVerify, UUID> {

    Optional<PhoneVerify> findByPhoneNumber(String phoneNumber);
}
