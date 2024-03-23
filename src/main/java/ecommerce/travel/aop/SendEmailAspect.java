package ecommerce.travel.aop;

import ecommerce.travel.customer.model.CustomerModel;
import ecommerce.travel.customer.service.CustomerService;
import ecommerce.travel.order.model.OrderModel;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.Map;

@Component
@Aspect
public class SendEmailAspect {

    @Autowired
    private CustomerService customerService;

    @Pointcut("@annotation(SendEmail)")
    public void pointcut(){
    }

    private static final Map<Timing, String> SUBJECT_TEMPLATE_MAP;
    private static final Map<Timing, String> MESSAGE_TEMPLATE_MAP;

    static {
        SUBJECT_TEMPLATE_MAP = new EnumMap<>(Timing.class);
        SUBJECT_TEMPLATE_MAP.put(Timing.AfterOrder, "Check your order NO. %s");

        MESSAGE_TEMPLATE_MAP = new EnumMap<>(Timing.class);
        MESSAGE_TEMPLATE_MAP.put(Timing.AfterOrder, "Hi, %s. The detail of your order detail is as follows...");
    }

    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void after(JoinPoint joinPoint, Object result) throws Throwable {

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SendEmail sendEmail = signature.getMethod().getAnnotation(SendEmail.class);
        String subject = null;
        String content = null;
        switch(sendEmail.value()){
            case AfterOrder:
                OrderModel orderModel = (OrderModel) joinPoint.getArgs()[0];
                CustomerModel customerModel = customerService.findCustomerById(orderModel.getCustomerId());
                OrderModel completedOrderModel = (OrderModel) result;
                subject = String.format(SUBJECT_TEMPLATE_MAP.get(sendEmail.value()), completedOrderModel.getOrderId());
                content = String.format(MESSAGE_TEMPLATE_MAP.get(sendEmail.value()), customerModel.getName());
        }
        System.out.println("subject content: " + subject);
        System.out.println("email content: " + content);
    }

}
