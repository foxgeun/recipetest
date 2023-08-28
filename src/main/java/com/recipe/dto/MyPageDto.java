package com.recipe.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import com.recipe.entity.Member;
import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeOrder;
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
	private List<MyPageDto> recipeList = new ArrayList<>();
	
	public void addRecipe(MyPageDto recipeList) {
		this.recipeList.add(recipeList);
	}
	
}
