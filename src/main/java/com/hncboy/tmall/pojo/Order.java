package com.hncboy.tmall.pojo;

import com.hncboy.tmall.service.OrderService;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Order {
    private Integer id;

    private String orderCode;

    private String address;

    private String post;

    private String receiver;

    private String mobile;

    private String userMessage;

    private Date createDate;

    private Date payDate;

    private Date deliveryDate;

    private Date confirmDate;

    private Integer uid;

    private String status;

    /**
     * 如下是非数据库字段
     */
    private List<OrderItem> orderItems;

    private User user;

    private float total;

    private int totalNumber;

    public String getStatusDesc() {
        String desc;
        switch (status) {
            case OrderService.waitPay:
                desc = "待付款";
                break;
            case OrderService.waitDelivery:
                desc = "待发货";
                break;
            case OrderService.waitConfirm:
                desc = "待收货";
                break;
            case OrderService.waitReview:
                desc = "等评价";
                break;
            case OrderService.finish:
                desc = "完成";
                break;
            case OrderService.delete:
                desc = "刪除";
                break;
            default:
                desc = "未知";
        }
        return desc;
    }
}