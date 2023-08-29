package com.recipe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recipe.entity.ItemReview;

public interface ItemReviewRepository extends JpaRepository<ItemReview, Long> {

}
