package org.demo.auto.javaconf;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by cjz on 2017/12/6.
 */
@Configuration
@EnableAspectJAutoProxy
@Import({SubConfig.class})
//@ImportResource({"classpath:xml/subApplicationContext.xml"})
public class JavaConf {
    
}
