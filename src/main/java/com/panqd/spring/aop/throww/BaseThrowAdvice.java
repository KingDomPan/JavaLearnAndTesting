package com.panqd.spring.aop.throww;

import java.lang.reflect.Method;
import java.util.logging.Logger;

import org.springframework.aop.ThrowsAdvice;

public class BaseThrowAdvice implements ThrowsAdvice {
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    public void afterThrowing(Method m, Object[] args, Object target, Throwable subclass) {
        this.logger.info("afterThrowing____" + m.toString() + " " + subclass.toString());
    }
}
