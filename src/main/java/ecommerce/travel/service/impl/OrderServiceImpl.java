package ecommerce.travel.service.impl;

import ecommerce.travel.entity.Order;
import ecommerce.travel.mapper.OrderMapper;
import ecommerce.travel.model.OrderModel;
import ecommerce.travel.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Integer createOrder(OrderModel orderModel) {
        Order order = new Order();
        BeanUtils.copyProperties(orderModel, order);
        order.setOrderDate(new Date(orderModel.getOrderDate()));
        return orderMapper.createOrder(order);
    }
}
