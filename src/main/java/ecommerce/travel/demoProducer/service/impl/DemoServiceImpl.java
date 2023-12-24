package ecommerce.travel.demoProducer.service.impl;

import ecommerce.travel.config.RabbitMQConfig;
import ecommerce.travel.demoProducer.service.DemoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class DemoServiceImpl implements DemoService {

    //日期格式化
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Resource
    public RabbitTemplate rabbitTemplate;

    @Override
    public String sendMsg(String msg) throws Exception {
        try {
            String msgId = String.valueOf(Math.random()*1000);
            String sendTime = sdf.format(new Date());
            Map<String, Object> map = new HashMap<>();
            map.put("msgId", msgId);
            map.put("sendTime", sendTime);
            map.put("msg", msg);
            rabbitTemplate.convertAndSend(RabbitMQConfig.RABBITMQ_DEMOPRODUCER_TO_DEMOCOMSUMER_DIRECT_EXCHANGE, RabbitMQConfig.RABBITMQ_DEMOPRODUCER_TO_DEMOCOMSUMER_DIRECT_ROUTING, map);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }
}
