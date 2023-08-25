package com.recipe.dto;



import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.querydsl.core.annotations.QueryProjection;
import com.recipe.entity.Member;


import org.hibernate.validator.constraints.Length;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

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
	
	private String passwordConfirm;


	private String phoneNumber;

	@QueryProjection
	public MemberDto(Long id, String nickname, String email, String password, String phoneNumber) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
	}
	
	public MemberDto() {
	}



	public static MemberDto memberDto() {
		MemberDto memberDto = new MemberDto();
		
		if(memberDto.getName() != null) {
			memberDto.setNickname(memberDto.getName());
		} 
		
		if(memberDto.getNickname() == null) {
			memberDto.setNickname(memberDto.getEmail());
		}
		return memberDto;
		
	}


	// 상품 이미지 정보를 저장
	private List<RecipeDto> recipeDtoList = new ArrayList<>();

	private static ModelMapper modelMapper = new ModelMapper();

	// dto -> entity로 바꿈
	public Member createMember() {
		return modelMapper.map(this, Member.class);
	}

	// entity -> dto로 바꿈
	public static MemberDto of(Member member) {
		return modelMapper.map(member, MemberDto.class);
	}


	
	private String provider;
	
	private String providerId;
	

}
