package com.recipe.dto;

import org.modelmapper.ModelMapper;

import com.recipe.entity.Comment;
import com.recipe.entity.Recipe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

	private Long commentId;

	private String content;
	
	private int commentCount;

	public CommentDto(Comment comment) {
		this.commentId = comment.getId();
		this.content = comment.getContent();
	}

	private static ModelMapper modelMapper = new ModelMapper();

	public static CommentDto of(Comment comment) {
		return modelMapper.map(comment, CommentDto.class);
	}
	
}
