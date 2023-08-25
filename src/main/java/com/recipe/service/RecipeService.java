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
import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeIngre;
import com.recipe.entity.RecipeOrder;
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
	
	private final FileService fileService;
	
	private String itemImgLocation = "C:/yummy/recipe";
	
	public Long saveRecipe(RecipeNewDto recipeNewDto , 
			List<String> RecipeingreMaterialList , List<String> RecipeingreNameList,
			List<String> recipeOrderContentList ,  MultipartFile file) throws Exception{
		
			Recipe recipe = recipeNewDto.createRecipe();
			
			String imgName = file.getOriginalFilename();
			String imgUrl = "";
			
			//1.파일을 itemImgLocation에 저장
			if(!StringUtils.isEmpty(imgName)) {
				//oriImgName이 빈문자열이 아니라면 이미지 파일 업로드
				imgName = fileService.uploadFile(itemImgLocation, 
						imgName, file.getBytes());
				imgUrl = "/img/recipe/" + imgName;
				
				recipe.updateRecipeImg(imgUrl, imgName);
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
			recipeOrderRepository.save(recipeOrder);
		}
		
			
			return recipe.getId();
	}
	
	
	@Transactional(readOnly = true)
	public List<RecipeMainDto> getRecipeNewList() {
		List<RecipeMainDto> getRecipeNewList = recipeRepository.getRecipeNewList();
		return getRecipeNewList;
	}
	
	@Transactional(readOnly = true)
	public List<RecipeMainDto> getRecipeBestList() {
		List<RecipeMainDto> getRecipeBestList = recipeRepository.getRecipeBestList();
		return getRecipeBestList; 
	}
	
	@Transactional(readOnly = true)
	public List<RecipeMainDto> getRecipeTotalList() {
		List<RecipeMainDto> getRecipeTotalList = recipeRepository.getRecipeTotalList();
		return getRecipeTotalList; 
	}
	
	@Transactional(readOnly = true)
	public Page<RecipeCategoryDto> getRecipeCategoryReviewBestList(Pageable pageable , RecipeSearchDto recipeSearchDto) {
		Page<RecipeCategoryDto> getRecipeCategoryReviewBestList = recipeRepository.getRecipeCategoryReviewBestList(pageable, recipeSearchDto);
	    return getRecipeCategoryReviewBestList;
	}
	
}
