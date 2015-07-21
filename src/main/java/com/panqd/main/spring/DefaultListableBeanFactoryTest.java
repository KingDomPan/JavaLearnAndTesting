package com.panqd.main.spring;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

public class DefaultListableBeanFactoryTest {
    public static void main(String[] args) {
        // 定义资源
        ClassPathResource cpr = new ClassPathResource("applicationContext.xml");
        // dlbf只是单纯功能上的IOC容器, 因此需要定义一个xbdr来处理资源
        DefaultListableBeanFactory dlbf = new DefaultListableBeanFactory();
        // 实际上是一个BeanDefinitionRegister
        XmlBeanDefinitionReader xbdr = new XmlBeanDefinitionReader(dlbf);
        // 在内部使用BeanDefinitionRegister.register来注册cpr资源中定义的bean
        xbdr.loadBeanDefinitions(cpr);
    }
}
