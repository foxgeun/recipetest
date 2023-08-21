package com.recipe.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.dto.RecipeCategoryDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.entity.QBookMark;
import com.recipe.entity.QRecipe;

import jakarta.persistence.EntityManager;

public class RecipeRepositoryCustomImpl implements RecipeRepositoryCustom {

	private JPAQueryFactory queryFactory;
	
	public RecipeRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	@Override
	public Page<RecipeCategoryDto> getRecipeCategoryOrderList(RecipeSearchDto recipeSearchDto, Pageable pageable) {
		
	
		
		return null;
	}

}
