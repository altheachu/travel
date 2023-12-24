package ecommerce.travel.demoConsumer.comsumer;

import ecommerce.travel.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = {RabbitMQConfig.RABBITMQ_DEMOPRODUCER_TO_DEMOCOMSUMER_TOPIC_001})
public class RabbitDemoConsumer {
    @RabbitHandler
    public void demoConsumer(Map map){
        System.out.print("Demo consumer receives a msg from demo producer: " + map.toString());
    }
}
