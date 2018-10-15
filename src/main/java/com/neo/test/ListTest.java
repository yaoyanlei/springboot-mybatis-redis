package com.neo.test;

import java.util.ArrayList;
import java.util.List;

import com.neo.entity.Moment;
import com.neo.service.MomentService;

public class ListTest {

	public static void main(String[] args) {
//		List<Long> list = new ArrayList<Long>();
//		list.add(112L);
//		//list.addAll(null);  //NPE
//		List<Long> list1 = new ArrayList<Long>();
//		list1.stream().forEach(id->System.err.println(id));//list1为空 ，NPE
//		String cnt = "10110101110";
//		System.err.println(Integer.parseInt(cnt));;
		
		List<Moment> momentList = new ArrayList<Moment>();
		Moment momen = new Moment();
		momen.setMomentId(2222L);
		momen.setAccessCnt(67);
		momentList.add(momen);
	 	int momentAccessCnt = 5;
		boolean existsFlag = false;
		Integer newAccessCnt = 0;
		String cacheAccessCnt = "";
		for (Moment moment :momentList) {
			existsFlag = false;
			if (existsFlag) {
				cacheAccessCnt = "1234";
				newAccessCnt = Integer.parseInt(cacheAccessCnt)+ momentAccessCnt;
			}else{
				newAccessCnt = moment.getAccessCnt()+momentAccessCnt;
			}
			System.err.println(newAccessCnt.toString());
			System.err.println(Integer.toString(newAccessCnt));
		}
		
	}

}
