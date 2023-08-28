package com.recipe.dto;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

	private Long id;

	private String nickname;

	private String email;

	private String title;

	private String writer;

	private String commentContent;

	@QueryProjection
	public CommentDto(Long id, String nickname, String email, String title, String writer, String commentContent) {
		this.id = id;
		this.nickname = nickname;
		this.email = email;
		this.title = title;
		this.writer = writer;
		this.commentContent = commentContent;
	}
}
