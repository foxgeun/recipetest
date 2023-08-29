package com.recipe.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.recipe.constant.CategoryEnum;
import com.recipe.constant.WritingStatus;
import com.recipe.dto.RecipeNewDto;
import com.recipe.service.RecipeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {

	
	private final RecipeService recipeService;
	//레시피 등록화면
	@GetMapping(value = "/recipe/new")
	public String recipe( Model model ) {
		
		RecipeNewDto recipeNewDto = new RecipeNewDto();
		
		model.addAttribute("recipeNewDto" , recipeNewDto);
		
		return "recipe/new";
	}
	
	//레시피 등록기능
	@PostMapping(value = "/recipe/new")
	public String recipeNew(@RequestParam("recipeImgFile") MultipartFile recipeImgFile ,  @Valid RecipeNewDto recipeNewDto ,  
			BindingResult bindingResult , Model model, 
			@RequestParam("RecipeingreName") List<String> RecipeingreMaterialList ,
			@RequestParam("RecipeingreMaterial") List<String> RecipeingreNameList ,
			@RequestParam("recipeOrderContent") List<String> recipeOrderContentList,
			@RequestParam("recipeOrderImgFile") List<MultipartFile> recipeOrderImgFile,
			@RequestParam("categoryType") String categoryTypeString,
			@RequestParam("writingStatus")String writingStatus,
			Principal principal) {
		
		CategoryEnum categoryType = CategoryEnum.fromString(categoryTypeString);
		recipeNewDto.setCategoryType(categoryType);
		
		WritingStatus status = WritingStatus.valueOf(writingStatus);

		if (status == WritingStatus.PUBLISHED) {
			System.out.println("dasfsadf===" + status);
			recipeNewDto.setWritingStatus(status);
		} else if(status == WritingStatus.DRAFT) {
			recipeNewDto.setWritingStatus(status);
		}
		
		
		System.out.println("카테고리:::" + categoryType);
		
		System.out.println("임시저장여부===" + recipeNewDto.getWritingStatus());
		
		
		//String email = principal.getName();
		
		System.out.println("타이틀==" + recipeNewDto.getTitle());
		System.out.println("소개==" + recipeNewDto.getIntro());
		System.out.println("부제목==" + recipeNewDto.getSubTitle());
		System.out.println("난이도==" + recipeNewDto.getLevel());
		System.out.println("카테고리==" + recipeNewDto.getCategoryType());
		
		System.out.println(recipeOrderImgFile.size());
		System.out.println(recipeOrderContentList.size());
		
		try { 
			recipeService.saveRecipe(recipeNewDto, recipeImgFile , RecipeingreMaterialList, 
					RecipeingreNameList , recipeOrderContentList , recipeOrderImgFile ,principal );
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}
	
	
	
}
