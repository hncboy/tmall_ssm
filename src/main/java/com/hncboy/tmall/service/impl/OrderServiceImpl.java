package com.hncboy.tmall.service.impl;

import com.hncboy.tmall.mapper.OrderMapper;
import com.hncboy.tmall.pojo.Order;
import com.hncboy.tmall.pojo.OrderExample;
import com.hncboy.tmall.pojo.OrderItem;
import com.hncboy.tmall.pojo.User;
import com.hncboy.tmall.service.OrderItemService;
import com.hncboy.tmall.service.OrderService;
import com.hncboy.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemService orderItemService;

    @Override
    public void add(Order c) {
        orderMapper.insert(c);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackForClassName = "Exception")
    public float add(Order o, List<OrderItem> ois) {
        float total = 0;
        add(o);

        for (OrderItem oi : ois) {
            oi.setOid(o.getId());
            orderItemService.update(oi);
            total += oi.getProduct().getPromotePrice() * oi.getNumber();
        }
        return total;
    }

    @Override
    public void delete(int id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order c) {
        orderMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Order get(int id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result = orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }

    public void setUser(List<Order> os) {
        for (Order o : os) {
            setUser(o);
        }
    }

    public void setUser(Order o) {
        int uid = o.getUid();
        User u = userService.get(uid);
        o.setUser(u);
    }
}
