package com.recipe.dto;


import org.modelmapper.ModelMapper;

import com.querydsl.core.annotations.QueryProjection;
import com.recipe.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

	private String id;
	
	private String nickname;
	
	private String email;
	
	private String password;
	
	private String phoneNumber;
	
	@QueryProjection
	public MemberDto(String id, String nickname, String email, String password, String phoneNumber) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	//dto -> entity로 바꿈
		public Member createMember() {
			return modelMapper.map(this, Member.class);
		}
		
		//entity -> dto로 바꿈
		public static MemberDto of(Member member) {
			return modelMapper.map(member, MemberDto.class);
		}
}
