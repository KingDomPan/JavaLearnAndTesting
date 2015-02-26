package com.panqd.mybatis.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.panqd.mybatis.model.NotifyLog;
import com.panqd.mybatis.spring.NotifyLogDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MybatisTest {

    @Autowired
    public NotifyLogDao notifyLogDao;

    @Test
    public void testQueryAll() {
        List<NotifyLog> nls = notifyLogDao.queryAll();
        if (nls != null) {
            for (int i = 0; i < nls.size(); i++) {
                System.out.println(nls.get(i));
            }
        }
    }
}
