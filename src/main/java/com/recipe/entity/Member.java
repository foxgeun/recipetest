package com.recipe.entity;



import org.springframework.security.crypto.password.PasswordEncoder;

import com.recipe.constant.PrivateOk;
import com.recipe.constant.PromotionOk;
import com.recipe.constant.Role;
import com.recipe.constant.ServiceOk;
import com.recipe.dto.MemberDto;


import com.recipe.dto.MyPageDto;
import jakarta.persistence.*;
import lombok.*;




@NoArgsConstructor(access = AccessLevel.PROTECTED)
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



	
	@Column(nullable = false)
	private String nickname;

	
	private String postCode;
	private String address;
	private String detailAddress;

	@Column(nullable = false)
	private String phoneNumber;

	
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

	
	private String name;
	
	private String provider; //google
 
	private String providerId; //google 기본키 id값 
	
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
	
	



}
