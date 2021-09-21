package com.cos.blogapp2.domain.board;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
@Entity
public class Board {
	@Id
	private int id;
	private String title;
	@Lob
	private String content;
}
