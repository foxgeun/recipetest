package com.recipe.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="recipe") //테이블 이름 지정
@Getter
@Setter
@ToString
public class RecipeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Column(length = 1000) // Adjust the length as needed
    private String description;

    private String imageUrl; // 이미지 URL 필드 추가

    // Getters and setters
}

