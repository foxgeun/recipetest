package com.recipe.entity;

import jakarta.persistence.CascadeType;
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
@Table(name="comment")
@Getter
@Setter
@ToString
public class Comment extends BaseEntity{

	@Id
	@Column(name="comment_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String content;
	
	
	@ManyToOne(fetch = FetchType.LAZY , cascade= CascadeType.REMOVE)
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
}
