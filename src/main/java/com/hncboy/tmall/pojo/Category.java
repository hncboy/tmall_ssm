package com.hncboy.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Category {

    private Integer id;

    private String name;

    /**
     * 如下是非数据库字段
     */
    private List<Product> products;

    private List<List<Product>> productsByRow;
}