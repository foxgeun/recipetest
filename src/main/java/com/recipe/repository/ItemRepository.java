package com.recipe.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipe.entity.Item;


import jakarta.transaction.Transactional;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
	

	@Transactional
	@Query(value = "SELECT * FROM item ORDER BY RAND() limit 12", nativeQuery = true)
	List<Item> getAllItemList();
	
	@Query(value = "select * from item where item_id = ?1", nativeQuery = true)
	Item getItemByItemId(int Id);

	 Optional<Item> findById(Long id);

	
	@Query(value = "select * from item where category = ?1", nativeQuery = true)
	List<Item> getItemByCategoryEnum(String category);

}