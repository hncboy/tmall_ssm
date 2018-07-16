package com.hncboy.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Property {
    private Integer id;

    private Integer cid;

    private String name;

    private Category category; //非数据库字段
}