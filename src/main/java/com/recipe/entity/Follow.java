package com.recipe.entity;

import java.security.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Entity
@Table(
		uniqueConstraints = {
				@UniqueConstraint(
						name="follow_uk",
						columnNames = {"toUserId", "fromUserId"}
				)
		}
)
public class Follow {
	

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	

	private Long toUserId;
	
	@JoinColumn(name = "fromUserId")
	@ManyToOne(fetch = FetchType.LAZY)
	private Member fromUser;
	
	
	@CreationTimestamp 
	private Timestamp createDate;
	
	// JPQL 사용하지 않을시
	public Follow(Long toUserId, Member fromUser) {
		this.toUserId = toUserId;
		this.fromUser = fromUser;
	}
	
}