package ecommerce.travel.order.service.impl;

import ecommerce.travel.config.RabbitMQConfig;
import ecommerce.travel.order.service.OrderProxyService;
import ecommerce.travel.product.service.ProductService;
import ecommerce.travel.utility.OrderDetailProxyDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderProxyServiceImpl implements OrderProxyService {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private ProductService productService;
    public OrderProxyServiceImpl(ProductService productService){
        this.productService = productService;
    }
    @Resource
    public RabbitTemplate rabbitTemplate;

    @Override
    public Map<Integer, BigDecimal> findProductPriceById(List<Integer> productIds) throws Exception {
        Map<Integer, BigDecimal> productPriceMap = new HashMap<>();
        for(Integer productId: productIds){
            productPriceMap.put(productId, productService.findProductById(productId).getPrice());
        }
        return productPriceMap;
    }

    @Override
    public Boolean deductProductStock(List<OrderDetailProxyDTO> orderDetails) {
        Boolean isPublish = false;
        String msgId = String.valueOf(Math.random()*1000);
        String sendTime = sdf.format(new Date());
        rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_PRODUCT_DIRECT_EXCHANGE, RabbitMQConfig.RABBITMQ_PRODUCT_DIRECT_ROUTING, orderDetails);
        isPublish = true;
        return isPublish;
    }
}
