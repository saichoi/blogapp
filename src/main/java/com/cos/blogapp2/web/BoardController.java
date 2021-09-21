package com.cos.blogapp2.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cos.blogapp2.domain.board.Board;
import com.cos.blogapp2.domain.board.BoardRepository;
import com.cos.blogapp2.domain.user.User;
import com.cos.blogapp2.web.dto.BoardSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardRepository boardRepository;
	private final HttpSession session;
	
	//글쓰기
	@PostMapping("/board")
	public String save(BoardSaveReqDto dto) {
		Board board = dto.toEntity();
		User principal = (User)session.getAttribute("principal");
		board.setUser(principal);
		boardRepository.save(board);
		return "redirect:/";
	}
	
	//메인페이지 = 게시글 목록페이지
	@GetMapping("/board")
	public String list(Model model) {
		List<Board> boardsEntity = boardRepository.findAll();
		model.addAttribute("boardsEntity",boardsEntity);
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
		return "board/saveForm";
	}
	
}
