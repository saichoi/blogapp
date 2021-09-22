package com.cos.blogapp2.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blogapp2.domain.user.User;
import com.cos.blogapp2.domain.user.UserRepository;
import com.cos.blogapp2.util.SHA;
import com.cos.blogapp2.util.Script;
import com.cos.blogapp2.web.dto.JoinReqDto;
import com.cos.blogapp2.web.dto.LoginReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI
@Controller
public class UserController {

	// 의존성주입(DI)
	private final UserRepository userRepository;
	private final HttpSession session;

	// 로그아웃
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "redirect:/";
	}

	// 로그인
	@PostMapping("/login")
	public @ResponseBody String login(@Valid LoginReqDto dto, BindingResult bindingResult, Model model) {

		// 유효성 검사 실패
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			model.addAttribute("errorMap", errorMap);
			return Script.back(errorMap.toString());
		}

		// 유효성 검사 성공 
		String encPassword = SHA.encrypt(dto.getPassword());
		User principal = userRepository.mLogin(dto.getUsername(), encPassword);

		if (principal == null) {
			return  Script.back("아이디 혹은 비밀번호를 잘못 입력하였습니다.");
		} else {
			session.setAttribute("principal", principal);
			return Script.href("/","로그인 성공");
		}
	}

	// 회원가입
	@PostMapping("/join")
	public @ResponseBody String join(@Valid JoinReqDto dto, BindingResult bindingResult, Model model) {

		// 유효성 검사 실패
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			model.addAttribute("errorMap", errorMap);
			return Script.back(errorMap.toString());
		}

		// 유효성 검사 성공
		// 비밀번호 해시로 변경
		String encPassword = SHA.encrypt(dto.getPassword());
		dto.setPassword(encPassword);
		User user = dto.toEntity();
		userRepository.save(user);
		return Script.href("/loginForm");

	}

	// 회원가입 페이지
	@GetMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}

	// 로그인 페이지
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

	// 회원정보 페이지
	@GetMapping("/user/{id}")
	public String userInfo(@PathVariable int id) {
		return "user/updateForm";
	}

}
