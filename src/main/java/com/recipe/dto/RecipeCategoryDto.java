package com.recipe.dto;

import java.time.LocalDateTime;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RecipeCategoryDto {

	/*
	 * Long getMemberId(); // 레시피 작성한 멤버 id
	 * 
	 * Long getRecipeId(); // 레시피 id
	 * 
	 * int getNum(); // 순차번호
	 * 
	 * int getBookmarkCount(); // 레시피에 북마크 수
	 * 
	 * int getCount(); // 레시피 조회수
	 * 
	 * String getDurTime(); //레시피 소요시간
	 * 
	 * String getLevel(); //레시피 난이도
	 * 
	 * String getTitle(); //레시피 제목
	 * 
	 * String getSubTitle(); //레시피 부제목
	 * 
	 * String getIntro(); //레시피 간단소개
	 * 
	 * String getImageUrl(); //레시피 메인사진
	 * 
	 * LocalDateTime getRegTime(); //레시피 작성시간
	 * 
	 * String getNickname(); // 레시피 등록한 멤버 닉네임
	 * 
	 * String getMemberImg(); //레시피 등록한 멤버 메인사진
	 * 
	 * String getImgMainOk(); //레시피 메인 여부 Y OR NONE
	 * 
	 * String getCategoryEnum(); // 레시피의 카테고리 명
	 * 
	 * int getReviewCount(); // 레시피에 등록된 리뷰 갯수
	 * 
	 * double getRetingAvg(); //레시피에 등록된 리뷰 평점
	 */

     private Long memberId;
	
	 private Long recipeId;
	
	 private int bookmarkCount;
	
	 private int count;
	
	 private String durTime;
	
	 private String Level;
	
	 private String title;
	
	 private String subTitle;

	 private String intro;
	
	 private String imageUrl;
	
	 private LocalDateTime regTime;
	
	 private String nickname;
	 private String memberImg;
	 private String imgMainOk;
	 private String categoryEnum;
	 private int reviewCount;
	 
	 private double retingAvg;

	
	  @QueryProjection 
	  public RecipeCategoryDto
	  (Long memberId, Long recipeId,  int bookmarkCount, int count, 
	  String durTime, String Level, String title, String subTitle, 
	  String intro, String imageUrl,
	  LocalDateTime regTime , String nickname , String memberImg , 
	  String imgMainOk , String categoryEnum , int reviewCount , double retingAvg){ 
	  this.memberId = memberId; 
	  this.recipeId = recipeId;
	  this.bookmarkCount = bookmarkCount; 
	  this.count = count;
	  this.durTime = durTime; 
	  this.Level = Level; 
	  this.title = title; 
	  this.subTitle = subTitle; 
	  this.intro = intro;
	  this.imageUrl = imageUrl; 
	  this.regTime = regTime;
	  this.memberImg = memberImg;
	  this.imgMainOk = imgMainOk;
	  this.categoryEnum = categoryEnum;
	  this.reviewCount = reviewCount;
	  this.retingAvg = retingAvg;
	  }
	  
	  
	 

}
