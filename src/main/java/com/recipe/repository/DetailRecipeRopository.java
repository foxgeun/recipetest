package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entity.DetailRecipe;


public interface DetailRecipeRopository extends JpaRepository<DetailRecipe, Long>{
	
	
}
