package com.neo.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Moment {


    // 时刻id
//    @AutoID
    private Long momentId;
   
    // 浏览量
    private Integer accessCnt;

	public Long getMomentId() {
		return momentId;
	}

	public void setMomentId(Long momentId) {
		this.momentId = momentId;
	}

	public Integer getAccessCnt() {
		return accessCnt;
	}

	public void setAccessCnt(Integer accessCnt) {
		this.accessCnt = accessCnt;
	}

	@Override
	public String toString() {
		return "Moment [momentId=" + momentId + ", accessCnt=" + accessCnt + "]";
	}
   

}
