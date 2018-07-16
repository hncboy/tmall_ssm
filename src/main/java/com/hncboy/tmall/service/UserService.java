package com.hncboy.tmall.service;

import com.hncboy.tmall.pojo.User;

import java.util.List;

public interface UserService {

    void add(User c);

    void delete(int id);

    void update(User c);

    User get(int id);

    List list();

    boolean isExist(String name);
}
