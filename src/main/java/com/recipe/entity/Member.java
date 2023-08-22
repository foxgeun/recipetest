package com.recipe.entity;



import org.springframework.web.multipart.MultipartFile;

import com.recipe.constant.PrivateOk;
import com.recipe.constant.PromotionOk;
import com.recipe.constant.Role;
import com.recipe.constant.ServiceOk;
import com.recipe.dto.MyPageDto;

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
	private Long id;
	
	@Column(unique = true) //중복된 값이 들어올 수 없다
	private String email;
	
	private String password;
	
	private String nickname;
	
	private String phoneNumber;
	
	private String name;
	
	private String postCode;
	private String roadAddress;
	private String jibunAddress;
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
		this.address = myPageDto.getAddress();
		this.password = myPageDto.getPassword();
		this.introduce = myPageDto.getIntroduce();

	}
	
	//이미지 업데이트
	public void updateImg(String oriImgName, String imgName, String imgUrl) {
		this.imgUrl = imgUrl;
		this.imgName = imgName;
		this.oriImgName = oriImgName;
	}
	
	
}
