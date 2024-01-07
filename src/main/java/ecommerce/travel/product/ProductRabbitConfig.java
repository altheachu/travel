package ecommerce.travel.product;

import ecommerce.travel.config.RabbitMQConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductRabbitConfig {

    @Bean
    public Queue rabbitmqProductDirectQueue(){
        /**
         * 1、name:    队列名称
         * 2、durable: 是否持久化
         * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
         * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
         * */
        return new Queue(RabbitMQConfig.RABBITMQ_ORDER_TO_PRODUCT_TOPIC, true, false, false);
    }

    @Bean
    public DirectExchange rabbitmqProductDirectExchange() {
        //Direct交换机
        return new DirectExchange(RabbitMQConfig.RABBITMQ_ORDER_TO_PRODUCT_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding bindProductDirect() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(rabbitmqProductDirectQueue())
                //到交换机
                .to(rabbitmqProductDirectExchange())
                //并设置匹配键
                .with(RabbitMQConfig.RABBITMQ_ORDER_TO_PRODUCT_DIRECT_ROUTING);
    }
}
