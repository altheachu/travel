package ecommerce.travel.product.service;

import ecommerce.travel.utility.dto.OrderEventProxyDTO;
import ecommerce.travel.utility.dto.ProductEventProxyDTO;

import java.util.Map;

public interface ProductProxyService {

    /*
    description: change the status of order after deducting stock successfully
    author: Althea Chu
    params: orderDetail, msgId, sendTime
    lastModified: 2025-08-05
    */
    void modifyOrderStatus(ProductEventProxyDTO productEventProxyDTO) throws Exception;

}
