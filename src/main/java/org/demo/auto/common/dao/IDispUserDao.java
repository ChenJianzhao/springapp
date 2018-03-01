package org.demo.auto.common.dao;

import org.demo.auto.common.entity.User;
import org.demo.auto.common.redis.RedisCache;
import org.demo.auto.common.redis.RedisEvict;

public interface IDispUserDao {

    void display(String caller);
    
    @RedisCache(type = User.class)
    User select(int id);
    
    @RedisEvict(type = User.class)
    void delete(int id);
}
