package ecommerce.travel.order.service.impl;

import ecommerce.travel.aop.EventLog;
import ecommerce.travel.aop.LogTime;
import ecommerce.travel.config.RabbitMQConfig;
import ecommerce.travel.order.service.OrderProxyService;
import ecommerce.travel.product.service.ProductService;
import ecommerce.travel.utility.dto.OrderEventProxyDTO;
import ecommerce.travel.utility.service.EventlogService;
import ecommerce.travel.utility.utils.EventlogConstant;
import ecommerce.travel.utility.utils.RabbitMqConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderProxyServiceImpl implements OrderProxyService {
    private ProductService productService;
    private EventlogService eventlogService;
    public OrderProxyServiceImpl(ProductService productService, EventlogService eventlogService){
        this.productService = productService;
        this.eventlogService = eventlogService;
    }
    @Resource
    public RabbitTemplate rabbitTemplate;

    @Override
    public Map<Integer, BigDecimal> findProductPriceById(List<Integer> productIds) throws Exception {
        try {
            Map<Integer, BigDecimal> productPriceMap = new HashMap<>();
            for(Integer productId: productIds){
                productPriceMap.put(productId, productService.findProductById(productId).getPrice());
            }
            return productPriceMap;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @EventLog(logTime = LogTime.AFTER_METHOD, type = EventlogConstant.publishMsg)
    public Boolean deductProductStock(OrderEventProxyDTO eventProxyDTO) throws Exception {
        try {
            Boolean isPublish = false;
            // publish event
            rabbitTemplate.convertAndSend(RabbitMqConstant.RABBITMQ_ORDER_TO_PRODUCT_DIRECT_EXCHANGE, RabbitMqConstant.RABBITMQ_ORDER_TO_PRODUCT_DIRECT_ROUTING, eventProxyDTO);
            isPublish = true;
            return isPublish;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

}
