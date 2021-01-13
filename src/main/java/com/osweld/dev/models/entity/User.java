package com.osweld.dev.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;
	@NotEmpty
	@Column(unique = true)
	private String username;
	@NotEmpty
	private String password;
	@NotNull
	private Boolean active;
	@Column(name = "create_at")
	private Date createAt;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_person")
	private Person person;
	@OneToMany(mappedBy = "user")
	private List<Rol> rol;

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}
}
