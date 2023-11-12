package ecommerce.travel.service;

import ecommerce.travel.model.OrderModel;

public interface OrderService {

    Boolean createOrder(OrderModel orderModel) throws Exception;

}
