package com.recipe.service;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recipe.entity.Item;
import com.recipe.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
	
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

}