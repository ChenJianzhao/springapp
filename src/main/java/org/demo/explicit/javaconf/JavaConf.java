package org.demo.explicit.javaconf;

import org.demo.explicit.common.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cjz on 2017/12/6.
 */
@Configuration
public class JavaConf {
    
    @Bean
    public User user() {
	User user =  new User();
	user.setUsername("explicit javaconf user");
	return user;
    }
}
