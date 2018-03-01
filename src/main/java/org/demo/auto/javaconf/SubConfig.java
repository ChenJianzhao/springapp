package org.demo.auto.javaconf;

import org.demo.auto.common.dao.DaoScanMark;
import org.demo.auto.common.entity.EntityScanMark;
import org.demo.auto.javaconf.aspect.AspectMark;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = {EntityScanMark.class, DaoScanMark.class, AspectMark.class})
public class   SubConfig {

    /**
     * 使用内嵌数据库 H2 作为数据源，JdbcTemplate 直接操作数据库
     * @return
     */
    @Bean
//    @Profile("dev")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:sql/scheme.sql")
                .build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
