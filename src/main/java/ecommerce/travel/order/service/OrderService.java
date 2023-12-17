package ecommerce.travel.order.service;

import ecommerce.travel.order.model.OrderModel;

public interface OrderService {

    Boolean createOrder(OrderModel orderModel) throws Exception;

}
