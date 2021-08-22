package uz.pdp.appduonotarypraktikaserver.service.team1service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.*;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqDiscountType;
import uz.pdp.appduonotarypraktikaserver.repository.*;
import java.util.*;

@Service
public class DiscountService {

    @Autowired
    DiscountTypeRepository discountTypeRepository;

    @Autowired
    ZipCodeRepository zipCodeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserZipCodeRepository userZipCodeRepository;

    @Autowired
    DiscountRepository discountRepository;


    public ApiResponse crateDiscountType(ReqDiscountType reqDiscountType, User user) {
        if (!discountTypeRepository.existsByNameIgnoreCase(reqDiscountType.getName())) {
            List<ZipCode> zipcodes = new ArrayList<>();
            for (Role role : user.getRoles()) {
                if (role.getName().equals(RoleName.ROLE_SUPER_ADMIN)) {
                    zipcodes = zipCodeRepository.findAll();
                } else {
                    zipcodes = zipCodeRepository.getZipCodesByUserId(user.getId());
                }
            }
            List<DiscountType> discountTypes = new ArrayList<>();

            zipcodes.forEach(zipCode -> {
                DiscountType discountType = new DiscountType();
                discountType.setName(reqDiscountType.getName());
                discountType.setPercent(reqDiscountType.getPercent());
                discountType.setMaxDiscountSum(reqDiscountType.getMaxDiscountSum());
                discountType.setActive(true);
                discountType.setZipCode(zipCode);
                discountTypes.add(discountType);
            });
            discountTypeRepository.saveAll(discountTypes);

            return new ApiResponse("success", true);
        }
        return new ApiResponse("This discount type already exists", false);
    }

    public ApiResponse getDiscountType() {
        List<DiscountType> all = discountTypeRepository.findAll();
        return new ApiResponse("success", true, all);
    }

    public ApiResponse changeActive(UUID discountTypeId) {
        return new ApiResponse("Discount type "+(discountTypeRepository.changeActiveById(discountTypeId)?"activated":"deactivated"), true);
    }

    public ApiResponse editDiscountType(ReqDiscountType reqDiscountType) {
        discountTypeRepository.editDiscountTypeById(reqDiscountType.getName(),reqDiscountType.getPercent(),reqDiscountType.getMaxDiscountSum(),reqDiscountType.getId());
        return new ApiResponse("success", true);
    }

    public ApiResponse deleteDiscountType(UUID discountTypeId) {
        discountTypeRepository.deleteById(discountTypeId);
        return new ApiResponse("success", true);
}



    public ApiResponse getDiscount(){
        List<Discount> all = discountRepository.findAll();
        return new ApiResponse("success",true,all);
    }

    public ApiResponse deleteDiscount(UUID discountId) {
        discountRepository.deleteById(discountId);
        return new ApiResponse("success", true);
    }

    public ApiResponse changeActiveDiscount(UUID discountId) {
        return new ApiResponse(" Discount "+(discountRepository.changeActiveById(discountId)?"activated":"deactivated"), true);
    }

}
