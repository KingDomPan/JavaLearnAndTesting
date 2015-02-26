package com.panqd.activemq.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActiveMQSpringTest {
    
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("activemq.xml");
        SimpleMessageSender sms = (SimpleMessageSender)ac.getBean("messageSender");
        sms.sendMessage("KingDomPan");
    }
    
}
