package com.recipe.dto;

import com.recipe.entity.Comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

	private Long commentId;

	private String content;

	public CommentDto(Comment comment) {
		this.commentId = comment.getId();
		this.content = comment.getContent();
	}

}
