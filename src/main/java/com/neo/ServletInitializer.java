package com.neo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * war部署在容器中
 * 继承SpringBootServletInitializer
 * 并指明配置类 
 * @author yaoya
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringbootMybatisRedisApplication.class);
	}

}
