package com.neo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.neo.entity.UserDomain;

@Mapper
public interface UserMapper {
	
	List<UserDomain> selectUsers();
	
	
	UserDomain selectUser(String userNmae);
	
}
