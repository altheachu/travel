package ecommerce.travel.aop;

import ecommerce.travel.utility.dto.OrderEventProxyDTO;
import ecommerce.travel.utility.dto.ProductEventProxyDTO;
import ecommerce.travel.utility.entity.Eventlog;
import ecommerce.travel.utility.service.EventlogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Aspect
public class EventlogAspect {

    @Autowired
    private EventlogService eventlogService;

    @Around("@annotation(eventLog)")
    public Object around(ProceedingJoinPoint joinPoint, EventLog eventLog) throws Throwable {

        Eventlog eventlog = new Eventlog();
        eventlog.setType(eventLog.type());

        Object obj = joinPoint.getArgs()[0];

        if (obj instanceof OrderEventProxyDTO){
            OrderEventProxyDTO orderEventProxyDTO = (OrderEventProxyDTO) obj;
            eventlog.setContent(String.valueOf(orderEventProxyDTO.getOrderDetailProxyDTOList()));
            eventlog.setMsgId(orderEventProxyDTO.getMsgId());
            eventlog.setSendTime(orderEventProxyDTO.getSendTime());
        } else if (obj instanceof ProductEventProxyDTO){
            ProductEventProxyDTO productEventProxyDTO = (ProductEventProxyDTO) obj;
            eventlog.setContent(String.valueOf(productEventProxyDTO));
            eventlog.setMsgId(productEventProxyDTO.getMsgId());
            eventlog.setSendTime(productEventProxyDTO.getSendTime());
        }

        if (eventLog.logTime().equals(LogTime.BEFORE_METHOD)){
            // record event pre-publish log
            eventlogService.updateEventLog(eventlog);
        }
        Object result = joinPoint.proceed();
        if (eventLog.logTime().equals(LogTime.AFTER_METHOD)){
            // record event receive log
            eventlogService.updateEventLog(eventlog);
        }
        return result;
    }
}
