package com.hncboy.tmall.service;

import com.hncboy.tmall.pojo.Product;
import com.hncboy.tmall.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {

    void init(Product p);

    void update(PropertyValue pv);

    PropertyValue get(int ptid, int pid);

    List<PropertyValue> list(int pid);
}
