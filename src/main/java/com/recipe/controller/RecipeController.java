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
			Principal principal) {
			
		System.out.println("aaaaaa==" + recipeNewDto.getCategory());
		
		System.out.println("bbbbbbbbb====" + recipeNewDto.getDraft());
		
		System.out.println("cccccccc===" + recipeNewDto.getPublished());
		
		//String email = principal.getName();
		
		System.out.println("레시피 정보 1" + recipeNewDto.getTitle());
		System.out.println("레시피 정보 1" + recipeNewDto.getIntro());
		System.out.println("레시피 정보 1" + recipeNewDto.getSubTitle());
		System.out.println("레시피 정보 1" + recipeNewDto.getLevel());
		System.out.println("레시피 정보 1" + recipeNewDto.getCategory());
		
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
