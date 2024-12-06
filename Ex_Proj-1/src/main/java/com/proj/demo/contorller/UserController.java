package com.proj.demo.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proj.demo.model.User;
import com.proj.demo.service.UserService;

@RestController	
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	@Autowired
	private UserService userService;

	// 회원가입 API
	@PostMapping("/signup")
	public ResponseEntity<String> signUp(@RequestBody User user) {
		try {
			// 회원가입 로직 처리
			// 예를 들어 데이터베이스에 사용자 저장
			userService.signUp(user);
			return ResponseEntity.ok("회원가입 성공");
		} catch (Exception e) {
			System.out.println("exception  : " + e.getMessage());
			// 예외 발생 시 처리 로직
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
		}
	}

	// 모든 예외를 처리할 수 있는 글로벌 예외 처리기
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
	}
//    public String signUp(@RequestBody User user) {
//        return userService.signUp(user);
//    }
}