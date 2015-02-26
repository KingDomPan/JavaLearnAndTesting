package com.panqd.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 这样的代理对象称之为切面, 一个动作或者服务, 比如记录日志, 权限验证
 * @author KingDom
 */
public class LogHandler implements InvocationHandler {

    private Logger logger = Logger.getLogger(this.getClass().getName());
    
    private Object delegate;
    
    public Object bind(Object delegate) {
        System.out.println("delegate_____" + delegate);
        this.delegate = delegate;
        Object obj = Proxy.newProxyInstance(delegate.getClass().getClassLoader(), 
                delegate.getClass().getInterfaces(), this);
        System.out.println("return Proxy.newProxyInstance......" + obj);
        return obj;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result = null;
        try {
            this.log("method before here........" + method);
            result = method.invoke(this.delegate, args);
            this.log("method finish here........");
        } catch (Exception e) {
            this.log(e.toString());
        }
        return result;
    }
    
    private void log(String msg) {
        this.logger.log(Level.INFO, msg);
    }

}
