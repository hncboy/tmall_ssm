package com.hncboy.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Product {
    private Integer id;

    private String name;

    private String subTitle;

    private Float originalPrice;

    private Float promotePrice;

    private Integer stock;

    private Integer cid;

    private Date createDate;

    /*非数据库字段*/
    private Category  category;

    private ProductImage firstProductImage;

    private List<ProductImage> productSingleImages;

    private List<ProductImage> productDetailImages;

    private int saleCount;

    private int reviewCount;
}