package uz.pdp.appduonotarypraktikaserver.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appduonotarypraktikaserver.entity.enums.OrderStatus;
import uz.pdp.appduonotarypraktikaserver.entity.enums.PayStatus;
import uz.pdp.appduonotarypraktikaserver.entity.template.AbsEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Orders")
public class Order extends AbsEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serialId;

    private int docAmount;

    private int orderSum;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String address;

    private String lan;

    private String lat;

    @Enumerated(EnumType.STRING)
    private PayStatus payStatus;

    private double discountPercent;

    private boolean docPackage;

    private String docVerifyId;

    @ManyToOne
    private ServicePrice servicePrice;

    @ManyToOne
    private User customer;

    @ManyToOne
    private User agent;

    @ManyToOne
    private PayType payType;
}
