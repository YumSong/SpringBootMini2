package com;
import org.apache.log4j.Logger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Hello world!
 * 
 */

// 此注解表示SpringBoot启动类
@SpringBootApplication
// 此注解表示动态扫描DAO接口所在包
@MapperScan(basePackages = "com.**.dao")
public class App extends SpringBootServletInitializer {

	private static Logger logger = Logger.getLogger(App.class);

	public static void main(String[] args) {
		logger.info("=================开始成功=================");
		SpringApplication.run(App.class, args);
		logger.info("=================启动成功=================");
	}
	

	/**
	 * 如果要发布到自己的Tomcat中的时候，需要继承SpringBootServletInitializer类，并且增加如下的configure方法。
	 * 如果不发布到自己的Tomcat中的时候，就无需上述的步骤
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}
}