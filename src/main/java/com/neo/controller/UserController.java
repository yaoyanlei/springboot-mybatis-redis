package com.neo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.neo.entity.UserDomain;
import com.neo.service.UserService;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/user")
public class UserController {
	
	protected static Logger logger = LoggerFactory.getLogger(UserController.class);

	
	@Autowired
	private UserService userService;
	
	
	@ResponseBody
    @GetMapping("/all")
    public Object findAllUser(int pageNum, int pageSize) {
        //开始分页
        PageHelper.startPage(pageNum,pageSize);
        return userService.findAllUser(pageNum,pageSize);
    }
	
	/**
	 * 缓存user
	 * @param userName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/user")
	public UserDomain getUser(String userName){
		return userService.getUserDomain(userName);
	}
	
	
	@GetMapping("/hello")
    public Object hello() {
        return "hello";
    }
	
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name = "stringRedisTemplate")
    ValueOperations<String, String> valueOperationStr;


    @ResponseBody
    @RequestMapping("/redis/string/get")
    public String getKey(String key) {
    	logger.debug("redis取值：key="+key);
        return valueOperationStr.get(key);
    }
    
    @RequestMapping("/redis/string/set")
    public String setKeyAndValue(String key, String value) {
        valueOperationStr.set(key, value);
        return "Set Ok";
    }
    
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    ValueOperations<String, Object> valOps;

    @RequestMapping("/redis/obj/set")
    public void save(UserDomain user) {
        valOps.set(user.getUserId(), user);
    }

    @RequestMapping("/redis/obj/get")
    public UserDomain getPerson(String id) {
        return (UserDomain) valOps.get(id);
    }
    
    
    @ResponseBody
    @RequestMapping("/testredis")
    public String testRedis(){
//    	stringRedisTemplate.opsForValue().set("love", "qiqi");  
//	    redisTemplate.opsForValue().set("like", "qiqi");  
//	    System.out.println(stringRedisTemplate.opsForValue().get("love"));  
//	    System.out.println(redisTemplate.opsForValue().get("like")); 
	    return "set ok";
    }

    @Autowired
    private Jedis jedis;
    
    @ResponseBody
    @RequestMapping("/jedistest")
    public String jedistest(){
    	 Map<String , String> map = new  HashMap<String,String>();
    	 map.put("username", "yaoyanei");
    	 map.put("age", "22");
    	 map.put("email", "yyl@163.com");
    	 
    	 //存储map   jedis.hmset
    	 jedis.hmset("moment_cnt", map);
    	 //map key的个数   jedis.hlen
         System.out.println("map的key的个数" + jedis.hlen("moment_cnt"));
         // map 获取key的set   jedis.hkeys
         System.out.println("map的key" + jedis.hkeys("moment_cnt"));
         // map 获取value的list  jedis.hvals
         System.out.println("map的value" + jedis.hvals("moment_cnt"));
         // (String key, String... fields)返回值是一个list
         //获取指定域的值 
         List<String> list = jedis.hmget("moment_cnt", "age", "username");
         System.out.println("redis中key的各个 fields值："
                 + jedis.hmget("moment_cnt", "age", "username") + list.size());
         //map中指定的key jedis.hexists 是否存在  存在-true  ,不存在-false
         System.err.println("是否存在worlk:"+jedis.hexists("moment_cnt", "work"));;
         
         //返回全部值
         Map<String, String> map1 = jedis.hgetAll("moment_cnt");
         

         //将哈希表 key 中的域 field 的值设为 value 。
         ///* 如果 key 不存在，一个新的哈希表被创建并进行 HSET 操作。
         //* 如果域 field 已经存在于哈希表中，旧值将被覆盖。
         jedis.hset("moment_cnt", "address", "上海");
         
//         HMSET key field value [field value ...]
//	     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
//	     * 此命令会覆盖哈希表中已存在的域。
//	     * 如果 key 不存在，一个空哈希表被创建并执行 HMSET 操作。
         Map<String, String> subMap = new HashMap<String,String>();
         subMap.put("port", "333333");
         jedis.hmset("moment_cnt", subMap);

//         HSETNX key field value
//         * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。
//         * 若域 field 已经存在，该操作无效。
//         * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
         jedis.hsetnx("moment_cnt", "address", "上海");
         // 删除map中的某一个键 的值 password
         // 当然 (key, fields) 也可以是多个fields
         //jedis.hdel("user", "age");
         
         System.err.println(jedis.hget("moment_cnt", "yao"));
         System.err.println(jedis.hmget("moment_cnt","yao"));
         
         return "set ok";

    }
   
    
    
   


}
