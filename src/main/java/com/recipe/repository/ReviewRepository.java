package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.recipe.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{
	
	@Query(value="select * from review where member_id = ?1" , nativeQuery = true)
	List<Review> getReview(Long id);
}
