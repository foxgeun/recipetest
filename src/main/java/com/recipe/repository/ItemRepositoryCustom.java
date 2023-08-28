package com.recipe.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.recipe.dto.ItemCategoryDto;
import com.recipe.dto.ItemSearchDto;

public interface ItemRepositoryCustom {
	
	Page<ItemCategoryDto> getItemCategoryList(Pageable pageable , ItemSearchDto itemSearchDto);
	
}
