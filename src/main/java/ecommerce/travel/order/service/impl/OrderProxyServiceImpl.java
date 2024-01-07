package ecommerce.travel.order.service.impl;

import ecommerce.travel.config.RabbitMQConfig;
import ecommerce.travel.order.service.OrderProxyService;
import ecommerce.travel.product.service.ProductService;
import ecommerce.travel.utility.dto.OrderDetailProxyDTO;
import ecommerce.travel.utility.dto.OrderEventProxyDTO;
import ecommerce.travel.utility.entity.Eventlog;
import ecommerce.travel.utility.mapper.EventlogMapper;
import ecommerce.travel.utility.service.EventlogService;
import ecommerce.travel.utility.utils.EventlogConstant;
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
    private EventlogService eventlogService;
    public OrderProxyServiceImpl(ProductService productService, EventlogService eventlogService){
        this.productService = productService;
        this.eventlogService = eventlogService;
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
    public Boolean deductProductStock(List<OrderDetailProxyDTO> orderDetails) throws Exception {
        Boolean isPublish = false;

        String msgId = String.valueOf(Math.round(Math.random()*1000));
        String sendTime = sdf.format(new Date());

        // record event pre-publish log
        Eventlog eventlog = new Eventlog();
        eventlog.setMsgId(msgId);
        eventlog.setSendTime(sendTime);
        eventlog.setContent(orderDetails.toString());
        eventlog.setType(EventlogConstant.prePublishMsg);
        eventlogService.updateEventLog(eventlog);

        // publish event
        OrderEventProxyDTO eventProxyDTO = new OrderEventProxyDTO();
        eventProxyDTO.setMsgId(msgId);
        eventProxyDTO.setSendTime(sendTime);
        eventProxyDTO.setOrderDetailProxyDTOList(orderDetails);
        rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_ORDER_TO_PRODUCT_DIRECT_EXCHANGE, RabbitMQConfig.RABBITMQ_ORDER_TO_PRODUCT_DIRECT_ROUTING, eventProxyDTO);
        isPublish = true;
        return isPublish;
    }
}
