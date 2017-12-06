package org.demo.explicit.common.entity;

/**
 * Created by cjz on 2017/12/6.
 */
public class User {

    String username = null;
    String password = null;
    
    public User() {
    }

    public User(String username, String password) {
	this.username = username;
	this.password = password;
    }

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
