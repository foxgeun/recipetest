package com.recipe.dto;

import java.time.LocalDateTime;

public interface RecipeCategoryDto {
	
	 Long getMemberId(); // 레시피 작성한 멤버 id
	
	 Long getRecipeId(); // 레시피 id
	
	 int getNum(); // 순차번호
	
	 int getBookmarkCount(); // 레시피에 북마크 수
	
	 int getCount(); // 레시피 조회수
	
	 String getDurTime(); //레시피 소요시간
	
	 String getLevel(); //레시피 난이도
	
	 String getTitle(); //레시피 제목
	
	 String getSubTitle(); //레시피 부제목

	 String getIntro(); //레시피 간단소개
	
	 String getImageUrl(); //레시피 메인사진
	
	 LocalDateTime getRegTime(); //레시피 작성시간
	 
	 String getNickname(); // 레시피 등록한 멤버 닉네임
	 
	 String getMemberImg(); //레시피 등록한 멤버 메인사진
	 
	 String getImgMainOk(); //레시피 메인 여부 Y OR NONE
	 
	 String getCategoryEnum(); // 레시피의 카테고리 명
	 
	 int getReviewCount(); // 레시피에 등록된 리뷰 갯수
	 
	 double getRetingAvg(); //레시피에 등록된 리뷰 평점
	
		/*	
		 *  private Long memberId;
	
	 private Long recipeId;
	
	 private int getNum;
	
	 private int bookmarkCount;
	
	 private int count;
	
	 private String durTime;
	
	 private String Level;
	
	 private String description;
	
	 private String title;
	
	 private String subTitle;

	 private String intro;
	
	 private String imageUrl;
	
	 private LocalDateTime regTime;
	
	 @QueryProjection
	 public RecipeCategoryDto(Long memberId, Long recipeId, int getNum, int bookmarkCount, int count,
             String durTime, String Level, String description, String title, String subTitle,
             String intro, String imageUrl, LocalDateTime regTime) {
				this.memberId = memberId;
				this.recipeId = recipeId;
				this.getNum = getNum;
				this.bookmarkCount = bookmarkCount;
				this.count = count;
				this.durTime = durTime;
				this.Level = Level;
				this.description = description;
				this.title = title;
				this.subTitle = subTitle;
				this.intro = intro;
				this.imageUrl = imageUrl;
				this.regTime = regTime;
}
		 *  
		 *  
		 *  */
	 
	
	
}
