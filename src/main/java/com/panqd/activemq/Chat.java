package com.panqd.activemq;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;

public class Chat implements MessageListener {

    private TopicSession pubSession;
    private TopicPublisher publisher;
    private TopicConnection connection;
    private String username;

    private Chat(String topicFactory, String topicName, String username)
            throws Exception {
        InitialContext ctx = new InitialContext();
        TopicConnectionFactory factory = (TopicConnectionFactory) ctx
                .lookup(topicFactory);
        TopicConnection connection = factory.createTopicConnection();
        TopicSession pubSession = connection.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);
        TopicSession subSession = connection.createTopicSession(false,
                Session.AUTO_ACKNOWLEDGE);
        Topic chatTopic = (Topic) ctx.lookup(topicName);
        TopicPublisher publisher = pubSession.createPublisher(chatTopic);
        TopicSubscriber subscriber = subSession.createSubscriber(chatTopic,
                null, true);
        subscriber.setMessageListener(this);

        this.connection = connection;
        this.publisher = publisher;
        this.pubSession = pubSession;
        this.username = username;

        this.connection.start();
    }

    protected void writeMessage(String text) throws JMSException {
        TextMessage message = this.pubSession.createTextMessage();
        message.setText(this.username + ":" + text);
        this.publisher.send(message);
    }

    public void close() throws JMSException {
        this.connection.close();
    }

    public static void main(String[] args) throws Exception {
        Chat chat = new Chat("TopicCF", "topic1", null);
        // Read from command line
        BufferedReader commandLine = new java.io.BufferedReader(
                new InputStreamReader(System.in));
        // Loop until the word "exit" is typed
        while (true) {
            String s = commandLine.readLine();
            if (s.equalsIgnoreCase("exit")) {
                chat.close(); // close down connection
                System.exit(0);// exit program
            } else {
                chat.writeMessage(s);
            }
        }
    }

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println("接收到的消息: " + text);
        } catch (JMSException jmse) {
            jmse.printStackTrace();
        }
    }

}
