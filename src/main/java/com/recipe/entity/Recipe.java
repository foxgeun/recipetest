package com.recipe.entity;

import com.recipe.constant.CategoryEnum;
import com.recipe.constant.WritingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private Long id; // id

    private String title; //제목
    
    private String subTitle; //부제목
    
    private String intro; //레시피 소개
    
    //소요시간
    private String durTime;
    
    //난이도
    private String level;
    
    private int count = 0; //조회수
    
    private String imageUrl; // 메인이미지 (이미지 URL필드 추가)
    
    private String imgName; //이미지 이름
    
    @Enumerated(EnumType.STRING) //레시피타입별 카테고리
	private CategoryEnum categorys;
    
    @Enumerated(EnumType.STRING) //레시피 등록,임시저장
    private WritingStatus writingStatus;
    
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
	
	@Column(length = 1000) // Adjust the length as needed
    private String description;
	

	public void updateRecipeImg(String imageUrl , String imgName) {
		this.imageUrl = imageUrl;
		this.imgName = imgName;
	}
}
