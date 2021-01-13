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

@Entity
@Table(name = "tokens")
public class Token implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_token")
	private Long id;
	@Column(name = "active_token")
	private String activeToken;
	@Column(name = "password_token")
	private String passwordToken;
	@Column(name = "create_at")
	private Date createAt;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user")
	private User user;

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}
}
