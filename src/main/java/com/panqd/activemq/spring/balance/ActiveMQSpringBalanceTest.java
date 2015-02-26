package com.panqd.activemq.spring.balance;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.panqd.activemq.spring.balance.SimpleMessageSender;

public class ActiveMQSpringBalanceTest {
    
    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        ApplicationContext ac = new ClassPathXmlApplicationContext("activemq-balance.xml");
        SimpleMessageSender sms = (SimpleMessageSender)ac.getBean("messageSender");
        for (int i = 0; i < 20; i++) {
            sms.send(new User(String.valueOf(i), String.valueOf(i)));
        }
    }
}
