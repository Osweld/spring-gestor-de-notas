package com.osweld.dev.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;
	@NotEmpty(message = "Este campo no puede quedar vacio")
	@Column(unique = true)
	@Size(min = 6, max = 20,message = "debe contener entre 6 y 20 caracteres el nombre de usuario")
	private String username;
	@NotEmpty(message = "Este campo no puede quedar vacio")
	@Size(min = 6,message = "debe contener entre 6 y 20 caracteres la contraseña")
	private String password;
	private Boolean active;
	@Column(name = "create_at")
	private Date createAt;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "persons_id_person")
	@Valid
	private Person person;
	@ManyToOne()
	@JoinColumn(name = "role_id_rol")
	Rol rol;

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	public User() {
	}
	

	public User(Long id) {
		this.id = id;
	}

	public User(Long id, @NotEmpty String username, @NotEmpty String password, @NotNull Boolean active, Date createAt,
			Person person, Rol rol) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.active = active;
		this.createAt = createAt;
		this.person = person;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", active=" + active +
				", createAt=" + createAt +
				'}';
	}
}
