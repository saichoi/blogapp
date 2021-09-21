package com.cos.blogapp2.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	//메인페이지 = 게시글 목록페이지
	@GetMapping("/board")
	public String list() {
		return "board/list";
	}
	
	//게시글 상세보기
	@GetMapping("/board/{id}")
	public String detail() {
		return "board/detail";
	}
	
	//게시글 쓰기
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveFrom";
	}
	
}
