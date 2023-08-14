package com.recipe.entity;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="ingredient")
@Getter
@Setter
@ToString
public class Ingredient {
	
	@Id
	@Column(name="ingredient_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 1000) // Adjust the length as needed
	private String basic;
	
	private String seasoning;
	
	
}
