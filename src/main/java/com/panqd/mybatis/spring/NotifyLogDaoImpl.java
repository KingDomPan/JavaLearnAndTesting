package com.panqd.mybatis.spring;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.panqd.mybatis.model.NotifyLog;

@Repository
public class NotifyLogDaoImpl implements NotifyLogDao {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate; 
    
    @Override
    public List<NotifyLog> queryAll() {
        return this.sqlSessionTemplate.selectList("selectAll");
    }
    
}
