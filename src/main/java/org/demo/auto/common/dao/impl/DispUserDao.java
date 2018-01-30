package org.demo.auto.common.dao.impl;

import javax.annotation.Resource;

import org.demo.auto.common.dao.IDispUserDao;
import org.demo.auto.common.entity.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Repository
public class DispUserDao implements IDispUserDao{

    User user = null;
    JdbcOperations jdbcTemplate;

    public User getUser() {
        return user;
    }

    @Resource
    public void setUser(User user) {
        this.user = user;
    }

    public JdbcOperations getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Resource
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void display(String caller) {
	    System.out.println(caller + " just called " + user);

	    user.setUsername("cjz");
	    user.setPassword("hash123456");

	    String insertSql = "insert into T_User (FUsername, FPassword) Values (?, ?)";
	    int i = jdbcTemplate.update(insertSql, user.getUsername(), user.getPassword());
        System.out.println("updated " + i);

        String querySql  = "select * from T_User where FUsername = ? ";
        User queryUser = jdbcTemplate.queryForObject(querySql, new Object[]{user.getUsername()}, new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("FID");
                String username = resultSet.getString("FUsername");
                String password = resultSet.getString("FPassword");
                User user =  new User(id,username,password);

                return  user;
            }
        });

        System.out.println(queryUser);
    }

}
