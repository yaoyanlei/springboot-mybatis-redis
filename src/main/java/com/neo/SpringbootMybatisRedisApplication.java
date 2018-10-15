package com.neo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.neo.interceptor.MomentAccessCntInterceptor;
import com.neo.interceptor.OtherInterceptor;

/**
 * 项目启动配置类
 * @author yaoya
 */
@SpringBootApplication(scanBasePackages = { "com.neo" })
@MapperScan("com.neo.mapper")
@ImportResource("classpath:elastic-job.xml")
public class SpringbootMybatisRedisApplication extends WebMvcConfigurerAdapter implements ApplicationContextAware{
	
	private ApplicationContext applicationContext;
	
	@Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisRedisApplication.class, args);
	}
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(applicationContext.getBean(MomentAccessCntInterceptor.class))
        	.addPathPatterns("/user/**","/pet/**/community/feed/attention/list","/pet/v3/new_recommends/home/feed");
        registry.addInterceptor(applicationContext.getBean(OtherInterceptor.class)).addPathPatterns("/user/**");
	}
}