package ecommerce.travel.order.service;

import ecommerce.travel.utility.dto.OrderDetailProxyDTO;

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
    params: orderDetail
    lastModified: 2023-12-24
    */
    Boolean deductProductStock(List<OrderDetailProxyDTO> orderDetails) throws Exception;
}
