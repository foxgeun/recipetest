package com.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recipe.dto.ItemCategoryDto;
import com.recipe.dto.ItemDetailDto;
import com.recipe.dto.ItemDetailImgDto;
import com.recipe.dto.ItemImgDto;
import com.recipe.dto.ItemReviewDto;
import com.recipe.dto.ItemSearchDto;
import com.recipe.dto.ItemReviewAnswerDto;
import com.recipe.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ItemController {
	
	private final ItemService itemService;
	
//	상품페이지
	@GetMapping(value= {"item/total" , "item/total/{page}"})
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
	
//	상품 상세페이지
	@GetMapping(value= {"/item/{ItemId}" })
	public  String ItemDetail( @PathVariable("ItemId") Long itemId ,
			Optional<Integer> reviewPage, Model model) {
		
		ItemDetailDto item = itemService.getItemDetailList(itemId);
		
		List<ItemImgDto> imgList =  item.getItemImgDtoList();
		
		List<ItemDetailImgDto> imgDetailList = item.getItemDetailImgDtoList();
		
		Pageable pageable = PageRequest.of(reviewPage.isPresent() ? reviewPage.get() : 0 , 8);
		Page<ItemReviewDto> itemReviewList = itemService.getItemReviewList(pageable, itemId);
		
		model.addAttribute("item" , item);
		model.addAttribute("imgList" , imgList);
		model.addAttribute("imgDetailList" , imgDetailList);
		model.addAttribute("itemReviewList" , itemReviewList);
		model.addAttribute("maxPage" , 5);
		model.addAttribute("itemId" , itemId);
		model.addAttribute("reviewPage",reviewPage);
		
		return "itemDetail";
	}
	
	@PostMapping(value="/answerReg")
	public @ResponseBody String reviewAnswerReg(@RequestBody ItemReviewAnswerDto itemReviewAnswerDto  ) {
		
		 itemService.itemReviewAnswerReg(itemReviewAnswerDto);
		
		return "itemDetail :: #review_list";
		
	} 
	
	@PostMapping(value="/answerUpdate")
	public @ResponseBody String reviewAnswerUpdate(@RequestBody ItemReviewAnswerDto itemReviewAnswerDto  ) {
		
		itemService.itemReviewAnswerUpdate(itemReviewAnswerDto);
		
		return "itemDetail :: #review_list";
		
	} 
	
	@PostMapping(value="/answerDelete")
	public @ResponseBody String reviewAnswerDelete(@RequestBody Long id ) {
		
		itemService.itemReviewAnswerDelete(id);
		
		return "itemDetail :: #review_list";
		
	} 
}
