package com.hncboy.tmall.service;

import com.hncboy.tmall.pojo.Category;
import com.hncboy.tmall.util.Page;

import java.util.List;

public interface CategoryService {

    List<Category> list(Page page);

    int total();

    void add(Category category);

    void delete(int id);

    Category get(int id);

    void update(Category category);
}
