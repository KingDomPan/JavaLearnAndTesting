package com.panqd.mybatis.main;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.panqd.mybatis.model.NotifyLog;

public class MybatisMain {
    private static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sessionFactory = null;
        String resource = "configuration.xml";
        try {
            sessionFactory = new SqlSessionFactoryBuilder().build(Resources
                    .getResourceAsReader(resource));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
    
    public static void main(String[] args) {
        SqlSession session = getSqlSessionFactory().openSession();
        session.select("mapper.notifyLogMapper.selectAll", new ResultHandler() {
            @Override
            public void handleResult(ResultContext rc) {
                NotifyLog map = (NotifyLog)rc.getResultObject();
                System.out.println(map.getId());
                System.out.println(map.getTitle());
                System.out.println(map.getContent());
                System.out.println(map.getSendDate());
                System.out.println("________________________");
            }
        });
    }
}
