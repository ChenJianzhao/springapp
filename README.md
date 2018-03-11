

## Spring 使用Demo

## 包结构介绍

**src/main/java**

`org.demo.auto` 使用 Java @Bean/@Component/@Resource 等注解自动扫描发现和装配 Bean

`org.demo.explicit` 使用 xml 显式声明和装配 Bean（区别于自动发现Bean）（此包已删除）

`org.demo.auto.javaconf` 使用 Java 配置类声明和装配 Bean

`org.demo.auto.xml` 使用 xml 文件声明和装配 Bean 相关的一些类



**src/main/resources**

`auto.xml` 使用 xml 文件声明和装配 Bean 的配置文件（用于源码无法添加注解声明和装配的情况）



**src/test/java**

`org.demo.auto.javaconf` 使用 Java 配置类启动容器的测试用例

`org.demo.auto.xml` 使用 xml 配置文件启动容器的测试用例



**src/main/webapp**

web 相关的一些 web应用配置文件，SpringMVC 配置文件，jsp 视图文件等。



## 装配 Bean

当描述 bean 如何进行装配时，Spring 具有非常大的灵活性，它提供了三种主要的装配机制：

- 在 XML 中进行显式配置。


- 在 Java 中进行显式配置。


- 隐式的 bean 发现机制和自动装配。

**注：可以使用 xml 配置文件和 Java 配置类相结合的方式配置 Spring 应用**



### 自动化装配 Bean

1. 使用 @Component、@Repository、@Service、@Controller等注解可被发现的 Bean

2. 设置组件扫描的基础包

   ```java
   @Configuration
   //@ComponentScan(basePackages ={"org.demo.auto.common","org.demo.auto.javaconf.aspect"})
   @ComponentScan(basePackageClasses = {EntityScanMark.class, DaoScanMark.class, AspectMark.class})
   public class  JavaConfig {
       
   }
   ```

   - basePackages 属性可以使用 String 类型配置基础包名，但这种方法类型不安全
   -  basePackageClasses 属性指定基础包中所包含的类或接口，这些类所在的包将会作为组件扫描的基础包。可以在包中创建一个用来进行扫描的空标记接口（marker interface）。通过标记接口的方式，你依然能够保持对重构友好的接口引用，但是可以避免引用任何实际的应用程序代码（在稍后重构中，这些应用代码有可能会从想要扫描的包中移除掉）。

3. 在方法上使用 @Resource、@Autowired 注解自动装配 Spring 上下文中的 Bean。	

   ```java
   @Repository
   public class DispUserDao implements IDispUserDao{

       User user = null;

       public User getUser() {
           return user;
       }

       @Resource
       public void setUser(User user) {
           this.user = user;
       }
   }
   ```



### 通过 Java 代码装配 Bean

1. 要在 JavaConfig 中声明 bean，我们需要编写一个方法，这个方法会创建所需类型的实例，然后给这个方法添加@Bean 注解。默认情况下，bean 的 ID 与带有@Bean 注解的方法名是一样的。
2. 借助 JavaConfig 实现注入。在传入的参数列表中加入需要装配的 Bean id，Spring 会在上下文中寻找符合条件的 Bean 注入。

```java
@Configuration
@ComponentScan(basePackageClasses = {EntityScanMark.class, DaoScanMark.class, AspectMark.class})
public class JavaConfig {

    /**
     * 使用内嵌数据库 H2 作为数据源，JdbcTemplate 直接操作数据库
     * @return
     */
    @Bean
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
```



### 使用 xml 文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="
       http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

	<!-- xml 配置文件同样可以设置扫描基础包 -->
    <context:component-scan base-package="org.demo.auto.common.entity" />
    <context:component-scan base-package="org.demo.auto.common.dao" />
    <context:component-scan base-package="org.demo.auto.xml.aspect" />

    <!-- bean 的声明和装配先省略吧哎，懒得写 -->
    <!-- bean 的声明和装配先省略吧哎，懒得写 -->
    <!-- bean 的声明和装配先省略吧哎，懒得写 -->
</beans>
```



## 面相切面的 Spring

### 启用 AspectJ 自动代理

```java
@Configuration
@EnableAspectJAutoProxy
public class JavaConf {
    // ......
}
```

或

```xml
<!-- 【*】启用 aspect 自动代理 -->
<aop:aspectj-autoproxy />
```



### 使用注解创建切面

```java
package org.demo.auto.javaconf.aspect;

/**
 * 日志记录切面类
 */
@Aspect
@Component
public class LogAspect {

    @Pointcut("execution(* org.demo.auto.common.dao.impl.*.*(..))")
    public void pointCut() {};
    
    @Before(value = "pointCut()")
    public void logBefore(JoinPoint jp) {
	System.out.println("log before");
    }
    
    @After(value = "pointCut()")
    public void logAfter(JoinPoint jp) {
	System.out.println("log after");
    }
    
    @Around(value = "pointCut()")
    public void logAround(ProceedingJoinPoint jp) throws Throwable {
	System.out.println("log around before");
	jp.proceed();
	System.out.println("log around after");
    }
    
    @AfterReturning(value = "pointCut()")
    public void logAfterReturning() {
	System.out.println("log around after returning");
    }
}
```



### 在 xml 中声明切面

```xml
<!-- 启用 aspect 自动代理 -->
<aop:aspectj-autoproxy />

<!-- xml 方式配置切面 -->
<aop:config >
    <aop:aspect ref="logAspect" >
        <aop:pointcut id="log" expression="execution(* org.demo.auto.common.dao.impl.*.*(..))" />
        <aop:before method="logBefore" pointcut-ref="log" />
        <aop:after method="logAfter" pointcut-ref="log" />
        <aop:around method="logAround" pointcut-ref="log" />
    </aop:aspect>
</aop:config>
```







