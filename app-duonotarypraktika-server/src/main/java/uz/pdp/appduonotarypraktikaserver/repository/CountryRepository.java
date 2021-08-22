package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.Country;
import uz.pdp.appduonotarypraktikaserver.resModels.ResCountry;

import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID> {

    @Query(value = "select cast(c.id as varchar) as id , active as active , description as description , name as name , is_embassy as embassy  from country as c where lower(name) LIKE concat('%',lower(:nameOfCountry),'%'    ) order by c.name" ,nativeQuery = true)
    Page<ResCountry> getAllCountryPaged(@Param(value = "nameOfCountry") String name, Pageable pageable);

    @Query(value = "select cast(c.id as varchar) as id , active as active , description as description , name as name , is_embassy as embassy  from country as c where c.id=:countryId" ,nativeQuery = true)
    ResCountry getCurrentCountry(@Param(value = "countryId") UUID id);
}
