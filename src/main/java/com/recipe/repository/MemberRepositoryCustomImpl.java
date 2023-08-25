package com.recipe.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.recipe.dto.MemberSearchDto;
import com.recipe.entity.Member;
import com.recipe.entity.QMember;

import jakarta.persistence.EntityManager;

public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

	private JPAQueryFactory queryFactory;

	public MemberRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}

	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if (StringUtils.equals("email", searchBy)) {
			// 등록자로 검색시
			return QMember.member.email.like("%" + searchQuery + "%"); // email like %검색어%
		} else if (StringUtils.equals("nickName", searchBy)) {
			return QMember.member.nickname.like("%" + searchQuery + "%"); // nickName like %검색어%
		}

		return null; // 쿼리문을 실행하지 않는다.
	}

	@Override
	public Page<Member> getAdminMemberPage(MemberSearchDto memberSearchDto, Pageable pageable) {

		/*
		 * select * from item where item_nm like %검색어% order by item_id desc;
		 */

		List<Member> content = queryFactory.selectFrom(QMember.member)
				.where(searchByLike(memberSearchDto.getSearchBy(), memberSearchDto.getSearchQuery()))
				.orderBy(QMember.member.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

		/*
		 * select count(*) from item where reg_time = ? and item_sell_status = ? and
		 * item_nm like %검색어% order by item_id desc;
		 */
		long total = queryFactory.select(Wildcard.count).from(QMember.member)
				.where(searchByLike(memberSearchDto.getSearchBy(), memberSearchDto.getSearchQuery())).fetchOne();

		return new PageImpl<>(content, pageable, total);
	}
}
