package com.neo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.neo.entity.UserDomain;
import com.neo.mapper.UserMapper;
import com.neo.service.UserService;

@Service(value = "userService")
public class UserServiceImpl implements UserService
{
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisTemplate redisTemplate;

	
	
	/**
	 * @Cacheable 在Spring Boot一般标注在service层的实现类的方法上
	 * 	常用两个属性value和key
	 *  当代码从controller层或者其他的service层调用该方法时（注意：在同一类中调用带有redis注解的方法时，将不会触发缓存），
	 *  redis会检测缓存中是否有相对应的缓存，如果有，数据会从缓存取，
	 *  否则会进入方法体中读取数据。并且return后面跟的数据就是要缓存的数据
	 */
	//@Cacheable("findAllUser")
	@Override
	public List<UserDomain> findAllUser(int pageNum, int pageSize) {
		
		//将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        List<UserDomain> list = userMapper.selectUsers();
        //缓存list 
        redisTemplate.opsForList().leftPush("user:list", list);
         return list;
		
	}
	
	/**
	 * value必须，指定 cache名称，该方法返回结果被缓存到该cache
	 * key属性是用来指定Spring缓存方法返回结果时所对应的key值的
	 * condition：指定发生条件 支持SpringEL表达式
	 */
	@Cacheable(key="'user::'+ #userName + '_boco_'",value="db0")
	@Override
	public UserDomain getUserDomain(String userName){
		return userMapper.selectUser(userName);
	}

	
}
