package com.recipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recipe.entity.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

	
	@Query(value = "SELECT * FROM recipe WHERE member_id = ?1  ORDER BY recipe_id DESC" , nativeQuery = true)
	List<Recipe> findRecipe(Long id);
	
	@Query(value = "SELECT * FROM recipe WHERE member_id = ?1 AND writing_status = 'PUBLISHED' ORDER BY recipe_id DESC ", nativeQuery = true)
	List<Recipe> findAllRecipe(Long id);
	
	
	@Query(value = "SELECT r.* FROM recipe r " +
            "JOIN (SELECT recipe_id, COUNT(*) AS bookmark_count " +
            "      FROM book_mark " +
            "      GROUP BY recipe_id) b ON r.recipe_id = b.recipe_id " +
            "WHERE r.member_id = ?1 AND writing_status = 'PUBLISHED' " +
            "ORDER BY b.bookmark_count DESC " +
            "LIMIT 5", nativeQuery = true)
List<Recipe> getPopularRecipe(Long id);
	
}
