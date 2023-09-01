package com.recipe.controller;

import java.util.List;
import org.openqa.selenium.NoSuchElementException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recipe.entity.Item;
import com.recipe.entity.ItemDetailImg;
import com.recipe.entity.ItemImg;
import com.recipe.entity.Recipe;
import com.recipe.entity.RecipeIngre;
import com.recipe.entity.RecipeOrder;
import com.recipe.repository.ItemDetailImgRepository;
import com.recipe.repository.ItemImgRepository;
import com.recipe.repository.ItemRepository;
import com.recipe.repository.RecipeIngreRepository;
import com.recipe.repository.RecipeOrderRepository;
import com.recipe.repository.RecipeRepository;


import org.springframework.validation.BindingResult;
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
	

	@Autowired
	private final RecipeRepository recipeRepository;
	private final RecipeOrderRepository recipeOrderRepository;
	private final RecipeIngreRepository recipeIngreRepository;

	private final RecipeService recipeService;
	
	
	@GetMapping(value = "/recipe/{Id}")
	public String recipe(Model model, @PathVariable("Id") Long Id) {
		
		Recipe recipeDetail = recipeRepository.getRecipeDetailByid(Id);
		
		recipeRepository.setaddview(Id);
		List<RecipeOrder> recipeOrderList = recipeOrderRepository.getRecipeOrderByid(Id);
		List<RecipeIngre> recipeIngreList = recipeIngreRepository.getRecipeIngreByid(Id);
		
		
		model.addAttribute("recipeIngreList" ,recipeIngreList);
		model.addAttribute("recipeOrder", recipeOrderList);
		model.addAttribute("recipeDetail",recipeDetail);
		
		return "recipes/recipe";
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
			) {
			
		
//		String email = principal.getName();
		
		System.out.println("레시피 정보 1" + recipeNewDto.getTitle());
		System.out.println("레시피 정보 1" + recipeNewDto.getIntro());
		System.out.println("레시피 정보 1" + recipeNewDto.getSubTitle());
		System.out.println("레시피 정보 1" + recipeNewDto.getLevel());
		System.out.println("레시피 정보 1" + recipeNewDto.getCategory());
		
		System.out.println(recipeOrderImgFile.size());
		System.out.println(recipeOrderContentList.size());
		
		try { 
			recipeService.saveRecipe(recipeNewDto, recipeImgFile , RecipeingreMaterialList, 
					RecipeingreNameList , recipeOrderContentList , recipeOrderImgFile   );
		} catch (Exception e) {				//, email
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}
	
	
}
