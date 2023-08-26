package com.recipe.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocialMemberDto {
	
	@NotEmpty(message = "이메일은 필수 입력 값입니다")
	@Email(message = "이메일 형식으로 입력해주세요")
	private String email;
	
	@Length(min = 2, max = 8, message = "닉네임은 2~8자 사이로 입력해주세요")
	@NotNull
	private String nickname;
	
	@NotEmpty
	@Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "올바른 휴대폰 번호를 입력해주세요.")
	private String phoneNumber;
	
	private String name; //google에서 주는 구글닉네임 
	
	private String provider; //google
 
	private String providerId; //google 기본키 id값 
}
