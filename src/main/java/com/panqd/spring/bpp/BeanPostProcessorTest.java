package com.panqd.spring.bpp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 在bean的依赖关系由Spring关系建立之后, 可以自定义一些bean
 * 的修正动作来修正相关的属性, 方法是实现BeanPostProcessor接口
 * @author KingDom
 */
public class BeanPostProcessorTest {
    
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("spring.xml");
        HelloWorldBean hw = (HelloWorldBean)app.getBean("helloWorldBean");
        System.out.println(hw.getHelloWorld());
        ((AbstractApplicationContext) app).registerShutdownHook();
    }
}

