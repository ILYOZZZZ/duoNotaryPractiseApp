package uz.pdp.appduonotarypraktikaserver.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.appduonotarypraktikaserver.entity.Feedback;
import uz.pdp.appduonotarypraktikaserver.entity.enums.RoleName;
import uz.pdp.appduonotarypraktikaserver.resModels.ResFeedback;
import uz.pdp.appduonotarypraktikaserver.resModels.ResOrderDetailsForFeedbackAnswer;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {

//    @Query(value = "select cast(u.id as varchar) as id,concat(u.first_name , ' ' , u.last_name) as customerName,u.email as email ,u.phone_number as phoneNumber , (select ms.name as service from service_price as sp2 join service s on sp2.service_id = s.id join sub_service ss on s.sub_service_id = ss.id join main_service ms on ss.main_service_id = ms.id where sp2.id=o.service_price_id ) ,o.order_sum as amount , (select concat(users.first_name , ' ' , users.last_name) as agent from users where users.id=o.agent_id_id) , f.rate_amount as rating , f.comment as comment from " + "feedback f join users u on f.user_id = u.id join orders o on o.id = f.order_id where :userRole in (select r.name from user_role ur join role r on r.id = ur.role_id where ur.user_id=u.id) " , nativeQuery = true)
//    List<ResFeedback> getAllFeedbacksOfCustomerByZipCode(@Param(value = "userRole") RoleName roleName, @Param(value = "zipCodeId") UUID zipCodeId, Pageable pageable);
//
//    @Query(value = "select  cast(u.id as varchar) as id, u.first_name + ' ' + u.last_name as agent , f.rate_amount as rating , (select c.first_name + ' ' + c.last_name as customerName, c.email as email,c.phone_number as phoneNumber from users c where c.id=o.customer_id_id), (select ms.name as service from service_price as sp2 join service s on sp2.service_id = s.id join sub_service ss on s.sub_service_id = ss.id join main_service ms on ss.main_service_id = ms.id where sp2.id=o.service_price_id ),o.order_sum as amount , f.comment as comment from feedback f join users u on u.id = f.user_id join orders o on o.id = f.order_id where :userRole in (select r.name from user_role ur join role r on r.id = ur.role_id where ur.user_id=u.id) and :zipCodeId=(select sp.zip_code_id from service_price sp where sp.id = o.service_price_id)" , nativeQuery = true)
//    List<ResFeedback> getAllFeedbacksOfAgentByZipCode(@Param(value = "userRole") RoleName roleName, @Param(value = "zipCodeId") UUID zipCodeId, Pageable pageable);




    @Query(value = "select cast(feedback.id as varchar) as id, feedback.is_answered as isAnswered  ,cast(feedback.order_id as varchar) as orderId ,(select concat(u1.first_name ,' ',u1.last_name) as agent from users u1 where u1.id=o.agent_id) ,concat(u.first_name,' ',u.last_name) as customer , u.email as email , u.phone_number as phoneNumber , o.order_sum as amount , feedback.rate_amount as rating , feedback.comment as comment   from feedback join users u on feedback.user_id = u.id join user_role ur on u.id = ur.user_id join orders o on feedback.order_id = o.id where 'ROLE_CUSTOMER' in (select name from role ro where ro.id=ur.role_id)",nativeQuery = true)
    Page<ResFeedback> getAllFeedbacksOfCustomer(Pageable pageable);


    @Query(value = "select cast(feedback.id as varchar) as id , feedback.is_answered as isAnswered ,cast(feedback.order_id as varchar) as orderId , (select concat(u1.first_name ,' ',u1.last_name) as customer from users u1 where u1.id=o.customer_id) ,concat(u.first_name,' ',u.last_name) as agent , u.email as email , u.phone_number as phoneNumber , o.order_sum as amount , feedback.rate_amount as rating , feedback.comment as comment    from feedback join users u on feedback.user_id = u.id join user_role ur on u.id = ur.user_id join orders o on feedback.order_id = o.id where 'ROLE_AGENT' in (select name from role ro where ro.id=ur.role_id)",nativeQuery = true)
    Page<ResFeedback> getAllFeedbacksOfAgent(Pageable pageable);


    @Query(value = "select coalesce(count(*),0) from feedback join users u on u.id = feedback.user_id join user_role ur on u.id = ur.user_id where :roleName in (select name from role where role.id=ur.role_id)",nativeQuery = true)
    Integer getCountOfFeedbacks(@Param(value = "roleName")String roleName);
}
