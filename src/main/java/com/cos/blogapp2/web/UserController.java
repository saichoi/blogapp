package com.cos.blogapp2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {

	//회원가입
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	//로그인
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	//회원정보 보기
	@GetMapping("/user/{id}")
	public String userInfo(@PathVariable int id) {
		return "user/updateForm";
	}
	
}
