package com.panqd.spring.bpp;

import java.lang.reflect.Field;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class UpperCaseModifier implements BeanPostProcessor {

    /**
     * 在bean类被初始化之前被执行
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("postProcessBeforeInitialization");
        Field[] fields = bean.getClass().getDeclaredFields();
        for(Field f: fields) {
            if(f.getType().equals(String.class)) {
                f.setAccessible(true);
                try {
                    String origin = (String) f.get(bean);
                    String originUpperCase = origin.toUpperCase();
                    f.set(bean, originUpperCase);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    /**
     * 在bean类被初始化之后被执行
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("postProcessAfterInitialization");
        return bean;
    }
    
}