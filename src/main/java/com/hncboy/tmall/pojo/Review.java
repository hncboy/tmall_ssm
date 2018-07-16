package com.hncboy.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class Review {
    private Integer id;

    private String content;

    private Integer uid;

    private Integer pid;

    private Date createDate;

    private User user; //非数据库字段
}