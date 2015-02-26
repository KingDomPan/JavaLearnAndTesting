package com.panqd.activemq.spring.balance;

import java.io.Serializable;

public class SimpleMessageConsumer {
    
    public String receive(Serializable object) {
        System.out.println(object);
        System.out.println(Thread.currentThread().getName());
        return "SimpleMessageConsumer";
    }
    
}
