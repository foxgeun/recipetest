package com.recipe.entity;



import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.recipe.constant.PrivateOk;
import com.recipe.constant.PromotionOk;
import com.recipe.constant.Role;
import com.recipe.constant.ServiceOk;
import com.recipe.dto.MyPageDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
	

    
    // 멤버에 팔로워 추가
    public void addFollower(Follower follower) {
        followers.add(follower);
        follower.setFollowing(this);
    }

    // 멤버에서 팔로워 제거
    public void removeFollower(Follower follower) {
        followers.remove(follower);
        follower.setFollowing(null);
    }

    
    // 멤버에 팔로잉 추가
    public void addFollowing(Following following) {
        followings.add(following);
        following.setFollower(this);
    }

    // 멤버에서 팔로잉 제거
    public void removeFollowing(Following following) {
        followings.remove(following);
        following.setFollower(null);
    }
	
    // 다른 멤버를 팔로우하는 팔로워 목록
    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follower> followers = new ArrayList<>();

    // 다른 멤버를 팔로잉하는 목록
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Following> followings = new ArrayList<>();

    // 멤버가 팔로우하는 다른 멤버를 추가하는 메서드
    public void followMember(Member memberToFollow) {
        Following following = new Following(this, memberToFollow);
        followings.add(following);
        memberToFollow.addFollower(new Follower(memberToFollow, this));
    }

    // 멤버가 팔로우를 취소하는 메서드
    public void unfollowMember(Member memberToUnfollow) {
        Following following = getFollowing(memberToUnfollow);
        if (following != null) {
            followings.remove(following);
            memberToUnfollow.removeFollower(new Follower(memberToUnfollow, this));
        }
    }
    // 팔로잉 목록에서 특정 멤버 찾기
    private Following getFollowing(Member memberToFind) {
        for (Following following : followings) {
            if (following.getFollowing().equals(memberToFind)) {
                return following;
            }
        }
        return null;
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
