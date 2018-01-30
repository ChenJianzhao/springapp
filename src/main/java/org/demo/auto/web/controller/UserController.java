package org.demo.auto.web.controller;

import org.demo.auto.common.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mvc")
public class UserController {

    @RequestMapping("/hello")
    public String hello(){
        return "loginPage";
    }


    /**
     * 描述：成员域自动注入参数
     *
     * 浏览器 url http://localhost:8080/mvc/user?username=1
     * @param user url 中的参数会自动注入到方法参数 User 对象的成员域中
     * @return
     */
    @RequestMapping("/user")
    public String user(User user){

        if(user!=null){
            System.out.println(user.getUsername() + ":" + user.getPassword());
        }else
            System.out.println("null user");

        return "loginPage";
    }

    /**
     * 描述：测试转发
     * @return
     */
    @RequestMapping("/forward")
    public String forward() {
        return "forward:/mvc/hello";
    }

    /**
     * 注解 @ResponseBody 返回 Json 数据需要引入三个 jackson 包并添加配置
     *
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping
     * @see org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter
     * @see org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
     *
     * @return
     */
    @RequestMapping("/userJson")
    @ResponseBody
    public User userJson() {
        return new User("123","hash");
    }
}
