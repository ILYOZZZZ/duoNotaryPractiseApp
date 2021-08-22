package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.County;
import uz.pdp.appduonotarypraktikaserver.resModels.ResCountry;
import uz.pdp.appduonotarypraktikaserver.resModels.ResCounty;

import java.util.UUID;

public interface CountyRepository extends JpaRepository<County, UUID> {

    @Query(value = "select cast(c.id as varchar),c.name as name , c.description as description , c.active as active from county c where state_id=:stateId and lower(c.name) LIKE concat('%',lower(:countyName),'%') order by c.name" , nativeQuery = true)
    Page<ResCounty> getAllCountiesByState(@Param(value = "stateId")UUID stateId, @Param(value = "countyName") String countyName , Pageable pageable);

    @Query(value = "select cast(c.id as varchar) , c.name as name , c.description as description , c.active as active from county c where c.id = :countyId",nativeQuery = true)
    ResCounty getCurrentCounty(@Param(value = "countyId") UUID id);

    @Query(value = "select cast(c.id as varchar) , c.name as name , c.description as description , c.active as active from county c join zip_code zc on c.id = zc.county_id where zc.id=:zipCodeId",nativeQuery = true)
    ResCounty getCountyByZipCode(@Param(value = "zipCodeId") UUID id);


}
