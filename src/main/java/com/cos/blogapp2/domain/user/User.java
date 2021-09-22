package com.cos.blogapp2.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 20, unique = true)
	private String username;
	
	@Column(nullable = false, length = 70)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
}
