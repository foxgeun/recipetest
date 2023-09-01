package com.recipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeIngre;
import com.recipe.entity.RecipeOrder;
import com.recipe.repository.RecipeIngreRepository;
import com.recipe.repository.RecipeOrderRepository;
import com.recipe.repository.RecipeRepository;


import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.recipe.dto.RecipeNewDto;
import java.security.Principal;

import com.recipe.constant.CategoryEnum;
import com.recipe.constant.WritingStatus;
import com.recipe.service.RecipeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class RecipeController {
	

	@Autowired
	private final RecipeRepository recipeRepository;
	private final RecipeOrderRepository recipeOrderRepository;
	private final RecipeIngreRepository recipeIngreRepository;

	private final RecipeService recipeService;
	



	// 레시피 등록기능
	@PostMapping(value = "/recipe/new")
	public String recipeNew(@RequestParam("recipeImgFile") MultipartFile recipeImgFile ,  @Valid RecipeNewDto recipeNewDto ,  
			BindingResult bindingResult , Model model, 
			@RequestParam("recipeingreName") List<String> recipeingreNameList ,
			@RequestParam("recipeingreMaterial") List<String> recipeingreMaterialList,
			@RequestParam("recipeOrderContent") List<String> recipeOrderContentList,
			@RequestParam("recipeOrderImgFile") List<MultipartFile> recipeOrderImgFile,
			@RequestParam("categoryType") String categoryTypeString,
			@RequestParam("writingStatus")String writingStatus, Principal principal) {
		
		CategoryEnum categoryType = CategoryEnum.fromString(categoryTypeString);
		recipeNewDto.setCategoryType(categoryType);
		
		WritingStatus status = WritingStatus.valueOf(writingStatus);

		if (status == WritingStatus.PUBLISHED) {
			recipeNewDto.setWritingStatus(status);
		} else if(status == WritingStatus.DRAFT) {
			recipeNewDto.setWritingStatus(status);
		}
		
		try { 
			recipeService.saveRecipe(recipeNewDto, recipeImgFile , recipeingreMaterialList, 
					recipeingreNameList , recipeOrderContentList , recipeOrderImgFile ,principal );
		} catch (Exception e) {			
			e.printStackTrace();
		}
		
		return "redirect:/";
	}
	
	//레시피 수정화면
	@GetMapping(value = "/recipe/modify/{recipeId}")
	public String recipeDtl(@PathVariable("recipeId") Long recipeId, Model model){	

		
		Recipe recipeDetail = recipeRepository.getRecipeDetailByid(recipeId);
		
		recipeRepository.setaddview(recipeId);
		List<RecipeOrder> recipeOrderList = recipeOrderRepository.getRecipeOrderByid(recipeId);
		List<RecipeIngre> recipeIngreList = recipeIngreRepository.getRecipeIngreByid(recipeId);
		
		
		model.addAttribute("recipeIngreList" ,recipeIngreList);
		model.addAttribute("recipeOrder", recipeOrderList);
		model.addAttribute("recipeDetail",recipeDetail);
		
		try {
			RecipeNewDto recipeNewDto = recipeService.getRecipeDtl(recipeId);
			model.addAttribute("recipeNewDto" , recipeNewDto);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			model.addAttribute("errorMessage", "레시피 정보를 가져오는중 에러가 발생했습니다");
			model.addAttribute("recipeNewDto", new RecipeNewDto());
			
			return "main";
		}
		
		
		
		return "recipe/modify";
	}
	
	//레시피 수정기능
	@PostMapping(value = "/recipe/modify/{recipeId}")
	public String recipeUpdate(@RequestParam("recipeImgFile") MultipartFile recipeImgFile ,  @Valid RecipeNewDto recipeNewDto ,  
			BindingResult bindingResult , Model model, 
			@RequestParam("recipeingreName") List<String> recipeingreNameList ,
			@RequestParam("recipeingreMaterial") List<String> recipeingreMaterialList,
			@RequestParam("recipeOrderContent") List<String> recipeOrderContentList,
			@RequestParam("recipeOrderImgFile") List<MultipartFile> recipeOrderImgFile,
			@RequestParam("categoryType") String categoryTypeString,
			@RequestParam("writingStatus")String writingStatus) {
		
			CategoryEnum categoryType = CategoryEnum.fromString(categoryTypeString);
			recipeNewDto.setCategoryType(categoryType);
			
			WritingStatus status = WritingStatus.valueOf(writingStatus);
	
			if (status == WritingStatus.PUBLISHED) {
				recipeNewDto.setWritingStatus(status);
			} else if(status == WritingStatus.DRAFT) {
				recipeNewDto.setWritingStatus(status);
			}
			
			
			try { 
				recipeService.updateRecipe(recipeNewDto, recipeImgFile , recipeingreMaterialList, 
						recipeingreNameList , recipeOrderContentList , recipeOrderImgFile);
			} catch (Exception e) {			
				e.printStackTrace();
			}
			return "redirect:/";
	}
	@GetMapping(value = "/recipe")
	public String recipe( Model model ) {
		
		RecipeNewDto recipeNewDto = new RecipeNewDto();

		
		model.addAttribute("recipeNewDto" , recipeNewDto);
		
		return "new";
	}

	
	@PostMapping(value = "/recipe/new")
	public String recipeNew(@RequestParam("recipeImgFile") MultipartFile recipeImgFile ,  @Valid RecipeNewDto recipeNewDto ,  
			BindingResult bindingResult , Model model, 
			@RequestParam("RecipeingreName") List<String> RecipeingreMaterialList ,
			@RequestParam("RecipeingreMaterial") List<String> RecipeingreNameList ,
			@RequestParam("recipeOrderContent") List<String> recipeOrderContentList,
			@RequestParam("recipeOrderImgFile") List<MultipartFile> recipeOrderImgFile //,Principal principal
			,Principal principal) {
			
		
//		String email = principal.getName();
		
		System.out.println("레시피 정보 1" + recipeNewDto.getTitle());
		System.out.println("레시피 정보 1" + recipeNewDto.getIntro());
		System.out.println("레시피 정보 1" + recipeNewDto.getSubTitle());
		System.out.println("레시피 정보 1" + recipeNewDto.getLevel());
		System.out.println("레시피 정보 1" + recipeNewDto.getCategoryType());
		
		System.out.println(recipeOrderImgFile.size());
		System.out.println(recipeOrderContentList.size());
		
		try { 
			recipeService.saveRecipe(recipeNewDto, recipeImgFile , RecipeingreMaterialList, 
					RecipeingreNameList , recipeOrderContentList , recipeOrderImgFile , principal );
		} catch (Exception e) {				//, email
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}
	
}
