package com.panqd.activemq.spring.balance;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.jms.core.JmsTemplate;

public class SimpleMessageSender {

    private List<JmsTemplate> templates = null;

    private AtomicInteger current = new AtomicInteger(0);

    // 轮询算法解决负载均衡
    private JmsTemplate findJmsTemplate() {
        int cur = this.current.getAndIncrement();
        int index = cur % this.templates.size();
        return this.templates.get(index);
    }

    public List<JmsTemplate> getTemplates() {
        return this.templates;
    }

    public void setTemplates(List<JmsTemplate> templates) {
        this.templates = templates;
    }
    
    public void send(Serializable object) {
        this.findJmsTemplate().convertAndSend(object);
    }

}
