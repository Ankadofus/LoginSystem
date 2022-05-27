package com.app.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.app.user.User;

@Entity
public class ConfirmationToken {

	@Id
	@SequenceGenerator(name = "token_sequence", sequenceName = "token_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_sequence")
	private Long id;
	
	@Column(nullable = false)
	private String token;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private LocalDateTime expiresAt;
	
	private LocalDateTime confirmedAt;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public ConfirmationToken() {}
	
	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, User user) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.user = user;
	}

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final String getToken() {
		return token;
	}

	public final void setToken(String token) {
		this.token = token;
	}

	public final LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public final void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public final LocalDateTime getExpiresAt() {
		return expiresAt;
	}

	public final void setExpiresAt(LocalDateTime expiredAt) {
		this.expiresAt = expiredAt;
	}

	public final LocalDateTime getComfirmedAt() {
		return confirmedAt;
	}

	public final void setComfirmedAt(LocalDateTime comfirmedAt) {
		this.confirmedAt = comfirmedAt;
	}

	public final User getUser() {
		return user;
	}

	public final void setUser(User user) {
		this.user = user;
	}
	
}
