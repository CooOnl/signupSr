package com.proj.demo.mapper;


import org.apache.ibatis.annotations.Mapper; 

import com.proj.demo.model.User;

@Mapper
public interface UserMapper {

	// 회원가입 처리 (새로운 유저 등록)
	void insertUser(User user);

	// 아이디 중복 확인
	User findByUsername(String username);
}
