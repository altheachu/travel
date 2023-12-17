package ecommerce.travel.config;

public class RabbitMQConfig {

    /*
    * 隊列主題名稱
    */
    public static final String RABBITMQ_DEMO_TOPIC = "rabbitmqDemoTopic";

    /*
     * 交換機名稱
     */
    public static final String RABBITMQ_DEMO_DIRECT_EXCHANGE = "rabbitmqDemoDirectExchange";

    /*
     * 交換機與隊列綁定的匹配鍵
     */
    public static final String RABBITMQ_DEMO_DIRECT_ROUTING = "rabbitmqDemoDirectRouting";
}
