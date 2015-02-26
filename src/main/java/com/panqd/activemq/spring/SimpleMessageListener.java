package com.panqd.activemq.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

public class SimpleMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println(this);
        System.out.println(Thread.currentThread().getName());
        System.out.println(message.toString());
        try {
            System.out.println(message.getStringProperty("panqd"));
        } catch (JMSException e) {
            e.printStackTrace();
        }
        System.out.println("__________________________________");
    }

}
