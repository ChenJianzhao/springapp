package org.demo.javaconf.config;

import org.demo.javaconf.entity.ComponentScanMark;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cjz on 2017/12/6.
 */
@Configuration
@ComponentScan(basePackageClasses = {ComponentScanMark.class})
public class JavaConf {


}
