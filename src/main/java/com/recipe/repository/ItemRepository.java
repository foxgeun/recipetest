package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entity.Item;

public interface ItemRepository  extends JpaRepository<Item, Long> , ItemRepositoryCustom{

}
