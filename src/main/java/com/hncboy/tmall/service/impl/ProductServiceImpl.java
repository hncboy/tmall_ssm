package com.hncboy.tmall.service.impl;

import com.hncboy.tmall.mapper.ProductMapper;
import com.hncboy.tmall.pojo.Category;
import com.hncboy.tmall.pojo.Product;
import com.hncboy.tmall.pojo.ProductExample;
import com.hncboy.tmall.service.CategoryService;
import com.hncboy.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryService categoryService;

    @Override
    public void add(Product p) {
        productMapper.insert(p);
    }

    @Override
    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product p) {
        productMapper.updateByPrimaryKeySelective(p);
    }

    @Override
    public Product get(int id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List result = productMapper.selectByExample(example);
        setCategory(result);
        return result;
    }

    public void setCategory(List<Product> ps) {
        for (Product p : ps) {
            setCategory(p);
        }
    }

    public void setCategory(Product p) {
        int cid = p.getCid();
        Category c = categoryService.get(cid);
        p.setCategory(c);
    }
}
