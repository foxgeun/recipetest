package com.recipe.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

	private Long id;
	
	private String name;
	
	private String nickname;
	
	private String email;
	
	private String password;
	
	private String address;
	
	private String phoneNumber;
	
	@QueryProjection 
	public MemberDto(Long id, String name, String email, String password, String address, String phoneNumber) {
		
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
}
