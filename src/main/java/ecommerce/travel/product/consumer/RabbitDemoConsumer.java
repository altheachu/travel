package ecommerce.travel.product.consumer;

import ecommerce.travel.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = {RabbitMQConfig.RABBITMQ_DEMO_TOPIC})
public class RabbitDemoConsumer {

    @RabbitHandler
    public void testConsumer(Map map){
        System.out.print("消費端收到服務端訊息: "+ map.toString());
    }
}
