package com.neo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neo.common.Result;
import com.neo.entity.Moment;
import com.neo.service.MomentService;

@RestController
@RequestMapping("/pet")
public class MomentController {
	
	protected static Logger logger = LoggerFactory.getLogger(MomentController.class);

	
	@Autowired
    private MomentService momentService;

    @RequestMapping(value = "/v2/community/feed/attention/list", method = RequestMethod.GET)
    public Object getAll(){
    	return new Result<>(momentService.getHomeFeed());
    }
    
    @RequestMapping(value = "/v1/community/feed/attention/list", method = RequestMethod.GET)
    public Object getAllv1(){
    	return new Result<>(momentService.getHomeFeed());
    }
    
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String getTest(){
    	return "test success";
    }

    @RequestMapping(value = "/v1/list", method = RequestMethod.GET)
    public Object getList() {
    	return momentService.getList();
    }
    
    
    @RequestMapping(value = "/getstr", method = RequestMethod.GET)
    public String getTest1(){
    	return momentService.getStr();
    }
    
    @RequestMapping(value = "/moment", method = RequestMethod.GET)
    public Moment getTest12(){
    	return momentService.getMoment();
    }
    
    
    @RequestMapping(value = "/add_moment", method = RequestMethod.POST)
    public Moment add(Moment moment){
    	System.err.println("----------moment--post------------");
    	System.err.println(moment);
    	return moment;
    }
   
   
    
    
   


}
