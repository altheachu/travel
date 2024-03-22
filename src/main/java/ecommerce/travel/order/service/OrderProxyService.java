package ecommerce.travel.order.service;

import ecommerce.travel.utility.dto.OrderDetailProxyDTO;
import ecommerce.travel.utility.dto.OrderEventProxyDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface OrderProxyService {

    /*
    description: get a map that product id is key and price is value
    author: Althea Chu
    params: productIds
    lastModified: 2023-12-24
    */
    Map<Integer, BigDecimal> findProductPriceById(List<Integer> productIds) throws Exception;
    /*
    description: deduct product stock based on an order
    author: Althea Chu
    params: orderDetail, msgId, sendTime
    lastModified: 2024-03-21
    */
    Boolean deductProductStock(OrderEventProxyDTO eventProxyDTO) throws Exception;
}
