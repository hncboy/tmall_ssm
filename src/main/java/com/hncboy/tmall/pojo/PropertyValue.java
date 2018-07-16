package com.hncboy.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PropertyValue {
    private Integer id;

    private Integer pid;

    private Integer ptid;

    private String value;

    private Property property; //非数据库字段
}