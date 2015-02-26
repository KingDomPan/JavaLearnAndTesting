package com.panqd.spring.bpp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * 在BeanFactory载入bean定义文件的所有内容, 但是还没正式生产bean实例之前,
 * 可以对BeanFactory进行一些处理, 方法是实现BeanFactoryPostProcessor接口
 * BeanFactoryPostProcessor
 *      接口方法 postProcessBeanFactory
 *      实现类 PropertyPlaceholderConfigurer 将配置信息移出到.properties文件
 *      实现类 PropertyOverrideConfigurer 优先使用.properties文件中配置的值
 *      实现类 CustomEditorConfigurer 
 *          读取实现java.beans.PropertyEditor接口(java.beans.PropertyEditorSupport类)的类, 并按其中的实现.
 *          将字符串转换为指定的值
 * @author KingDom
 */
public class BeanFactoryPostProcessorTest {
    
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        ApplicationContext app = new ClassPathXmlApplicationContext("spring.xml");
        HelloWorldBean hw = (HelloWorldBean)app.getBean("helloWorldBean");
        System.out.println(hw.getHelloWorld());
        System.out.println(hw.getUser().getUsername());
        ((AbstractApplicationContext) app).registerShutdownHook();
    }
}

