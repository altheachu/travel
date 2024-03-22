package ecommerce.travel.aop;

import ecommerce.travel.utility.dto.OrderDetailProxyDTO;
import ecommerce.travel.utility.dto.OrderEventProxyDTO;
import ecommerce.travel.utility.entity.Eventlog;
import ecommerce.travel.utility.service.EventlogService;
import ecommerce.travel.utility.utils.EventlogConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.function.Consumer;

@Component
@Aspect
public class EventlogAspect {

    @Autowired
    private EventlogService eventlogService;

    // 作法一
//    @Pointcut("@annotation(ecommerce.travel.aop.EventLog)")
//    public void pointcut(){
//    }
//
//    @Around("pointcut()")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        EventLog eventLog = signature.getMethod().getAnnotation(EventLog.class);
//        Object result = joinPoint.proceed();
//        // record event pre-publish log
//        Eventlog eventlog = new Eventlog();
//        eventlog.setContent(joinPoint.getArgs()[0].toString());
//        eventlog.setMsgId((String) joinPoint.getArgs()[1]);
//        eventlog.setSendTime((String) joinPoint.getArgs()[2]);
//        eventlog.setType(eventLog.type());
//        eventlogService.updateEventLog(eventlog);
//        return result;
//    }
    @Around("@annotation(eventLog)")
    public Object around(ProceedingJoinPoint joinPoint, EventLog eventLog) throws Throwable {

        Eventlog eventlog = new Eventlog();
        OrderEventProxyDTO orderEventProxyDTO = (OrderEventProxyDTO) joinPoint.getArgs()[0];
        eventlog.setContent(orderEventProxyDTO.getOrderDetailProxyDTOList().toString());
        eventlog.setMsgId(orderEventProxyDTO.getMsgId());
        eventlog.setSendTime(orderEventProxyDTO.getSendTime());
        eventlog.setType(eventLog.type());

        if (eventLog.logTime().equals(LogTime.AFTER_METHOD)){
            // record event pre-publish log
            eventlogService.updateEventLog(eventlog);
        }
        Object result = joinPoint.proceed();
        if (eventLog.logTime().equals(LogTime.BEFORE_METHOD)){
            // record event receive log
            eventlogService.updateEventLog(eventlog);
        }
        return result;
    }
}
