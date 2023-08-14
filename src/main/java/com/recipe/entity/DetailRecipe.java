package com.recipe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="detailRecipe")
@Getter
@Setter
@ToString
public class DetailRecipe {
	
	@Id
	@Column(name="ingredient_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String recipeImg;
	
	private String recipes;
	
	
	
	
}
