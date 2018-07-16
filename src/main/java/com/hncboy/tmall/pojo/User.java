package com.hncboy.tmall.pojo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;

    private String name;

    private String password;

    /**
     * 匿名显示评价
     *
     * @return
     */
    public String getAnonymousName() {
        if (null == name) {
            return null;
        }
        if (name.length() <= 1) {
            return "*";
        }
        if (name.length() == 2) {
            return name.substring(0, 1) + "*";
        }
        char[] cs = name.toCharArray();
        for (int i = 1; i < cs.length - 1; i++) {
            cs[i] = '*';
        }
        return new String(cs);
    }
}