package ecommerce.travel.product.service.impl;

import ecommerce.travel.aop.EventLog;
import ecommerce.travel.aop.LogTime;
import ecommerce.travel.product.service.ProductProxyService;
import ecommerce.travel.utility.dto.ProductEventProxyDTO;
import ecommerce.travel.utility.utils.EventlogConstant;
import ecommerce.travel.utility.utils.RabbitMqConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ProductProxyServiceImpl implements ProductProxyService {

    @Resource
    public RabbitTemplate rabbitTemplate;

    @EventLog(logTime = LogTime.AFTER_METHOD, type = EventlogConstant.publishMsg)
    @Override
    public void modifyOrderStatus(ProductEventProxyDTO productEventProxyDTO) throws Exception {
        try {
            rabbitTemplate.convertAndSend(RabbitMqConstant.RABBITMQ_PRODUCT_TO_ORDER_DIRECT_EXCHANGE, RabbitMqConstant.RABBITMQ_PRODUCT_TO_ORDER_DIRECT_ROUTING, productEventProxyDTO);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
