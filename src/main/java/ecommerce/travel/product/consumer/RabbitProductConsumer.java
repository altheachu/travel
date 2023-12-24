package ecommerce.travel.product.consumer;

import ecommerce.travel.config.RabbitMQConfig;
import ecommerce.travel.order.entity.OrderDetail;
import ecommerce.travel.util.OrderDetailProxyDTO;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

@Component
public class RabbitProductConsumer {

    @RabbitListener(queues = {RabbitMQConfig.RABBITMQ_PRODUCT_TOPIC})
    public void deductStockFromOrder(OrderDetailProxyDTO orderDetailList){
        System.out.print("消費端收到服務端訊息: "+ orderDetailList.toString());
    }
}
