package com.panqd.spring.bpp;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;


public class HelloWorldBean implements InitializingBean, DisposableBean {
    
    private String helloWorld;
    private User user;

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHelloWorld() {
        return this.helloWorld;
    }

    public void setHelloWorld(String helloWorld) {
        this.helloWorld = helloWorld;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("init");
        System.out.println(this.getHelloWorld());
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy");
        System.out.println(this.getHelloWorld());
    }
}
