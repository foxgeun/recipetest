package com.recipe.entity;



import org.springframework.security.crypto.password.PasswordEncoder;

import com.recipe.constant.PrivateOk;
import com.recipe.constant.PromotionOk;
import com.recipe.constant.Role;
import com.recipe.constant.ServiceOk;
import com.recipe.dto.MemberDto;

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
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String nickname;
	
	@Column(nullable = false)
	private String phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@Enumerated(EnumType.STRING)
	private ServiceOk serviceOk;
	
	@Enumerated(EnumType.STRING)
	private PrivateOk privateOk;
	
	@Enumerated(EnumType.STRING)
	private PromotionOk promotionOk;
	
	public static Member createMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {
		Member member = new Member();
		
		String password = passwordEncoder.encode(memberDto.getPassword());
		
		member.setEmail(memberDto.getEmail());
		member.setPassword(memberDto.getPassword());
		member.setNickname(memberDto.getNickname());
		member.setPhoneNumber(memberDto.getPhoneNumber());
		member.setPassword(password);
		member.setRole(Role.ADMIN);
		
		return member;
	}
	
}
