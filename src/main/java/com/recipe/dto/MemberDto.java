package com.recipe.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import com.querydsl.core.annotations.QueryProjection;
import com.recipe.entity.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {

	private Long id;

	private String nickname;

	private String email;

	private String password;

	private String phoneNumber;
	
	
	private Long allCommentCount;

	@QueryProjection
	public MemberDto(Long id, String nickname, String email, String password, String phoneNumber, Long allCommentCount) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.allCommentCount = allCommentCount;
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
}
