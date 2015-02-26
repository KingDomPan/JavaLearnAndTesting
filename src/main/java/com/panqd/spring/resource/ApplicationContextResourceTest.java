package com.panqd.spring.resource;

import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

/**
 * 1. 实现了ResourceLoader接口, 可以取得资源
 * 2. 实现了MessageResource接口, getMessage的各种版本方法来取得文字消息的资源文件, 实现国际化
 * 3. Spring应用程序执行期间, 会发布一系列的事件, 事件类型都是ApplicationEvent的子类
 *      ContextClosedEvent 在ApplicationContext关闭时发布事件
 *      ContextRefreshEvent 在ApplicationContext初始或者refresh时发布事件
 *      RequestHandledEvent 在web程序中, 当请求被处理时, ApplicationContext会发布此事件
 * 4. 事件监听接口 ApplicationListener
 *      实现类 ConsoleLinster 仅仅在Debug使用, 会在文字模式下显示记录(log)ApplicationContext的相关消息
 *      实现类 PerformanceMonitorLisnter 在web应用程序中, 搭配ApplicationContext使用, 可记录请求的回应时间
 *      实现类 
 *      
 * 5. 事件传播, ApplicationContext.publicEvent()
 *      
 * @author KingDom
 */
public class ApplicationContextResourceTest {

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception {
        /**
         * 取得资源
         */
        ApplicationContext app = new ClassPathXmlApplicationContext();
        Resource rs = app.getResource("classpath:jdbc.properties");
        System.out.println(rs.getFilename());
        System.out.println(rs.contentLength());
        
        /**
         * 解析消息
         */
        app = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println(app.getMessage("bbb", null, Locale.getDefault()));
        System.out.println(app.getMessage("bbb", null, Locale.US));
        
        /**
         * 事件传播
         */
        app.publishEvent(new ContextClosedEvent(app));
    }
    
}
