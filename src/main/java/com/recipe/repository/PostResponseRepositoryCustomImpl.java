package com.recipe.repository;

import java.util.List;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.dto.PostResponseDto;

import jakarta.persistence.EntityManager;

public class PostResponseRepositoryCustomImpl implements PostResponseRepositoryCustom {

	private JPAQueryFactory queryFactory;

	public  PostResponseRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public List<PostResponseDto> getPostResponse() {
		// TODO Auto-generated method stub
		return null;
	}

}
