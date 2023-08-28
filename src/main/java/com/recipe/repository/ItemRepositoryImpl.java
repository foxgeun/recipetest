package com.recipe.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.constant.ImgMainOk;
import com.recipe.dto.ItemCategoryDto;
import com.recipe.dto.ItemSearchDto;
import com.recipe.entity.QItem;
import com.recipe.entity.QItemImg;
import com.recipe.entity.QItemReview;

import jakarta.persistence.EntityManager;

public class ItemRepositoryImpl implements ItemRepositoryCustom  {
	
	private JPAQueryFactory queryFactory;

	public ItemRepositoryImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	
	
	@Override
	public Page<ItemCategoryDto> getItemCategoryList(Pageable pageable , ItemSearchDto itemSearchDto) {
		
		QItem i = QItem.item;
		QItemImg im = QItemImg.itemImg;
		QItemReview ir = QItemReview.itemReview;
		
		
		List<ItemCategoryDto> content = queryFactory
								.select(
										Projections.constructor(
												ItemCategoryDto.class,
												i.id,
												i.itemNm,
												i.itemSubNm,
												i.price,
												i.itemSellStatus,
												im.imgUrl,
												i.itemCategoryEnum,
												i.regTime,
												i.sale,
												ir.reting.avg().coalesce(0.0).as("retingAvg"),
												ir.item.count().as("reviewCount")
												))
								.from(im)
								.join(im.item , i)
								.leftJoin(ir).on(i.id.eq(ir.item.id))
								.orderBy(i.regTime.desc())
								.where(im.imgMainOk.eq(ImgMainOk.Y))
								.groupBy(i.id , i.itemNm,i.itemSubNm,i.price,i.itemSellStatus,i.itemCategoryEnum,
										i.sale,im.imgUrl,i.regTime)
								  .offset(pageable.getOffset())
									.limit(pageable.getPageSize())
							        .fetch();
					
			Long total = queryFactory
						.select(Wildcard.count)
						.from(im)
						.join(im.item , i)
						.fetchOne();
		
		
			return new PageImpl<>(content, pageable, total);
	}

}
