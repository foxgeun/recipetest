package com.recipe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeSearchDto {
	
	private String mainCategory;
	
	private String subCategory;
	
	private String type;
	
	private String searchBy;
	
	private String searchQuery = "";
	
	
	 public String generateOrderByClause() {
		 
	        if ("reviewAvg".equals(type)) {
	            return "COALESCE(rv_avg, 0) DESC";
	        } else if ("order".equals(type)) {
	            return "r.reg_time DESC";
	        } else if ("bookMarkBest".equals(type)) {
	            return "ifnull(bm_count, 0) DESC";
	        } else if ("reviewCount".equals(type)) {
	            return "ifnull(rv_count, 0) DESC";
	        } else {
	            // 기본 정렬 조건을 여기에 추가
	            return "r.reg_time DESC"; // 
	        }
	    }
}
