package com.proj.demo.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.proj.demo.mapper.UserMapper;
import com.proj.demo.model.User;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;		//UserMaper 인터페이스 가져옴

    // 회원가입 처리
    public String signUp(User user) {	//User 데이터 가져오기
        // 아이디 중복 확인
        if (userMapper.findByUsername(user.getUsername()) != null) {
            return "Username is already taken!";
        }

        // 새로운 유저 저장
        userMapper.insertUser(user);
        return "User registered successfully!";
        
        
        
        
    }
}
