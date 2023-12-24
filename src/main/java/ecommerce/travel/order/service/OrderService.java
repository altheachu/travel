package ecommerce.travel.order.service;

import ecommerce.travel.order.model.OrderModel;

public interface OrderService {

    /*
    description: check if sum of order details amount equals to front-end calculation
    author: Althea Chu
    params: orderModel
    lastModified: 2023-12-24
    */
    Boolean checkOrderAmt(OrderModel orderModel) throws Exception;

    /*
    description: create an order and deduct product stock
    author: Althea Chu
    params: orderModel
    lastModified: 2023-12-24
    */
    Boolean createOrder(OrderModel orderModel) throws Exception;

}
