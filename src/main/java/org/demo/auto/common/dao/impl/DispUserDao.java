package org.demo.auto.common.dao.impl;

import javax.annotation.Resource;

import org.demo.auto.common.dao.IDispUserDao;
import org.demo.auto.common.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class DispUserDao implements IDispUserDao{

    User user = null;
    
    public User getUser() {
        return user;
    }

    @Resource
    public void setUser(User user) {
        this.user = user;
    }

    
    public void display() {
	System.out.println(user);
    }

}
