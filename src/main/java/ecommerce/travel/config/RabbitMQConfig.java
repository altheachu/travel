package ecommerce.travel.config;

import ecommerce.travel.utility.utils.RabbitMqConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    /**
     * make sure all the new queue would be registered
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        RabbitAdmin admin = new RabbitAdmin(connectionFactory);
        admin.setAutoStartup(true);
        return admin;
    }

    /**
     * use JSON format to send event message
     */
    @Bean
    public MessageConverter jackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * make queue content format be JSON, it could be omitted.
     */
    /*
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter jackson2MessageConverter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jackson2MessageConverter);
        return template;
    }
     */

    /**
     * 1、name: queue
     * 2、durable: 是否持久化
     * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
     * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
     * 5. arguments
     * */
    @Bean
    public Queue rabbitmqProductDirectQueue(){
        Map<String, Object> args = new HashMap<>();
        // assign dead exchange
        args.put(RabbitMqConstant.X_DEAD_LETTER_EXCHANGE, RabbitMqConstant.RABBITMQ_DEAD_LETTER_EXCHANGE);
        // assign dlx routing key
        args.put(RabbitMqConstant.X_DEAD_LETTER_ROUTING_KEY, RabbitMqConstant.RABBITMQ_DLX_ROUTING);
        // assign message ttl
        // args.put(RabbitMQConfig.X_MESSAGE_TTL, 60000); // 可選，訊息過期時間（毫秒）
        return new Queue(RabbitMqConstant.RABBITMQ_ORDER_TO_PRODUCT_TOPIC, true, false, false, args);
    }

    @Bean
    public Queue dlq() {
        return new Queue(RabbitMqConstant.DEAD_LETTER_QUEUE, true);
    }

    @Bean
    public Queue rabbitmqOrderDirectQueue(){
        Map<String, Object> args = new HashMap<>();
        // assign dead exchange
        args.put(RabbitMqConstant.X_DEAD_LETTER_EXCHANGE, RabbitMqConstant.RABBITMQ_DEAD_LETTER_EXCHANGE);
        // assign dlx routing key
        args.put(RabbitMqConstant.X_DEAD_LETTER_ROUTING_KEY, RabbitMqConstant.RABBITMQ_DLX_ROUTING);
        // assign message ttl
        // args.put(RabbitMQConfig.X_MESSAGE_TTL, 60000); // 可選，訊息過期時間（毫秒）
        return new Queue(RabbitMqConstant.RABBITMQ_PRODUCT_TO_ORDER_TOPIC, true, false, false, args);
    }
    /**
     * Exchange 交換機
     */
    @Bean
    public DirectExchange rabbitmqProductDirectExchange() {
        return new DirectExchange(RabbitMqConstant.RABBITMQ_ORDER_TO_PRODUCT_DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public DirectExchange dlxExchange() {
        return new DirectExchange(RabbitMqConstant.RABBITMQ_DEAD_LETTER_EXCHANGE);
    }

    @Bean
    public DirectExchange rabbitmqOrderDirectExchange() {
        return new DirectExchange(RabbitMqConstant.RABBITMQ_PRODUCT_TO_ORDER_DIRECT_EXCHANGE, true, false);
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
                .with(RabbitMqConstant.RABBITMQ_ORDER_TO_PRODUCT_DIRECT_ROUTING);
    }

    @Bean
    public Binding dlqBinding() {
        return BindingBuilder
                .bind(dlq())
                .to(dlxExchange())
                .with(RabbitMqConstant.RABBITMQ_DLX_ROUTING); // 與上面 x-dead-letter-routing-key 對應
    }

    @Bean
    public Binding bindOrderDirect() {
        //链式写法，绑定交换机和队列，并设置匹配键
        return BindingBuilder
                //绑定队列
                .bind(rabbitmqOrderDirectQueue())
                //到交换机
                .to(rabbitmqOrderDirectExchange())
                //并设置匹配键
                .with(RabbitMqConstant.RABBITMQ_PRODUCT_TO_ORDER_DIRECT_ROUTING);
    }

}
