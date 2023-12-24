package ecommerce.travel.order.service;

import ecommerce.travel.order.model.OrderDetailModel;
import ecommerce.travel.util.OrderDetailProxyDTO;

import java.util.List;

public interface OrderProxyService {

    // 建立訂單後進行扣庫
    String deductProductStock(OrderDetailProxyDTO orderDetail);
}
