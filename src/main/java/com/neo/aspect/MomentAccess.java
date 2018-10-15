package com.neo.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented				//说明该注解将被包含在javadoc中
@Retention(RetentionPolicy.RUNTIME)//@Retention: 定义注解的保留策略,- 注解会在class字节码文件中存在，在运行时可以通过反射获取到
@Target({ElementType.METHOD,ElementType.PARAMETER})//@Target：定义注解的作用目标- 方法和方法参数
@Inherited				//说明子类可以继承父类中的该注解
public @interface MomentAccess {
	
	/**
	 * 新增的访问量
	 * @return
	 */
	int accessCnt() default 0;
	
	 /**
	   * 如果注解只有一个属性，那么肯定是赋值给该属性。
	   * 如果注解有多个属性，而且前提是这多个属性都有默认值，那么你不写注解名赋值，会赋值给名字为“value”这属性。 int value() default 0
	   * 如果注解有多个属性，其中有没有设置默认值的属性，那么当你不写属性名进行赋值的时候，是会报错的。
	   */
}
