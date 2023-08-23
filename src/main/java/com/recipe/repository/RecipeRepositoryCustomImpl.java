package com.recipe.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.constant.ImgMainOk;
import com.recipe.dto.QRecipeCategoryDto;
import com.recipe.dto.RecipeCategoryDto;
import com.recipe.dto.RecipeSearchDto;
import com.recipe.entity.QBookMark;
import com.recipe.entity.QCategory;
import com.recipe.entity.QMember;
import com.recipe.entity.QMemberImg;
import com.recipe.entity.QRecipe;
import com.recipe.entity.QReview;

import jakarta.persistence.EntityManager;

public class RecipeRepositoryCustomImpl implements RecipeRepositoryCustom {
	
	private JPAQueryFactory queryFactory;

	public RecipeRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	@Override
	public Page<RecipeCategoryDto> getRecipeCategoryReviewBestList(Pageable pageable, RecipeSearchDto recipeSearchDto) {
		
		QRecipe r = QRecipe.recipe;
		QBookMark bm = QBookMark.bookMark;
		QReview rv = QReview.review;
		QMember m = QMember.member;
		QMemberImg mi = QMemberImg.memberImg;
		QCategory c = QCategory.category;
		
		
		
		SubQueryExpression<Long> bookmarkCountSubQuery = JPAExpressions
		        .select(bm.recipe.id.count())
		        .from(bm)
		        .where(bm.recipe.id.eq(r.id))
		        .groupBy(bm.recipe.id);

		// 리뷰 수 서브쿼리
		SubQueryExpression<Long> reviewCountSubQuery = JPAExpressions
		        .select(rv.recipe.id.count())
		        .from(rv)
		        .where(rv.recipe.id.eq(r.id))
		        .groupBy(rv.recipe.id);

		// 평균 평점 서브쿼리
		SubQueryExpression<Double> retingAvgSubQuery = JPAExpressions
		        .select(rv.reting.avg().coalesce(0.0))
		        .from(rv)
		        .where(rv.recipe.id.eq(r.id))
		        .groupBy(rv.recipe.id);

		List<RecipeCategoryDto> content = queryFactory
		        .select(Projections.constructor(
		                RecipeCategoryDto.class,
		                r.id,
		                r.count,
		                r.durTime,
		                r.imageUrl,
		                r.level,
		                r.subTitle,
		                r.title,
		                r.member.id,
		                r.regTime,
		                r.intro,
		                m.nickname,
		                new CaseBuilder().when(mi.member.id.isNull()).then("none").otherwise(mi.imgUrl),
		                new CaseBuilder().when(mi.member.id.isNull()).then(ImgMainOk.NONE).otherwise(mi.imgMainOk),
		                c.categoryEnum,
		                bookmarkCountSubQuery,
		                reviewCountSubQuery,
		                retingAvgSubQuery
		        ))
		        .from(r)
		        .leftJoin(m).on(r.member.id.eq(m.id))
		        .leftJoin(mi).on(m.id.eq(mi.member.id).and(mi.imgMainOk.eq(ImgMainOk.Y)))
		        .join(c).on(r.id.eq(c.recipe.id))
		        .fetch();

		long total = queryFactory
		        .select(r.count())
		        .from(r)
		        .join(m).on(r.member.id.eq(m.id))
		        .leftJoin(mi).on(m.id.eq(mi.member.id).and(mi.imgMainOk.eq(ImgMainOk.Y)))
		        .join(c).on(r.id.eq(c.recipe.id))
		        .fetchOne();

		return new PageImpl<>(content, pageable, total);
		}

	
	
	
}
