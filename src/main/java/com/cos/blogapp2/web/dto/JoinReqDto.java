package com.cos.blogapp2.web.dto;

import com.cos.blogapp2.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JoinReqDto {

	public String username;
	public String password;
	public String email;
	
	public User toEntity() {
		User user = User.builder()
				.username(username)
				.password(password)
				.email(email)
				.build();
		return user;
	}
	
}
