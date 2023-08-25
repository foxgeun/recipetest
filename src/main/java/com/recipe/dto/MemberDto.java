package com.recipe.dto;
import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class MemberDto {
	@NotEmpty(message = "이메일은 필수 입력 값입니다")
	@Email(message = "이메일 형식으로 입력해주세요")
	private String email;
	
	@NotEmpty(message = "비밀번호는 필수 입력 값입니다")
	@Length(min = 9, max = 15, message = "비밀번호는 9 ~ 15자 사이로 입력해주세요")
	private String password;
	
	private String passwordConfirm;
	
	@Length(min = 2, max = 8, message = "닉네임은 2~8자 사이로 입력해주세요")
	private String nickname;
	
	@NotEmpty
	@Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "올바른 휴대폰 번호를 입력해주세요.")
	private String phoneNumber;
	
	private String name;
	
	private String provider;
	
	private String providerId;
	
	public static MemberDto memberDto() {
		MemberDto memberDto = new MemberDto();
		
		if(memberDto.getName() != null) {
			memberDto.setNickname(memberDto.getName());
		}
		
		if(memberDto.getNickname() == null) {
			memberDto.setNickname(memberDto.getEmail());
		}
		return memberDto;
		
	}
}