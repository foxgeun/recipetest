package com.recipe.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recipe.dto.ItemCategoryDto;
import com.recipe.dto.ItemSearchDto;
import com.recipe.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
//	상품페이지
	@GetMapping(value= {"/item" , "/item/{page}"})
	public String Item(ItemSearchDto itemSearchDto , @PathVariable("page") Optional<Integer> page ,
			Model model) {
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0 , 12);
		
		Page<ItemCategoryDto> category = itemService.getItemCategoryList(pageable , itemSearchDto);
		
		model.addAttribute("itemSearchDto" , itemSearchDto);
		model.addAttribute("category",category);
		model.addAttribute("maxPage" , 5);
		model.addAttribute("page" , page);
		
		return "item";
	}
	
	
	
}
