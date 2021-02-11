package com.osweld.dev.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tokens")
public class Token implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_token")
	private Long id;
	@NotEmpty(message = "Este campo no puede quedar vacio")
	private String token;
	@Column(name = "expiration_date")
	private Date expirationDate;
	@NotNull
	private Boolean activated;
	@Column(name = "create_at")
	private Date createAt;
	@OneToOne(cascade = CascadeType.ALL)//Investigar mas sobre esto
	@JoinColumn(name = "users_id_user")
	private User user;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tokentype_id_tokentype")
	private TokenType tokenType;

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	public Token() {
	
	}

	public Token(Long id, @NotEmpty String token, Date expirationDate,Boolean activated, Date createAt, User user,TokenType tokenType) {
		this.id = id;
		this.token = token;
		this.expirationDate = expirationDate;
		this.activated = activated;
		this.createAt = createAt;
		this.user = user;
		this.tokenType = tokenType;
	}

	public Token(String token,Boolean activated, Date expirationDate,User user,TokenType tokenType) {
		this.token = token;
		this.activated = activated;
		this.expirationDate = expirationDate;
		this.user = user;
		this.tokenType = tokenType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	public void setTokenType(TokenType tokenType) {
		this.tokenType = tokenType;
	}

	@Override
	public String toString() {
		return "Token{" +
				"id=" + id +
				", token='" + token + '\'' +
				", expirationDate=" + expirationDate +
				", activated=" + activated +
				", createAt=" + createAt +
				'}';
	}
}
	
	

