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
public class RecipeIngre {
	
	@Id
	@Column(name="ingredient_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String ingreImg;
	
	@Column(length = 1000) // Adjust the length as needed
	private String basicIngreName;
	
	@Column(length = 1000) // Adjust the length as needed
	private String seasonIngreName;
	
	private int RecipeCount;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;
	
	
	
}
