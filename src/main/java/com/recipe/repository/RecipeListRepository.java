package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipe.entity.RecipeListEntity;



public interface RecipeListRepository extends JpaRepository<RecipeListEntity, Long> {
	

	
	boolean existsByTitle(String title);
}
