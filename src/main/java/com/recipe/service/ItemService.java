package com.recipe.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.entity.Item;
import com.recipe.repository.ItemRepository;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.recipe.constant.AnswerOk;
import com.recipe.constant.ItemInqBoardEnum;
import com.recipe.constant.ItemInqEnum;
import com.recipe.dto.ItemCategoryDto;
import com.recipe.dto.ItemDetailDto;
import com.recipe.dto.ItemInqDto;
import com.recipe.dto.ItemReviewDto;
import com.recipe.dto.ItemReviewImgDto;
import com.recipe.dto.ItemSearchDto;
import com.recipe.dto.ItemReviewAnswerDto;
import com.recipe.entity.Item;
import com.recipe.entity.ItemInq;
import com.recipe.entity.ItemInqAnwser;
import com.recipe.entity.ItemReview;
import com.recipe.entity.ItemReviewAnswer;
import com.recipe.repository.ItemInqAnswerRepository;
import com.recipe.repository.ItemInqRepository;
import com.recipe.repository.ItemRepository;
import com.recipe.repository.ItemReviewAnswerRepository;
import com.recipe.repository.ItemReviewImgRepositroy;
import com.recipe.repository.ItemReviewRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {


	private final ItemRepository itemRepository;


	private final ItemReviewRepository itemReviewRepository;

	private final ItemReviewAnswerRepository itemReviewAnswerRepository;

	private final ItemInqRepository itemInqRepository;
	
	private final ItemInqAnswerRepository itemInqAnswerRepository;
	
	private final ItemReviewImgRepositroy itemReviewImgRepositroy;
	
	public Page<Item> getItemPage(Pageable pageable){
		Page<Item> Items = itemRepository.findAll(pageable);
		for(Item item : Items ) {
			System.out.println(item.getCategory());
		}
		return Items;
	}

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
	public ItemDetailDto getItemDetailList(Long itemId) {
		ItemDetailDto getItemDetailList = itemRepository.getItemDetailList(itemId);
		return getItemDetailList;
	}

//	모든 상품 가져오기
	@Transactional(readOnly = true)
	public Page<ItemCategoryDto> getItemCategoryList(Pageable pageable, ItemSearchDto itemSearchDto) {
		Page<ItemCategoryDto> getItemCategoryList = itemRepository.getItemCategoryList(pageable, itemSearchDto);
		return getItemCategoryList;
	}

//	상품의 리뷰글 가져오기
	@Transactional(readOnly = true)
	public Page<ItemReviewDto> getItemReviewList(Pageable pageable, Long itemId) {
		Page<ItemReviewDto> getItemReviewList = itemRepository.getItemReviewList(pageable, itemId);
		return getItemReviewList;
	}
	
//	상품의 리뷰의 이미지 가져오기
	@Transactional(readOnly = true)
	public List<ItemReviewImgDto> getItemReviewImgList(Long id) {
		List<ItemReviewImgDto> getItemReviewImgList = itemReviewImgRepositroy.getItemReviewImgList(id) ;
		return getItemReviewImgList;
	}
	
//	상품의 문의글 가져오기
	@Transactional(readOnly = true)
	public Page<ItemInqDto> getItemInqList(Pageable pageable , Long itemId){
		Page<ItemInqDto> getItemInqList =  itemRepository.getItemInqList(pageable ,itemId);
		return getItemInqList;
	}

//	리뷰 답변 등록
	public void itemReviewAnswerReg(@RequestBody Map<String, Object> requestBody) {
		Long id = Long.parseLong(requestBody.get("id").toString());
		String content = requestBody.get("content").toString();
		
		ItemReview itemReview = itemReviewRepository.findById(id).orElseThrow();

		ItemReviewAnswer itemReviewAnswer = new ItemReviewAnswer();

		itemReviewAnswer.setItemReview(itemReview);
		itemReviewAnswer.setContent(content);
		itemReviewAnswerRepository.save(itemReviewAnswer);
	}

//	리뷰 답변 수정
	public void itemReviewAnswerUpdate(@RequestBody Map<String, Object> requestBody) {
		Long id = Long.parseLong(requestBody.get("id").toString());
		String content = requestBody.get("content").toString();
		
		ItemReviewAnswer itemReviewAnswer = itemReviewAnswerRepository.findById(id)
				.orElseThrow();

		itemReviewAnswer.setId(id);
		itemReviewAnswer.setContent(content);

		itemReviewAnswerRepository.save(itemReviewAnswer);
	}

//	리뷰 답변 삭제
	public void itemReviewAnswerDelete(Long id) {
		
		ItemReviewAnswer itemReviewAnswer = itemReviewAnswerRepository.findById(id).orElseThrow();

		itemReviewAnswerRepository.delete(itemReviewAnswer);
	}

//	문의글 등록
	public void itemInqReg(Map<String, Object> requestBody) {

		Long id = Long.parseLong(requestBody.get("id").toString());
		String title = requestBody.get("title").toString();
		String content = requestBody.get("content").toString();
		int itemInqBoardEnum = Integer.parseInt(requestBody.get("itemInqBoardEnum").toString());
		int itemInqEnum = Integer.parseInt(requestBody.get("itemInqEnum").toString());
		
		Item item = itemRepository.findById(id).orElseThrow();
		
		ItemInq itemInq = new ItemInq();
		itemInq.setItem(item);
		itemInq.setTitle(title);
		itemInq.setContent(content);
		
		switch (itemInqBoardEnum) {
		case 2:
			itemInq.setItemInqBoardEnum(ItemInqBoardEnum.비밀글);
			break;
		}
		
		switch (itemInqEnum) {
		case 1:
			itemInq.setItemInqEnum(ItemInqEnum.배송문의);
			break;
		case 2:
			itemInq.setItemInqEnum(ItemInqEnum.재입고문의);
			break;
		case 3:
			itemInq.setItemInqEnum(ItemInqEnum.상품상세문의);
			break;
		case 4:
			itemInq.setItemInqEnum(ItemInqEnum.기타문의);
			break;
		}
		
		
		itemInqRepository.save(itemInq);
	}
	
	
//	리뷰 답변 등록
	public void itemInqAnswerReg(Map<String, Object> requestBody) {
		
		Long id = Long.parseLong(requestBody.get("id").toString());
		String content = requestBody.get("content").toString();
		
		ItemInq itemInq = itemInqRepository.findById(id).orElseThrow();
		itemInq.setAnswerOk(AnswerOk.답변완료);
		itemInqRepository.save(itemInq);
		
		ItemInqAnwser itemInqAnwser = new ItemInqAnwser();
		itemInqAnwser.setItemInq(itemInq);
		itemInqAnwser.setContent(content);
		
		itemInqAnswerRepository.save(itemInqAnwser);

	}
	
//	리뷰 답변 수정
	public void itemInqAnswerUpdate(Map<String, Object> requestBody) {
		
		Long id = Long.parseLong(requestBody.get("id").toString());
		String content = requestBody.get("content").toString();
		
		ItemInqAnwser itemInqAnwser = itemInqAnswerRepository.findById(id).orElseThrow();
		itemInqAnwser.setId(id);
		itemInqAnwser.setContent(content);
		
		itemInqAnswerRepository.save(itemInqAnwser);

	}
	
	
//	리뷰 답변 삭제
	public void itemInqAnswerDelete(Map<String, Object> requestBody) {
		
		Long id = Long.parseLong(requestBody.get("id").toString());
		
		ItemInqAnwser itemInqAnwser = itemInqAnswerRepository.findById(id).orElseThrow();
		
		ItemInq itemInq = itemInqRepository.findById(itemInqAnwser.getItemInq().getId()).orElseThrow();
		itemInq.setAnswerOk(AnswerOk.답변대기);
		itemInqRepository.save(itemInq);
		
		itemInqAnswerRepository.delete(itemInqAnwser);
	}
}

