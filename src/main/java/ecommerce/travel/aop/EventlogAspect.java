package ecommerce.travel.aop;

import ecommerce.travel.utility.dto.OrderEventProxyDTO;
import ecommerce.travel.utility.entity.Eventlog;
import ecommerce.travel.utility.service.EventlogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EventlogAspect {

    @Autowired
    private EventlogService eventlogService;

    @Around("@annotation(eventLog)")
    public Object around(ProceedingJoinPoint joinPoint, EventLog eventLog) throws Throwable {

        Eventlog eventlog = new Eventlog();
        OrderEventProxyDTO orderEventProxyDTO = (OrderEventProxyDTO) joinPoint.getArgs()[0];
        eventlog.setContent(orderEventProxyDTO.getOrderDetailProxyDTOList().toString());
        eventlog.setMsgId(orderEventProxyDTO.getMsgId());
        eventlog.setSendTime(orderEventProxyDTO.getSendTime());
        eventlog.setType(eventLog.type());

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
