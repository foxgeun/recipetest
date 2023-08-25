package com.recipe.entity;



import org.springframework.security.crypto.password.PasswordEncoder;


import com.recipe.constant.PrivateOk;
import com.recipe.constant.PromotionOk;
import com.recipe.constant.Role;
import com.recipe.constant.ServiceOk;

import com.recipe.dto.MyPageDto;

import com.recipe.dto.MemberDto;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true) // 중복된 값이 들어올 수 없다
	private String email;

	private String password;

	private String nickname;

	private String phoneNumber;
	
	private String name;
	
	private String postCode;
	private String address;
	private String detailAddress;
	
	private String introduce;
	
	private String imgUrl;
	
	private String oriImgName;
	
	private String imgName;

	



	@Enumerated(EnumType.STRING)
	private Role role;

	@Enumerated(EnumType.STRING)
	private ServiceOk serviceOk;

	@Enumerated(EnumType.STRING)
	private PrivateOk privateOk;

	@Enumerated(EnumType.STRING)
	private PromotionOk promotionOk;

	
	
	
	//member 엔티티 수정
	public void editMember(MyPageDto myPageDto) {
		this.nickname = myPageDto.getNickname();
		this.phoneNumber = myPageDto.getPhoneNumber();
		this.name = myPageDto.getName();
		this.password = myPageDto.getPassword();
		this.introduce = myPageDto.getIntroduce();
		this.detailAddress = myPageDto.getDetailAddress();
		this.postCode = myPageDto.getPostCode();
		this.address = myPageDto.getAddress();

	}
	
	//이미지 업데이트
	public void updateImg(String oriImgName, String imgName, String imgUrl) {
		this.imgUrl = imgUrl;
		this.imgName = imgName;
		this.oriImgName = oriImgName;
	}
	
	

	// MemberFormDto를 -> Member 엔티티 객체로 변환
	public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
		// 패스워드 암호화
		String password = passwordEncoder.encode(memberDto.getPassword());

		Member member = new Member();
		member.setNickname(memberDto.getNickname());
		member.setEmail(memberDto.getEmail());
		member.setPhoneNumber(memberDto.getPhoneNumber());
		member.setPassword(password);
		// member.setRole(Role.ADMIN); //관리자로 가입할때
		member.setRole(Role.USER); // 일반 사용자로 가입할때

		return member;
	}


}
