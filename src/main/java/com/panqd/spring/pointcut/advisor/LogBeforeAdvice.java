package com.panqd.spring.pointcut.advisor;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.aop.MethodBeforeAdvice;

public class LogBeforeAdvice implements MethodBeforeAdvice {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    @Override
    public void before(Method method, Object[] args, Object target)
            throws Throwable {
        this.logger.log(Level.INFO, "before " + method.toString());
    }

}
