package uz.pdp.appduonotarypraktikaserver.service.team2Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appduonotarypraktikaserver.entity.Attachment;
import uz.pdp.appduonotarypraktikaserver.entity.CompanyInfo;
import uz.pdp.appduonotarypraktikaserver.exceptions.ResourceNotFound;
import uz.pdp.appduonotarypraktikaserver.payload.ApiResponse;
import uz.pdp.appduonotarypraktikaserver.payload.ReqCompanyInfo;
import uz.pdp.appduonotarypraktikaserver.repository.AttachmentRepository;
import uz.pdp.appduonotarypraktikaserver.repository.CompanyInfoRepository;
import uz.pdp.appduonotarypraktikaserver.utils.CommonUtils;

import java.util.UUID;

@Service
public class CompanyInfoService {

    @Autowired
    CompanyInfoRepository companyInfoRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public ApiResponse saveCompanyInfo(ReqCompanyInfo reqCompanyInfo){
        Attachment attachment = attachmentRepository.findById(reqCompanyInfo.getLogoId()).orElseThrow(() -> new ResourceNotFound("Attachment", "id", reqCompanyInfo.getLogoId()));
        companyInfoRepository.save(new CompanyInfo(reqCompanyInfo.getCompanyName(),reqCompanyInfo.getPhoneNumbers(),reqCompanyInfo.getAddress(),reqCompanyInfo.getEmail(),reqCompanyInfo.getFax(),attachment));
        return new ApiResponse("successfully saved", true);

    }
    public ApiResponse editCompanyInfo(ReqCompanyInfo reqCompanyInfo){
        CompanyInfo companyInfo = companyInfoRepository.findById(reqCompanyInfo.getId()).orElseThrow(() -> new ResourceNotFound("CompanyInfo", "id", reqCompanyInfo.getId()));
        companyInfo.setAddress(reqCompanyInfo.getAddress());
        companyInfo.setCompanyName(reqCompanyInfo.getCompanyName());
        companyInfo.setEmail(reqCompanyInfo.getEmail());
        companyInfo.setFax(reqCompanyInfo.getFax());
        companyInfo.setLogo(attachmentRepository.findById(reqCompanyInfo.getLogoId()).orElseThrow(() -> new ResourceNotFound("Attachment", "id", reqCompanyInfo.getLogoId())));
        companyInfo.setPhoneNumbers(reqCompanyInfo.getPhoneNumbers());
        companyInfoRepository.save(companyInfo);
        return new ApiResponse("successfully edited", true);

    }
    public ApiResponse deleteCompanyInfo(UUID id){

        companyInfoRepository.deleteById(id);
        return new ApiResponse("successfully deleted", true);
    }
    public ApiResponse getAllCompany(Integer page,Integer size){
        Pageable pageable = CommonUtils.createPageable(page, size);
        return new ApiResponse("succesfully getted",true,companyInfoRepository.findAll(pageable));

    }



}
