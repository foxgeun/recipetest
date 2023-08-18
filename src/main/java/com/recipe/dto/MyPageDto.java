package com.recipe.dto;

import org.modelmapper.ModelMapper;

import com.recipe.entity.BookMark;
import com.recipe.entity.Comment;
import com.recipe.entity.Follower;
import com.recipe.entity.Following;
import com.recipe.entity.Member;
import com.recipe.entity.Post;
import com.recipe.entity.Recipe;
import com.recipe.entity.Review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageDto {
	
	public MyPageDto() {
		
	}

	private Long id;
	private String phoneNumber;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public static MyPageDto of(Member member) {
		
		return modelMapper.map(member, MyPageDto.class);
	}
}
