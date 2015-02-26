package com.panqd.activemq;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SendMessage {
    
    private final static String JMS_SERVER = "tcp://192.168.111.139:61616";
    private final static String QUEUE_NAME = "panqd.queue";
    protected String messageBody = "<panqd>KingDomPan</panqd>";
    
    public void sendMessage() throws JMSException {
        Connection connection = null;
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
                    JMS_SERVER);
            connection = factory.createConnection();
            connection.start();
            Session session = (Session)connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageProducer mp = session.createProducer(destination);
            TextMessage message = session.createTextMessage(messageBody);
            message.setStringProperty("headname", "panqd");
            mp.send(message);
            System.out.println("消息发送成功");
            mp.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception {
        SendMessage sm = new SendMessage();
        sm.sendMessage();
    }

}
