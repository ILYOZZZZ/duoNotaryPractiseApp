package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.DocAmountPricing;
import uz.pdp.appduonotarypraktikaserver.entity.Services;
import uz.pdp.appduonotarypraktikaserver.entity.ZipCode;

import java.util.List;
import java.util.UUID;

public interface DocAmountPricingRepository extends JpaRepository<DocAmountPricing, UUID> {

    List<DocAmountPricing> findAllByServiceAndZipCode(Services service, ZipCode zipCode);

//    @Query(value = "select * from doc_amount_pricing dap where dap.service_id=?1 and dap.zip_code_id=?2 and dap.from_count<=?3 when dap.till_count<>0 then dap.till_count>=?3 else false end",nativeQuery = true)
//    DocAmountPricing findOneByServiceAndZipCode(UUID serviceId, UUID zipCodeId, int docAmount);
}
