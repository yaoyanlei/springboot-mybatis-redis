package com.neo.service;

import java.util.List;

import com.neo.common.PageDataByCursor;
import com.neo.entity.HomeFeedVo;
import com.neo.entity.Moment;

public interface MomentService {
	
	
	PageDataByCursor<HomeFeedVo> getHomeFeed();
	List<Moment> getList();
	String getStr();
	Moment getMoment();
}
