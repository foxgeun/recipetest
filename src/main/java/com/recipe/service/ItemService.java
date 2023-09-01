package com.recipe.service;


import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.entity.Item;
import com.recipe.repository.ItemRepository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.dto.ItemCategoryDto;
import com.recipe.dto.ItemDetailDto;
import com.recipe.dto.ItemReviewDto;
import com.recipe.dto.ItemSearchDto;
import com.recipe.dto.ItemReviewAnswerDto;
import com.recipe.entity.Item;
import com.recipe.entity.ItemReview;
import com.recipe.entity.ItemReviewAnswer;
import com.recipe.repository.ItemRepository;
import com.recipe.repository.ItemReviewAnswerRepository;
import com.recipe.repository.ItemReviewRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
	
	private final ItemReviewRepository itemReviewRepository;
	
	private final ItemReviewAnswerRepository itemReviewAnswerRepository; 
	private final ItemRepository itemRepository;
	

	public List<Item> getAllItemList(){
		return itemRepository.getAllItemList();
	}
	
	public Item getItemByItemId(int Id) {
		return itemRepository.getItemByItemId(Id);
	}
	
	public List<Item> getItemByCategoryEnum(String category){
		return itemRepository.getItemByCategoryEnum(category);
	}




	
	@Transactional(readOnly = true)
	public ItemDetailDto getItemDetailList(Long itemId){
		ItemDetailDto getItemDetailList = itemRepository.getItemDetailList(itemId);
		return getItemDetailList;
	}
	
//	모든 상품 가져오기
	@Transactional(readOnly = true)
	public Page<ItemCategoryDto> getItemCategoryList(Pageable pageable , ItemSearchDto itemSearchDto){
		Page<ItemCategoryDto> getItemCategoryList = itemRepository.getItemCategoryList(pageable , itemSearchDto);
		return getItemCategoryList;
	}
	
//	상품의 리뷰 가져오기
	@Transactional(readOnly = true)
	public Page<ItemReviewDto> getItemReviewList(Pageable pageable , Long itemId){
		Page<ItemReviewDto> getItemReviewList = itemRepository.getItemReviewList(pageable, itemId);
		return getItemReviewList;
	}
	
	
//	리뷰 답변 등록
	public void itemReviewAnswerReg(ItemReviewAnswerDto itemReviewAnswerDto) {
		
		Optional<ItemReview> itemReview  = itemReviewRepository.findById(itemReviewAnswerDto.getId());
		
		ItemReviewAnswer itemReviewAnswer = new ItemReviewAnswer();
		
		itemReviewAnswer.setItemReview(itemReview.get());
		itemReviewAnswer.setContent(itemReviewAnswerDto.getContent());
		itemReviewAnswerRepository.save(itemReviewAnswer);
	}
	
//	리뷰 수정
	public void itemReviewAnswerUpdate(ItemReviewAnswerDto itemReviewAnswerDto) {
		
		Optional<ItemReviewAnswer> itemReviewAnswer = itemReviewAnswerRepository.findById(itemReviewAnswerDto.getId());
		
		itemReviewAnswer.get().setId(itemReviewAnswerDto.getId());
		itemReviewAnswer.get().setContent(itemReviewAnswerDto.getContent());
		
		itemReviewAnswerRepository.save(itemReviewAnswer.get());
	}

//	리뷰삭제
	public void itemReviewAnswerDelete(Long id) {
		
		Optional<ItemReviewAnswer> itemReviewAnswer = itemReviewAnswerRepository.findById(id);
		
		itemReviewAnswerRepository.delete(itemReviewAnswer.get());
	}
	
}

