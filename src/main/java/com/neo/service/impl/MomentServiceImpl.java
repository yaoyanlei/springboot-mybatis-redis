package com.neo.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.neo.aspect.MomentAccess;
import com.neo.common.PageDataByCursor;
import com.neo.constant.MomentAccessCnt;
import com.neo.entity.HomeFeedVo;
import com.neo.entity.Moment;
import com.neo.service.MomentService;


@Service(value = "momentService")
public class MomentServiceImpl implements MomentService {

	@Override
	public PageDataByCursor<HomeFeedVo> getHomeFeed() {
		PageDataByCursor<HomeFeedVo> data = new PageDataByCursor<>(0, 1);
		data.setCursor(2);
		List<HomeFeedVo> list = new ArrayList<HomeFeedVo>();
		HomeFeedVo homeFeedVo = new HomeFeedVo();
		homeFeedVo.setType(1);
		homeFeedVo.setRelatedActivityId(123);
		homeFeedVo.setRelatedActivityName("太仓团建");
		Moment moment =new Moment();
		moment.setMomentId(11111L);
		moment.setAccessCnt(12232);
		homeFeedVo.setMoment(moment);
		list.add(homeFeedVo);
		
		
		HomeFeedVo homeFeedVo1 = new HomeFeedVo();
		homeFeedVo1.setType(2);
		homeFeedVo1.setRelatedActivityId(234);
		homeFeedVo1.setRelatedActivityName("苏州南京");
		Moment moment1 =new Moment();
		moment1.setMomentId(12222L);
		moment1.setAccessCnt(88);
		homeFeedVo1.setMoment(moment1);
		list.add(homeFeedVo1);
		
		data.setData(list);
		return data;
	}
	
	@MomentAccess(accessCnt=MomentAccessCnt.SPECIAL_MOMENT_ACCESS_CNT)
	public List<Moment> getList(){
		List<Moment> list = new ArrayList<Moment>();
		Moment moment =new Moment();
		moment.setMomentId(11111L);
		moment.setAccessCnt(12232);
		Moment moment1 =new Moment();
		moment1.setMomentId(12221L);
		moment1.setAccessCnt(12);
		list.add(moment);
		list.add(moment1);
		return list;
	}

//	@MomentAccess
	@Override
	public String getStr() {
		return "set ok";
	}

	@MomentAccess(accessCnt=MomentAccessCnt.PIC_OR_MOVIE_MOMENT_ACCESS_CNT)
	@Override
	public Moment getMoment() {
		Moment moment1 =new Moment();
		moment1.setMomentId(12221L);
		moment1.setAccessCnt(12);
		return moment1;
	}


}
