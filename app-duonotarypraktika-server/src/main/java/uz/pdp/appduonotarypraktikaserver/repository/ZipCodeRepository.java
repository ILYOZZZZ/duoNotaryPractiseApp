package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.ZipCode;
import uz.pdp.appduonotarypraktikaserver.resModels.*;
import java.util.List;
import java.util.UUID;

public interface ZipCodeRepository extends JpaRepository<ZipCode, UUID> {


    @Query(value = "select * from zip_code join user_zip_code uzc on zip_code.id = uzc.zip_code_id where uzc.user_id=:userId",nativeQuery = true)
    List<ZipCode> getZipCodesByUserId(@Param(value = "userId") UUID userId);

    @Query(value = "select cast(zc.id as varchar ) as id , zc.zip_code as zipCode  from user_zip_code uzc join zip_code zc on uzc.zip_code_id = zc.id where uzc.user_id=:userId", nativeQuery = true)
    List<ResZipCode> getZipCodeByUserId(@Param(value = "userId") UUID userId);

    @Query(value ="select cast(c.id as varchar ) as id,  c.name as name from zip_code zc   join county c on c.id = zc.county_id where  zc.id=:zipCodeId" ,nativeQuery = true)
    ResCounty getCountyByZipCodeId(@Param(value = "zipCodeId") UUID zipCodeId);

    @Query(value ="select cast(s.id as varchar  ) as id,  s.name as name from zip_code zc   join county c on c.id = zc.county_id join state s on c.state_id = s.id where  zc.id=:zipCodeId" ,nativeQuery = true)
    ResState getStateByZipCodeId(@Param(value = "zipCodeId") UUID zipCodeId);


    @Query(value = "select cast(zc.id as varchar ) as id , zc.zip_code as zipCode  from zip_code zc join out_of_service oos on zc.id = oos.waiting_zip_code_id and oos.id=:outOfServiceId", nativeQuery = true)
    List<ResZipCodes> getResZipCodeByOutOfServiceId(@Param(value = "outOfServiceId") UUID outOfServiceId);


    @Query(value = "select cast(zip_code.id as varchar) as id , zip_code.zip_code as zipCode from zip_code where county_id=:countyId" , nativeQuery = true)
    List<ResZipCodes> getZipCodesByCountyId(@Param(value = "countyId") UUID countyId);

    @Query(value = "select cast(zip_code.id as varchar) , cast(s.id as varchar) as stateId ,zip_code.zip_code as zipCode, s.name as stateName from zip_code  join county c on zip_code.county_id = c.id join state s on c.state_id = s.id where zip_code.id=:zipCodeId",nativeQuery = true)
    ResZipCodeCurrent getCurrentZipCodes(@Param(value = "zipCodeId") UUID id);


    @Query(value = "select cast(zip_code.id as varchar) as id , zip_code.zip_code as zipCode from zip_code join county c on c.id = zip_code.county_id join state s on c.state_id = s.id where s.id=:stateParamId",nativeQuery = true)
    List<ResZipCodes> getZipCodesOfState(@Param(value = "stateParamId") UUID id);



}
