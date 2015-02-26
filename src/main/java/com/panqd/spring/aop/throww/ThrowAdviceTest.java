package com.panqd.spring.aop.throww;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * org.springframework.aop.framework.ProxyFactoryBean
 * 会被BeanFactory或者ApplicationContext用来建立代理对象
 * 
 * interceptorNames设置Advice, 不指定代理方法时, 默认是接口方法
 * @author KingDom
 */
public class ThrowAdviceTest {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("aop.xml");
        IHello helloProxy = (IHello) app.getBean("helloProxyThrow");
        try {
            helloProxy.hello("kingdom");
        } catch (Throwable e) {
            System.out.println("Exception");
        }
    }
}
