package com.cos.blogapp2.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LoginReqDto {
	
	@Size(min = 2, max = 20) 
	@NotBlank
	private String username;
	
	@Size(min = 2, max = 20) 
	@NotBlank
	private String password;

}
