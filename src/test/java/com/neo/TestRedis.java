package com.neo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


public class TestRedis extends BaseTest {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {
		stringRedisTemplate.opsForValue().set("love", "qiqi");
		redisTemplate.opsForValue().set("like", "qiqi");
		System.err.println(stringRedisTemplate.opsForValue().get("love"));
		System.err.println(redisTemplate.opsForValue().get("like"));
	}

}
