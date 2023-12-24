package ecommerce.travel.order.service.impl;

import ecommerce.travel.config.RabbitMQConfig;
import ecommerce.travel.order.model.OrderDetailModel;
import ecommerce.travel.order.service.OrderProxyService;
import ecommerce.travel.order.service.OrderService;
import ecommerce.travel.util.OrderDetailProxyDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderProxyServiceImpl implements OrderProxyService {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Resource
    public RabbitTemplate rabbitTemplate;
    @Override
    public String deductProductStock(OrderDetailProxyDTO orderDetail) {
        String msgId = String.valueOf(Math.random()*1000);
        String sendTime = sdf.format(new Date());
//        Map<String, Object> map = new HashMap<>();
//        map.put("msgId", msgId);
//        map.put("sendTime", sendTime);
//        map.put("orderDetailList", orderDetailList);
        rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_PRODUCT_DIRECT_EXCHANGE, RabbitMQConfig.RABBITMQ_PRODUCT_DIRECT_ROUTING, orderDetail);
        return "ok";
    }
}
