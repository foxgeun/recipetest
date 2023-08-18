package com.recipe.entity;

import java.time.LocalDateTime;
import java.util.Date;

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
    
    private LocalDateTime durTime;
    
    private int level;
    
    private int count;
    
    @Column(length = 1000) // Adjust the length as needed
    private String description;

    private String imageUrl; // 이미지 URL 필드 추가
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

    // Getters and setters
}
