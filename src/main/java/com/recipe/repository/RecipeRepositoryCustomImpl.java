package com.recipe.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.SubQueryExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.core.types.dsl.Expressions;
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

		List<RecipeCategoryDto> content = queryFactory
		        .select(Projections.constructor(RecipeCategoryDto.class,
		                r.member.id,
		                r.id,
		                ExpressionUtils.count(bm),  coalesce(bm.count(), 0),
		                ExpressionUtils.coalesce(rv.count(), 0),
		                ExpressionUtils.coalesce(rv.reting.avgCoalesce(0.0), 0.0),
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
		                mi.memberId.isNull().then("none").otherwise(mi.imgUrl),
		                mi.memberId.isNull().then("none").otherwise(mi.imgMainOk),
		                c.categoryEnum
		        ))
		        .from(r)
		        .leftJoin(bm).on(r.recipeId.eq(bm.recipe.id))
		        .leftJoin(rv).on(r.recipeId.eq(rv.recipe.id))
		        .join(m).on(r.member.id.eq(m.memberId))
		        .leftJoin(mi).on(m.memberId.eq(mi.memberId).and(mi.imgMainOk.eq("Y")))
		        .join(c).on(r.recipeId.eq(c.recipeId))
		        .orderBy(r.regTime.desc())
		        .fetch();
		
		
		    // Assuming you have a method to convert content to a Page
		    return convertToPage(content, pageable);
		}
	
	
}
