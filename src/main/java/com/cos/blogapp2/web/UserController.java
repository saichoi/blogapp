package com.cos.blogapp2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.blogapp2.domain.user.User;
import com.cos.blogapp2.domain.user.UserRepository;
import com.cos.blogapp2.util.SHA;
import com.cos.blogapp2.web.dto.JoinReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor //DI
@Controller
public class UserController {
	
	//의존성주입(DI)
	private final UserRepository userRepository;
	
	@PostMapping("/join")
	public String join(JoinReqDto dto) {
		
		//비밀번호 해시로 변경 
		String encPassword = SHA.encrypt(dto.getPassword());
		dto.setPassword(encPassword);
		User user = dto.toEntity();
		userRepository.save(user);
		return "redirect:/loginForm";
		
	}

	//회원가입 페이지 
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	//로그인 페이지
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	//회원정보 페이지
	@GetMapping("/user/{id}")
	public String userInfo(@PathVariable int id) {
		return "user/updateForm";
	}
	
}
