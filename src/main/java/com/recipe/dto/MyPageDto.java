package com.recipe.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.web.multipart.MultipartFile;

import com.recipe.entity.BookMark;
import com.recipe.entity.Comment;
import com.recipe.entity.Follower;
import com.recipe.entity.Following;
import com.recipe.entity.Member;
import com.recipe.entity.Post;
import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeOrder;
import com.recipe.entity.Review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyPageDto {
	
	public MyPageDto() {
		
	}
	//회원정보수정
	private Long id;
	private String phoneNumber;
	private String nickname;
	private String name;
	private String postCode;
	private String address;
	private String detailAddress;
	private String password;
	private String imgUrl;
	private String imgName;
	private String oriImgName;
	private String introduce;

	private static ModelMapper modelMapper = new ModelMapper();
	
	public static MyPageDto of(Member member) {
		
		return modelMapper.map(member, MyPageDto.class);
	}
	
	
	//레시피목록
	private Recipe recipeId;
	private RecipeOrder recipeOrderId;

	
	//찜목록
	private Member member;
	private BookMark bookmark;

	//찜목록
	public MyPageDto(Member member, Recipe recipe, BookMark bookmark) {
		this.member = member;
		this.recipeId = recipe;
		this.bookmark = bookmark;

		
	}

	private Comment comment;
	//내댓글
	public MyPageDto(Member member, Comment comment) {
		this.member = member;
		this.comment = comment;
	}

}
