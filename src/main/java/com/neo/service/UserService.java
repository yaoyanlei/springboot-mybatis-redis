package com.neo.service;

import java.util.List;

import com.neo.entity.UserDomain;

public interface UserService {
	
	List<UserDomain> findAllUser(int pageNum, int pageSize);
	
	UserDomain getUserDomain(String userName);

}
