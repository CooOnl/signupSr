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

import com.proj.demo.mapper.UserMapper;
import com.proj.demo.model.User;
import com.proj.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController	
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000",allowCredentials = "true")
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
	
	
	@Autowired
	private UserMapper usermapper;
	
//	@GetMapping("/login")
//	public String login() {
//		return "login";
//	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user, HttpSession session){
		String username = user.getUsername();
		String password = user.getPassword();
		
		User exitingUser = usermapper.findByUsername(username);
//		User exitingpass = usermapper.findByUsername(password);
		if (exitingUser == null) { // 아이디 없음
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("회원정보를 확인해주세요.");			
		}
		if(!password.equals(exitingUser.getPassword())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호 오류");
		}
		session.setAttribute("user", exitingUser);
		session.setMaxInactiveInterval(5*60);
		return ResponseEntity.ok("로그인 성공!");
	
	}
	// 세션에 저장된 사용자 정보를 확인하는 API (예시)
//	@PostMapping("/check-session")
//	public ResponseEntity<User> checkSession(HttpSession session) {
//	    User user = (User) session.getAttribute("user");
//	    if (user == null) {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);  // 세션에 정보가 없으면 로그인 안 된 상태
//	    }
//	    return ResponseEntity.ok(user);  // 세션에 저장된 사용자 정보 반환
//	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session){
		session.invalidate();
		return ResponseEntity.ok("로그아웃 성공");
	}
}