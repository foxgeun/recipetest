package com.recipe.entity;



import org.springframework.security.crypto.password.PasswordEncoder;

import com.recipe.constant.PrivateOk;
import com.recipe.constant.PromotionOk;
import com.recipe.constant.Role;
import com.recipe.constant.ServiceOk;
import com.recipe.dto.MemberDto;
import com.recipe.dto.MyPageDto;

import groovy.transform.builder.Builder;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member")
@Getter
@Setter
@ToString
public class Member extends BaseEntity {

	
	@Id
	@Column(name="member_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //id
	
	@Column(unique = true) //중복된 값이 들어올 수 없다
	private String email; //이메일
	
	@Column(nullable = false)
	private String password; //비밀번호
	
	@Column(nullable = false)
	private String nickname; //닉네임
	
	@Column(nullable = false)
	private String phoneNumber; //전화번호
	
	private String name; //이름
	
	private String provider; //google
	private String providerId; //google 기본키 id값
	
	private String postCode; //우편번호
	private String address;  //주소
	private String detailAddress; //상세주소
	
	private String introduce; //간략한 소개
	
	private String imgUrl; // 프로필 사진
	
	private String oriImgName; //원래 사진이름 
	
	private String imgName; //바뀐 사진이름
	
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Enumerated(EnumType.STRING)
	private ServiceOk serviceOk;
	

	@Enumerated(EnumType.STRING)
	private PrivateOk privateOk;
	
	@Enumerated(EnumType.STRING)
	private PromotionOk promotionOk;
	
	
//	---현서누나---- 
	
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
	
//	---현서누나---- 
	
	
	
	
// ----용규형님----
	public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		
		
		
		String password = passwordEncoder.encode(memberDto.getPassword());
		
		member.setNickname(memberDto.getNickname());
		member.setEmail(memberDto.getEmail());
		member.setPassword(memberDto.getPassword());
		member.setPhoneNumber(memberDto.getPhoneNumber());
		member.setPassword(password);
		member.setServiceOk(ServiceOk.Y);
		member.setPrivateOk(PrivateOk.Y);
		member.setPromotionOk(PromotionOk.Y);
		member.setName(memberDto.getName());
		
		member.setRole(Role.USER);
		
		return member;
	}
	
	@Builder(builderClassName = "MemberDetailRegister", builderMethodName = "MemberDetailRegister")
    public Member(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String name, String password, String email, Role role, String provider, String providerId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

	public Member() {
		// TODO Auto-generated constructor stub
	}

// ----용규형님----	
	
	
	
}
