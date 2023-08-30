package com.recipe.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.ItemCategoryDto;
import com.recipe.dto.ItemDetailDto;
import com.recipe.dto.ItemReviewDto;
import com.recipe.dto.ItemSearchDto;
import com.recipe.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
	
	private final ItemRepository itemRepository;
	
	@Transactional(readOnly = true)
	public ItemDetailDto getItemDetailList(Long itemId){
		ItemDetailDto getItemDetailList = itemRepository.getItemDetailList(itemId);
		return getItemDetailList;
	}
	
	
	@Transactional(readOnly = true)
	public Page<ItemCategoryDto> getItemCategoryList(Pageable pageable , ItemSearchDto itemSearchDto){
		Page<ItemCategoryDto> getItemCategoryList = itemRepository.getItemCategoryList(pageable , itemSearchDto);
		return getItemCategoryList;
	}
	
	
	public Page<ItemReviewDto> getItemReviewList(Pageable pageable , Long itemId){
		Page<ItemReviewDto> getItemReviewList = itemRepository.getItemReviewList(pageable, itemId);
		return getItemReviewList;
	}
	
	
	

}
