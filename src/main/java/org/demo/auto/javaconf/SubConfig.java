package org.demo.auto.javaconf;

import org.demo.auto.common.dao.DaoScanMark;
import org.demo.auto.common.entity.EntityScanMark;
import org.demo.auto.javaconf.aspect.AspectMark;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {EntityScanMark.class, DaoScanMark.class, AspectMark.class})
public class SubConfig {

}
