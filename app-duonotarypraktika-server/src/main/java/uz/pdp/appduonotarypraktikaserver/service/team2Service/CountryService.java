package uz.pdp.appduonotarypraktikaserver.service.team2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.Country;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqCountry;
import uz.pdp.appduonotarypraktikaserver.repository.CountryRepository;
import uz.pdp.appduonotarypraktikaserver.resModels.ResCountry;
import uz.pdp.appduonotarypraktikaserver.utils.CommonUtils;

import java.util.UUID;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;


    // GET

    public ApiResponse getAllCountry(Integer page, Integer size,String name) {
        Pageable pageable = CommonUtils.createPageable(page, size);
        Page<ResCountry> allCountryPaged = countryRepository.getAllCountryPaged(name,pageable);
        return new ApiResponse("succesfully getted",true,allCountryPaged);
    }

    public ApiResponse getCurrentCountry(UUID id){

        ResCountry currentCountry = countryRepository.getCurrentCountry(id);
        return new ApiResponse("successfully got",true,currentCountry);
    }

    // SAVE

    public ApiResponse saveCountry(ReqCountry reqCountry){

        try {
            Country country = new Country();
            country.setName(reqCountry.getName());
            country.setDescription(reqCountry.getDescription());
            country.setActive(reqCountry.isActive());
            country.setEmbassy(reqCountry.isEmbassy());
            countryRepository.save(country);
            return new ApiResponse("Successfully saved",true);
        } catch (Exception e) {
            return new ApiResponse("Didn't saved",false);
        }
    }

    // DELETE

    public ApiResponse deleteCountry(UUID id) {
        try {
            countryRepository.deleteById(id);
            return new ApiResponse("Successfully deleted",true);
        } catch (Exception e) {
            return new ApiResponse("Didn't deleted", false);
        }

    }

    // EDIT

    public ApiResponse editCountry(ReqCountry reqCountry,UUID uuid){

        try {
            Country country = countryRepository.findById(uuid).orElseThrow(() -> new ResourceNotFound("Country", "id",uuid));
            country.setName(reqCountry.getName());
            country.setDescription(reqCountry.getDescription());
            country.setEmbassy(reqCountry.isEmbassy());
            countryRepository.save(country);
            return new ApiResponse("Successfully edited",true);
        } catch (ResourceNotFound resourceNotFound) {
            return new ApiResponse("Didn't edited",false);
        }
    }

    public ApiResponse editCountryActiveOrEmbassy(String type,UUID id){
        Country country = countryRepository.findById(id).orElseThrow(() -> new ResourceNotFound("Country", "id", id));
        if(type.equalsIgnoreCase("ACTIVE")){
            country.setActive(!country.getActive());
        }else if(type.equalsIgnoreCase("EMBASSY")){
            country.setEmbassy(!country.isEmbassy());
        }
        countryRepository.save(country);
        return new ApiResponse("successfully active edited",true);
    }





}
