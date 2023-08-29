package com.recipe.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.constant.ImgMainOk;
import com.recipe.dto.ItemCategoryDto;
import com.recipe.dto.ItemDetailDto;
import com.recipe.dto.ItemDetailImgDto;
import com.recipe.dto.ItemImgDto;
import com.recipe.dto.ItemSearchDto;
import com.recipe.entity.QItem;
import com.recipe.entity.QItemDetailImg;
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
		                ir.count().as("reviewCount") // ir.item.count()에서 ir.count()로 변경
		            )
		        )
		        .from(i)
		        .join(im).on(im.item.id.eq(i.id).and(im.imgMainOk.eq(ImgMainOk.Y))) // item_img 테이블과 조인할 때 조인 조건 수정
		        .leftJoin(ir).on(i.id.eq(ir.item.id))
		        .groupBy(i.id, i.itemNm, i.itemSubNm, i.price, i.itemSellStatus, i.itemCategoryEnum,
		                i.sale, im.imgUrl, i.regTime)
		        .offset(pageable.getOffset())
		        .limit(pageable.getPageSize())
		        .fetch();
		    
		    Long total = queryFactory
		        .select(Wildcard.count)
		        .from(i)
		        .join(im).on(im.item.id.eq(i.id).and(im.imgMainOk.eq(ImgMainOk.Y))) // item_img 테이블과 조인할 때 조인 조건 수정
		        .leftJoin(ir).on(i.id.eq(ir.item.id))
		        .fetchOne();
		    
		    return new PageImpl<>(content, pageable, total);
	}



	@Override
	public ItemDetailDto getItemDetailList(Long itemId) {
		
		QItem i = QItem.item;
		QItemImg im = QItemImg.itemImg;
		QItemReview ir = QItemReview.itemReview;
		QItemDetailImg idm = QItemDetailImg.itemDetailImg;
		
		List<ItemImgDto> itemImgDto = queryFactory
										.select(
												Projections.constructor(
														ItemImgDto.class,
														im.id,
														im.imgUrl,
														im.imgName,
														im.imgOriName,
														im.imgMainOk
														))
										.from(im)
										.where(im.item.id.eq(itemId))
										.fetch();
		
		List<ItemDetailImgDto> itemDetialImgDto = queryFactory
				.select(
						Projections.constructor(
								ItemDetailImgDto.class,
								idm.id,
								idm.imgUrl,
								idm.imgName,
								idm.imgOriName
								))
				.from(idm)
				.where(idm.item.id.eq(itemId))
				.fetch();
		
		
		ItemDetailDto itemDetail = queryFactory
											.select(
													Projections.constructor(
													ItemDetailDto.class,
													i.id,
													i.itemNm,
													i.itemSubNm,
													i.price,
													i.itemSellStatus,
													i.itemCategoryEnum,
													i.sale,
													ir.reting.avg().coalesce(0.0).as("retingAvg"),
													ir.item.count().as("reviewCount")
													))
											.from(i)
											.leftJoin(ir).on(ir.item.id.eq(i.id))
											.where(i.id.eq(itemId))
											.fetchOne();
		
		itemDetail.setItemImgDtoList(itemImgDto);
		itemDetail.setItemDetailImgDtoList(itemDetialImgDto);
		
		return itemDetail;
	}
	
	
	
	
	
}
