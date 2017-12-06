package org.demo.auto.common.dao.impl;

import org.demo.auto.common.dao.INewDispUserDao;
import org.springframework.stereotype.Repository;

@Repository
public class NewDispUserDao implements INewDispUserDao{

    public void newDisplay() {
	System.out.println("new display method");
    }

}
