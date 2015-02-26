package com.panqd.spring.pointcut.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 之前所有定义的advice都是织入到代理接口方法的执行前后的, 或者在执行方法过程中异常发生, 事实上还可以定义更细部的织入细节.
 * Pointcut定义了感兴趣的Jointcut(Advice的应用时机) 在Spring中, 使用PointcutAdvisor提供Pointcut
 * PointcutAdvisor --> Advisor Spring内建的Pointcut都有对应的PointcutAdvisor
 * NameMatchMethodPointcutAdvisor 用于提供Spring中静态的Pointcut实例
 * 可使用表达式指定Advice应用目标上的方法名称. * 通配符
 * @author KingDom
 */
public class NameMatchMethodPointcutAdvisorTest {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("aop.xml");
        IHello helloProxy = (IHello) app.getBean("helloProxyAdvisor");
        helloProxy.helloNewble("kingdom");
        helloProxy.helloMaster("kingdom");
    }
}
