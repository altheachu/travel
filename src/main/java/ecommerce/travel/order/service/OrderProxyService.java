package ecommerce.travel.order.service;

import ecommerce.travel.utility.OrderDetailProxyDTO;

public interface OrderProxyService {

    // 建立訂單後進行扣庫
    String deductProductStock(OrderDetailProxyDTO orderDetail);
}
