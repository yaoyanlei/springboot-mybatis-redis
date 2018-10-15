package com.neo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.neo.common.Result;
import com.neo.service.NewRecommendService;

@RestController
@RequestMapping("/pet")
public class NewRecommendController {
	
	@Autowired
	private NewRecommendService newRecommendService;
	
	@RequestMapping(value = "/v3/new_recommends/home/feed", method = RequestMethod.GET)
	public Result<Object> list(){
		return new Result<>(newRecommendService.getList());
	}

}
