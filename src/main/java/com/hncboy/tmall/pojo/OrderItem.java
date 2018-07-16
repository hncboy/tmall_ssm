package com.hncboy.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem {
    private Integer id;

    private Integer pid;

    private Integer oid;

    private Integer uid;

    private Integer number;

    private Product product; //非数据库字段
}