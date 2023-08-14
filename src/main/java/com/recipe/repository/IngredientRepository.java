package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entity.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>  {

	
	
}
