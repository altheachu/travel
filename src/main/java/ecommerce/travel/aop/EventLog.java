package ecommerce.travel.aop;

import ecommerce.travel.utility.utils.EventlogConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EventLog {

    LogTime logTime();

    String type();

}
