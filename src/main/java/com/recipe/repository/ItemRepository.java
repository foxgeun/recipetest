package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipe.entity.Item;


import jakarta.transaction.Transactional;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	@Transactional
	@Query(value = "select * from item", nativeQuery = true)
	List<Item> getAllItemList();	
	
}
