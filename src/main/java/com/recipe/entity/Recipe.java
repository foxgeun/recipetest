package com.recipe.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="recipe") //테이블 이름 지정
@Getter
@Setter
@ToString
public class Recipe {
	
	
    @Id
    @Column(name="recipe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    private String subTitle;
    
    private String intro;
    
    private String durTime;
    
    private String level;
    
    private int count;
    
    @Column(length = 1000) // Adjust the length as needed
    private String description;

    private String imageUrl; // 이미지 URL 필드 추가
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngre> ingredients = new ArrayList<>();

    // Other getters and setters...

    public List<RecipeIngre> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngre> ingredients) {
        this.ingredients = ingredients;
    }
}
