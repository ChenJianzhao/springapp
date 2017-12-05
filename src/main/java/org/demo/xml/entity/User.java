package org.demo.xml.entity;

import org.springframework.stereotype.Component;

/**
 * Created by cjz on 2017/12/6.
 */
@Component
public class User {

    String username = null;
    String password = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "[" + username + "]" + super.toString();
    }
}
