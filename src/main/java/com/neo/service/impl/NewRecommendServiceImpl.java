package com.neo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.neo.entity.HomeFeedVo;
import com.neo.entity.Moment;
import com.neo.service.NewRecommendService;

@Service(value = "newRecommendService")
public class NewRecommendServiceImpl implements NewRecommendService {


	@Override
	public JSONObject getList() {
		JSONObject result = new JSONObject();
		
		List<HomeFeedVo> list = new ArrayList<HomeFeedVo>();
		HomeFeedVo homeFeedVo = new HomeFeedVo();
		homeFeedVo.setType(5);
		homeFeedVo.setRelatedActivityId(1223);
		homeFeedVo.setRelatedActivityName("上海大学校庆");
		Moment moment =new Moment();
		moment.setMomentId(11411L);
		moment.setAccessCnt(12232);
		homeFeedVo.setMoment(moment);
		list.add(homeFeedVo);
		
		
		HomeFeedVo homeFeedVo1 = new HomeFeedVo();
		homeFeedVo1.setType(4);
		homeFeedVo1.setRelatedActivityId(212334);
		homeFeedVo1.setRelatedActivityName("易居创研中心");
		Moment moment1 =new Moment();
		moment1.setMomentId(12252L);
		moment1.setAccessCnt(88);
		homeFeedVo1.setMoment(moment1);
		list.add(homeFeedVo1);
		
		result.put("data",list);
        result.put("pageCnt",12);
		return result;
	}

}
