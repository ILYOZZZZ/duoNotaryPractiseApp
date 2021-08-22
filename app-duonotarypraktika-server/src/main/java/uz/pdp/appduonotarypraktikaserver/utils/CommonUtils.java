package uz.pdp.appduonotarypraktikaserver.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.HttpClientErrorException;
import uz.pdp.appduonotarypraktikaserver.exceptions.BadRequest;


public class CommonUtils {

    public static void validatePageAndSize(int pageNumber,int pageSize){
        if (pageNumber<1){
            throw new BadRequest("Page number can't be less than 1");
        }

        if (pageSize>AppConstants.MAX_PAGE_SIZE||pageSize<1){
            throw new BadRequest("Incorrect page size. Page size must be between 1 and " + AppConstants.MAX_PAGE_SIZE);
        }

    }
    public static Pageable createPageableBySort(int pageNumber, int pageSize,String columnName,String sortType){
        validatePageAndSize(pageNumber,pageSize);
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize, Sort.Direction.fromString(sortType),columnName);
        return pageable;
    }

    public static Pageable createPageable(int pageNumber, int pageSize){
        validatePageAndSize(pageNumber,pageSize);
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize);
        return pageable;
    }
}
