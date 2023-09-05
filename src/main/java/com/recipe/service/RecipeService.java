package com.recipe.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.Tuple;
import com.recipe.dto.RecipeCategoryDto;
import com.recipe.dto.RecipeMainDto;
import com.recipe.dto.RecipeNewDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.entity.Member;
import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeIngre;
import com.recipe.entity.RecipeOrder;
import com.recipe.repository.MemberRepository;
import com.recipe.repository.RecipeIngreRepository;
import com.recipe.repository.RecipeOrderRepository;
import com.recipe.repository.RecipeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RecipeService {
	
	private final RecipeRepository recipeRepository;
	
	private final RecipeIngreRepository recipeIngreRepository;
	
	private final RecipeOrderRepository recipeOrderRepository;
	
//	private final MemberRepository memberRepository;
	
	private final FileService fileService;
	
	private String itemImgLocation = "C:/yummy/recipe";
	
	public Long saveRecipe(RecipeNewDto recipeNewDto , MultipartFile recipeImgFile , 
			List<String> RecipeingreMaterialList , List<String> RecipeingreNameList,
			List<String> recipeOrderContentList , List<MultipartFile> recipeOrderImgFile ) 
					throws Exception{ // , String email
		
//		String email = null;
//		
//		Member member = memberRepository.findbyEmail(email);
		
			Recipe recipe = recipeNewDto.createRecipe();
			
			String imgName = recipeImgFile.getOriginalFilename();
			String imgUrl = "";
			
			if(!StringUtils.isEmpty(imgName)) {
				imgName = fileService.uploadFile(itemImgLocation, 
						imgName, recipeImgFile.getBytes());
				imgUrl = "/img/recipe/" + imgName;
				
				recipe.updateRecipeImg(imgUrl, imgName);
//				recipe.setMember(member);
				recipeRepository.save(recipe);
			}
			
		for(int i=0; i<RecipeingreMaterialList.size(); i++) {
			
			RecipeIngre recipeIngre = new RecipeIngre();
			
			recipeIngre.setRecipe(recipe);
			recipeIngre.setIngreMaterial(RecipeingreMaterialList.get(i));
			recipeIngre.setIngreName(RecipeingreNameList.get(i));
			recipeIngreRepository.save(recipeIngre);
		}
		
		for(int i=0; i<recipeOrderContentList.size(); i++) {
			RecipeOrder recipeOrder = new RecipeOrder();
			
			recipeOrder.setRecipe(recipe);
			recipeOrder.setContent(recipeOrderContentList.get(i));
			recipeOrder.setOrder_number(i+1);
			
			String imgName2 = recipeOrderImgFile.get(i).getOriginalFilename();
			String imgUrl2 = "";
			
			if(!StringUtils.isEmpty(imgName2)) {
				imgName2 = fileService.uploadFile(itemImgLocation, 
						imgName2, recipeOrderImgFile.get(i).getBytes());
				imgUrl2 = "/img/recipe/" + imgName2;
				
				recipeOrder.updateRecipeOrderImg(imgUrl2, imgName2);
				recipeOrderRepository.save(recipeOrder);
			}
			
		}
			
			return recipe.getId();
	}
	
	
	@Transactional(readOnly = true)
	public List<RecipeMainDto> getRecipeNewList() {
		List<RecipeMainDto> getRecipeNewList = recipeRepository.getRecipeNewList();
		return getRecipeNewList;
	}
	
	@Transactional(readOnly = true)
	public List<RecipeMainDto> getRecipeHeaderBestList() {
		List<RecipeMainDto> getRecipeHeaderBestList = recipeRepository.getRecipeHeaderBestList();
		return getRecipeHeaderBestList; 
	}
	
	@Transactional(readOnly = true)
	public List<RecipeMainDto> getRecipeBestList() {
		List<RecipeMainDto> getRecipeBestList = recipeRepository.getRecipeBestList();
		return getRecipeBestList; 
	}
	
	
	@Transactional(readOnly = true)
	public Page<RecipeCategoryDto> getRecipeCategoryReviewBestList(Pageable pageable , RecipeSearchDto recipeSearchDto) {
		Page<RecipeCategoryDto> getRecipeCategoryReviewBestList = recipeRepository.getRecipeCategoryReviewBestList(pageable, recipeSearchDto);
	    return getRecipeCategoryReviewBestList;
	}
	
}
