package com.panqd.mybatis.spring;

import java.util.List;

import com.panqd.mybatis.model.NotifyLog;

public interface NotifyLogDao {
    public List<NotifyLog> queryAll();
}
