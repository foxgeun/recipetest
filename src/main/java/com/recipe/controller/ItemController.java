package com.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recipe.dto.ItemSearchDto;
import com.recipe.service.ItemService;
import com.recipe.entity.Item;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
	
//	상품페이지
	@GetMapping(value= {"item/total" , "item/total/{page}"})
	public String Item(ItemSearchDto itemSearchDto , @PathVariable("page") Optional<Integer> page ,
			Model model) {
		
		List<Item> itemList = itemService.getAllItemList();
		System.out.println(itemList);
		model.addAttribute("itemList",itemList);
		return "item/item";
	}
	
//	상품 상세페이지
	@GetMapping(value= {"/item/{ItemId}"})
	public String ItemDetail( @PathVariable("ItemId") Long itemId , Model model) {

		return "item/itemDetail";
		
	}
	
	
}