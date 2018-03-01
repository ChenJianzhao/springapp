package org.demo.auto.xml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import javax.annotation.Resource;

import org.demo.auto.common.dao.IDispUserDao;
import org.demo.auto.common.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:auto/xml/applicationContext.xml"})
public class TestRedis {

	@Test
	public void testRedis() {
		try {
			
			Jedis conn = new Jedis("localhost");
			Set<String> result =  conn.keys("*");
			for(String str : result)
				System.out.println(str);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMySQL() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql:///test");
		dataSource.setUsername("root");
		dataSource.setPassword("");
		
		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
		User user = jdbcTemplate.queryForObject("select * from `user` where id = 1",  new RowMapper<User>() {
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                User user =  new User(id,username,password);

                return  user;
            }
		});
		System.out.println(user);
	}
	
	@Resource
    IDispUserDao dispUserDao = null;
	
	@Test
	public void testCache() {
		User user = dispUserDao.select(1);
		System.out.println(user);
	}
	
	@Test
	public void testEvictCache() {
		dispUserDao.delete(1);
		
	}
}
