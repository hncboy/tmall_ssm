package com.hncboy.tmall.service.impl;

import com.hncboy.tmall.mapper.PropertyValueMapper;
import com.hncboy.tmall.pojo.Product;
import com.hncboy.tmall.pojo.Property;
import com.hncboy.tmall.pojo.PropertyValue;
import com.hncboy.tmall.pojo.PropertyValueExample;
import com.hncboy.tmall.service.PropertyService;
import com.hncboy.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    private PropertyValueMapper propertyValueMapper;

    @Autowired
    private PropertyService propertyService;

    @Override
    public void init(Product p) {
        List<Property> pts = propertyService.list(p.getCid());
        for (Property pt : pts) {
            PropertyValue pv = get(pt.getId(), p.getId());
            if (null == pv) {
                pv = new PropertyValue();
                pv.setPid(p.getId());
                pv.setPtid(pt.getId());
                propertyValueMapper.insert(pv);
            }
        }
    }

    @Override
    public void update(PropertyValue pv) {
        propertyValueMapper.updateByPrimaryKeySelective(pv);
    }

    @Override
    public PropertyValue get(int ptid, int pid) {
        //根据产品id和属性id获取PropertyValue对象
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria()
                .andPtidEqualTo(ptid)
                .andPidEqualTo(pid);
        List<PropertyValue> pvs = propertyValueMapper.selectByExample(example);
        if (pvs.isEmpty()) {
            return null;
        }
        return pvs.get(0);
    }

    @Override
    public List<PropertyValue> list(int pid) {
        //根据产品id获取所有属性值
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> result = propertyValueMapper.selectByExample(example);
        for (PropertyValue pv : result) {
            Property property = propertyService.get(pv.getPtid());
            pv.setProperty(property);
        }
        return result;
    }
}
