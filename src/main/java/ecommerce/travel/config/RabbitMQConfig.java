package ecommerce.travel.config;

public class RabbitMQConfig {

    /*
    * 隊列主題名稱
    */
    public static final String RABBITMQ_DEMOPRODUCER_TO_DEMOCOMSUMER_TOPIC_001 = "rabbitmqDemoTopic";
    public static final String RABBITMQ_PRODUCT_TOPIC = "rabbitmqProductTopic";

    /*
     * 交換機名稱
     */
    public static final String RABBITMQ_DEMOPRODUCER_TO_DEMOCOMSUMER_DIRECT_EXCHANGE = "rabbitmqDemoDirectExchange";
    public static final String RABBITMQ_PRODUCT_DIRECT_EXCHANGE = "rabbitmqProductDirectExchange";

    /*
     * 交換機與隊列綁定的匹配鍵
     */
    public static final String RABBITMQ_DEMOPRODUCER_TO_DEMOCOMSUMER_DIRECT_ROUTING = "rabbitmqDemoDirectRouting";
    public static final String RABBITMQ_PRODUCT_DIRECT_ROUTING = "rabbitmqProductDirectRouting";
}
