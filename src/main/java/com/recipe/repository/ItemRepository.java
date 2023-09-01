package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipe.entity.Item;


import jakarta.transaction.Transactional;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	@Transactional
	@Query(value = "SELECT * FROM item ORDER BY RAND()", nativeQuery = true)
	List<Item> getAllItemList();
	
	@Query(value = "select * from item where item_id = ?1", nativeQuery = true)
	Item getItemByItemId(int Id);
	
	@Query(value = "select * from item where category = ?1", nativeQuery = true)
	List<Item> getItemByCategoryEnum(String category);

}
