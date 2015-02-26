package com.panqd.activemq;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ReceiveMessage {
    
    private final static String JMS_SERVER = "tcp://192.168.111.139:61616";
    private final static String QUEUE_NAME = "panqd.queue";

    public void receiveMessage() throws Exception {
        Connection connection = null;
        try {
            ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(
                    JMS_SERVER);
            connection = factory.createConnection();
            connection.start();
            Session session = (Session) connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(QUEUE_NAME);
            MessageConsumer mc = session.createConsumer(destination);
            this.consumeMessagesAndClose(connection, session, mc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void consumeMessagesAndClose(Connection connection, Session session,
            MessageConsumer mc) throws Exception {
        long start = System.currentTimeMillis();
        while (true) {
            Message message = mc.receive(1000);
            if (message != null) {
                onMessage(message);
            }
            // 一分钟后断开连接
            if(System.currentTimeMillis() - start > 1000 * 60) {
                break;
            }
        }
        System.out.println("断开连接");
        mc.close();
        session.close();
        connection.close();
    }

    public void onMessage(Message message) throws Exception {
        if (message instanceof TextMessage) {
            TextMessage txtMsg = (TextMessage) message;
            String msg = txtMsg.getText();
            System.out.println("Received:" + msg);
        }
    }

    public static void main(String[] args) throws Exception {
        ReceiveMessage rm = new ReceiveMessage();
        rm.receiveMessage();
    }
}
