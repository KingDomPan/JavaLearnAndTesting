package com.panqd.spring.aop.after;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.aop.AfterReturningAdvice;

public class LogAfterAdvice implements AfterReturningAdvice {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void afterReturning(Object returnValue, Method method,
            Object[] args, Object target) throws Throwable {
        logger.log(Level.INFO, "afterReturning " + method.toString());
    }

}
