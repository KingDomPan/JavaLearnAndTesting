package com.panqd.activemq.spring;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class SimpleMessageSender {

    private JmsTemplate jmsTemplate;

    public void sendMessage(final Object obj) throws JMSException {
        for (int i = 0; i < 10; i++) {
            this.jmsTemplate.send(new MessageCreator() {
                @Override
                public Message createMessage(Session session)
                        throws JMSException {
                    Message message = session.createTextMessage(obj.toString());
                    message.setStringProperty("panqd",
                            "kingdom_" + System.currentTimeMillis());
                    return message;
                }
            });
        }
    }

    public JmsTemplate getJmsTemplate() {
        return this.jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

}
