mustang - 批处理任务扩展
===
简介
---
mustang是一个后端批处理任务调度集成项目，主要目的是提供简单的批处理任务管理、调度功能。本项目基于Spring Batch Admin扩展而成，欢迎大牛参与改进！
	
源码结构说明
---
- batchconsole

	用于开发调试的web模块，将其部署到web服务器中启动，在浏览器中访问服务器即可进入Spring Batch Admin，例如：http://localhost:8080.

- hello-jar

	开发调试的jar包，用于测试以jar包方式发布自定义job。

- myjobs

	基于Spring Batch Admin的扩展，实现集群管理、任务调度、用户认证等功能。
	
功能介绍
---
- 集群节点注册

	运行myjobs的web项目在启动时会自动将当前web服务器当作一个节点注册到集群中，同一台机器可以同时运行多个web服务器，且他们是不同的节点。每个节点在启动时会生成一个ID，Executors菜单中展示的是集群中所有节点列表，用户可以通过主机名、IP来判断节点运行在哪台机器上。
	
- 集群节点资源监控

	每个节点都会自动提服务器资源使用情况更新到集群中，包括CPU、磁盘、内存、网络等信息。

- 集群任务配置

	集群的任务是web服务器启动时自动注册的，用户需要为任务设置Cron表达式、参数、最多实例等属性。设置Cron表达式后任务会被自动触发，并在集群中一个节点启动。
	
	当任务在集群中没有运行实例，并且没有可执行节点时才能从能集群中删除。
	
	任务的触发器可以被暂停、恢复，暂停状态的任务不会启动新的实例。
	
	停止任务是指将集群中任务的所有执行实例置为STOP状态，该功能并不代表能立即停止所有实例，这需要根据你的任务实现代码来考虑。

- 集群任务执行历史

	任务执行历史只记录了这个任务在集群中执行的起止时间、状态等简单的信息。
	
- 集群任务实例限制

	任务实例限制是指限制这个任务在集群中同一时间最多运行多少个实例，根据任务的实际需要来决定（0是不限制）。或许简单的数据可能由多个实例以最快速度处理完，或许重要数据永远只需要一个实例处理即可。
	
- 集群任务管理

	集群任务可以删除、清空历史记录、停止所有实例、暂停与恢复触发器等。
	
- 用户认证

	为了安全而增加的用户认证，待完善。

版本历史
---
- 1.5 （当前版本）

	增加用户认证，修复问题。
- 1.4

	增加历史记录，按启动时间排序。
- 1.3

	增加节点资源监控，修复消息通知的问题，修复节点掉线的问题。
- 1.2

	增加任务配置，自动触发，消息通知。
- 1.1

	修复BUG，增加集群管理。
- 1.0

	集成Spring Batch Admin。

使用方法
---
- 创建项目

	Job项目可以是jar包，也可以是war包。war包直接部署到web服务器，jar包可以用依懒或其他方式部署到web服务器中，增加如下配置文件:
	+ resource/conf/sys.properties
	+ WEB-INF/web.xml
	+ pom.xm（如果需要)
	
- 依懒列表(maven group:artifact:version)
	+ com.dimogo.open.mustang:myjobs:1.5
	+ org.springframework.batch:spring-batch-admin-manager:2.0.0.M1
	+ org.springframework.batch:spring-batch-admin-resources:2.0.0.M1
	+ org.springframework.batch:spring-batch-admin-domain:2.0.0.M1
	
- Maven仓库

	引入myjobs时需要配置dimogo的仓库，仓库地址为：http://mvn.dimogo.com:10010/repository/dimogo/

配置与文件
---
- maven配置

```
<repositories>
    <repository>
        <id>mvn</id>
        <url>http://repo.spring.io/release/</url>
    </repository>
    <repository>
        <id>dimogo</id>
        <url>http://mvn.dimogo.com:10010/repository/dimogo/</url>
    </repository>
</repositories>
```

- resource/conf/sys.properties
	
```
#zookeeper配置
zk.servers=<ZooKeeper节点列表>
zk.conn.timeout=10000
zk.session.timeout=10000
#集群节点资源更新间隔时间(毫秒)
executor.monitor.interval=300000
```
	
- WEB-INF/web.xml
	
```
<?xml version="1.0" encoding="ISO-8859-1"?>
<web-app xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="
			http://java.sun.com/xml/ns/j2ee
			http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd" version="2.4">
    <display-name>Batch Console</display-name>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath*:/org/springframework/batch/admin/web/resources/webapp-config.xml</param-value>
    </context-param>
    <listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    <listener>
        <listener-class>com.dimogo.open.myjobs.listener.InitSystemListener</listener-class>
    </listener>
    <filter>
        <filter-name>springSecurityFilterChain</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>springSecurityFilterChain</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <filter>
        <filter-name>shallowEtagHeaderFilter</filter-name>
        <filter-class>org.springframework.web.filter.ShallowEtagHeaderFilter</filter-class>
    </filter>
    <filter>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>shallowEtagHeaderFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <filter-mapping>
        <filter-name>hiddenHttpMethodFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
    <servlet>
        <servlet-name>Batch Servlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>classpath*:/org/springframework/batch/admin/web/resources/servlet-config.xml</param-value>
        </init-param>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>Batch Servlet</servlet-name>
        <url-pattern>/*</url-pattern>
    </servlet-mapping>
    <servlet>
        <servlet-name>Application Context Catch Servlet</servlet-name>
        <servlet-class>com.dimogo.open.myjobs.servlet.ApplicationContextCatchServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
</web-app>
```

- pom.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.dimogo.open.test</groupId>
    <artifactId>batch</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>war</packaging>
    <properties>
        <spring.batch.admin.version>2.0.0.M1</spring.batch.admin.version>
        <druid.version>1.0.9</druid.version>
        <mybatis.version>3.2.8</mybatis.version>
        <mybatis.spring.version>1.2.2</mybatis.spring.version>
    </properties>
    <repositories>
        <repository>
            <id>mvn</id>
            <url>http://repo.spring.io/release/</url>
        </repository>
        <repository>
            <id>dimogo</id>
            <url>http://mvn.dimogo.com:10010/repository/dimogo/</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>com.dimogo.open.mustang</groupId>
            <artifactId>myjobs</artifactId>
            <version>1.5</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.batch</groupId>
            <artifactId>spring-batch-admin-manager</artifactId>
            <version>${spring.batch.admin.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.batch</groupId>
            <artifactId>spring-batch-admin-resources</artifactId>
            <version>${spring.batch.admin.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.batch</groupId>
            <artifactId>spring-batch-admin-domain</artifactId>
            <version>${spring.batch.admin.version}</version>
        </dependency>
   </dependencies>
</project>
```
