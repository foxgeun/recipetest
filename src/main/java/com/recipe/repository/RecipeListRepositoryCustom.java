package com.recipe.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.recipe.dto.MemberSearchDto;
import com.recipe.entity.Recipe;

public interface RecipeListRepositoryCustom {
	Page<Recipe> getAdminRecipePage(MemberSearchDto memberSearchDto, Pageable pageable);
}
