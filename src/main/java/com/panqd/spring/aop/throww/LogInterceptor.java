package com.panqd.spring.aop.throww;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LogInterceptor implements MethodInterceptor {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        logger.log(Level.INFO, "MethodInterceptor______" + invocation.getMethod());
        Object result = null;
        try {
            result = invocation.proceed();
        } catch (Exception e) {
            this.logger.info(e.toString());
        } finally {
            this.logger.info("method ends____" + invocation.getMethod());
        }
        return result;
    }

}
