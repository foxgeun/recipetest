package com.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.recipe.dto.ItemSearchDto;
import com.recipe.service.ItemDetailImgService;
import com.recipe.service.ItemImgService;
import com.recipe.service.ItemService;
import com.recipe.entity.Item;
import com.recipe.entity.ItemDetailImg;
import com.recipe.entity.ItemImg;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	private final ItemImgService itemImgService;
	private final ItemDetailImgService itemDetailImgService;
	
	
//	상품페이지
	@GetMapping(value= {"item/total/{category}"})
	public String Item(ItemSearchDto itemSearchDto , @PathVariable("category") String category ,
			Model model) {
		

		
		List<Item> itemByCtegoryList = itemService.getItemByCategoryEnum(category);


		
		model.addAttribute("itemList",itemByCtegoryList);

		return "item/item";
	}
	
	@GetMapping(value= {"item/total"})
	public String Item(ItemSearchDto itemSearchDto,
			Model model) {
		
		List<Item> itemList = itemService.getAllItemList();
		



		
		model.addAttribute("itemList",itemList);

		return "item/item";
	}
	
	
//	상품 상세페이지
	@GetMapping(value= {"/item/{ItemId}"})
	public String ItemDetail( @PathVariable("ItemId") int itemId , Model model) {

		List<ItemImg> itemImgvalue = itemImgService.getItemImgByItemId(itemId);
		Item itemvalue = itemService.getItemByItemId(itemId);
		List<ItemDetailImg> itemDetailImgList = itemDetailImgService.getItemDetailImgByItemId(itemId);
		
		
		model.addAttribute("imgDetailList",itemDetailImgList);
		model.addAttribute("itemImgList" , itemImgvalue);
		model.addAttribute("item" , itemvalue);
		
		return "item/itemDetail";
		
	}
	
	
}