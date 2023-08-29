package com.recipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="recipe_order")
@Getter
@Setter
public class RecipeOrder {
	
	@Id
	@Column(name="recipe_order_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private int order_number;
	
	private String content;
	
	private String imgUrl;
	
	private String recipeImg;
	
	private String recipes;
	

	
}
