package com.neo.test;

import java.util.ArrayList;
import java.util.List;

import com.neo.entity.Moment;

public class ListAssignmentTest {
	//赋值测试
	public static void main(String[] args) {
		List<Moment> list = null;;//new ArrayList<Moment>();
//		Moment momen = new Moment();
//		momen.setMomentId(2222L);
//		momen.setAccessCnt(67);
//		list.add(momen);
		
		list = getList();
		list.stream().forEach(moment->{System.err.println(moment);});
	}
	
	public static List<Moment> getList(){
		List<Moment>  list = new ArrayList<Moment>();
		Moment moment = new Moment();
		moment.setAccessCnt(111);
		moment.setMomentId(1111L);
		Moment moment1 = new Moment();
		moment1.setAccessCnt(333);
		moment1.setMomentId(3333L);
		//list.add(moment1);
		//list.add(moment);
		return list;
	}

}
