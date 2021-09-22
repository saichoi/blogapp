package com.cos.blogapp2.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.cos.blogapp2.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JoinReqDto {
	
	@Size(min = 2, max = 20)
	@NotBlank 
	private String username;

	@Size(min = 4, max = 20)
	@NotBlank
	private String password;

	@Size(min = 4, max = 50)
	@NotBlank
	private String email;

	public User toEntity() {
		User user = User.builder().username(username).password(password).email(email).build();
		return user;
	}

}
