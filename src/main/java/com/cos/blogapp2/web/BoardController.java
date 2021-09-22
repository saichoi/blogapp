package com.cos.blogapp2.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.blogapp2.domain.board.Board;
import com.cos.blogapp2.domain.board.BoardRepository;
import com.cos.blogapp2.domain.user.User;
import com.cos.blogapp2.handler.ex.MyNotFountException;
import com.cos.blogapp2.util.Script;
import com.cos.blogapp2.web.dto.BoardSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardRepository boardRepository;
	private final HttpSession session;

	// 글쓰기
	@PostMapping("/board")
	public @ResponseBody String save(@Valid BoardSaveReqDto dto, BindingResult bindingResult) {

		// 글쓰기 유효성 검사
		User principal = (User) session.getAttribute("principal");
		if (principal == null) {
			// 로그인이 되지 않은 상태에서 글쓰기한 경우
			return Script.href("/loginForm", "잘못된 접근입니다.");
		}
		
		//유효성 검사 실패 
		if (bindingResult.hasErrors()) {
			Map<String, String> errorMap = new HashMap<>();
			for (FieldError error : bindingResult.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return Script.back(errorMap.toString());
		}

		//유효성 검사 성공
		boardRepository.save(dto.toEntity(principal));
		return Script.href("/","글쓰기 성공");
	}

	// 메인페이지 = 게시글 목록페이지
	@GetMapping("/board")
	public String list(Model model, int page) {

		// 페이징
		PageRequest pageRequest = PageRequest.of(page, 3, Sort.by(Direction.DESC, "id"));
		Page<Board> boardsEntity = boardRepository.findAll(pageRequest);

		model.addAttribute("boardsEntity", boardsEntity);
		return "board/list";
	}

	// 게시글 상세보기
	@GetMapping("/board/{id}")
	public String detail(@PathVariable int id, Model model) {
		Board boardEntity = boardRepository.findById(id)
				.orElseThrow(() -> new MyNotFountException("게시글" + id + "번을 찾을 수 없습니다."));
		model.addAttribute("boardEntity", boardEntity);
		return "board/detail";
	}

	// 게시글 쓰기 페이지
	@GetMapping("/board/saveForm")
	public String saveForm() {
		return "board/saveForm";
	}

}
