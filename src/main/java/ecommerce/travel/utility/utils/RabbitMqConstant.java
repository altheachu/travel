package ecommerce.travel.utility.utils;

public class RabbitMqConstant {
    /*
     * Queue name
     */
    public static final String RABBITMQ_DEMOPRODUCER_TO_DEMOCOMSUMER_TOPIC_001 = "rabbitmqDemoTopic";
    public static final String RABBITMQ_ORDER_TO_PRODUCT_TOPIC = "rabbitmqProductTopic";
    public static final String DEAD_LETTER_QUEUE = "dead-letter-queue";

    /*
     * Exchange name
     */
    public static final String RABBITMQ_DEMOPRODUCER_TO_DEMOCOMSUMER_DIRECT_EXCHANGE = "rabbitmqDemoDirectExchange";
    public static final String RABBITMQ_ORDER_TO_PRODUCT_DIRECT_EXCHANGE = "rabbitmqProductDirectExchange";
    public static final String RABBITMQ_DEAD_LETTER_EXCHANGE = "dlx-exchange";

    /*
     * Queue-exchange routing key
     */
    public static final String RABBITMQ_DEMOPRODUCER_TO_DEMOCOMSUMER_DIRECT_ROUTING = "rabbitmqDemoDirectRouting";
    public static final String RABBITMQ_ORDER_TO_PRODUCT_DIRECT_ROUTING = "rabbitmqProductDirectRouting";
    public static final String RABBITMQ_DLX_ROUTING = "dlx-routing-key";

    /*
     * Arguments
     */
    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    public static final String X_MESSAGE_TTL = "x-message-ttl";
}
