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
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "role")
public class Rol implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Long id;
	@NotEmpty(message = "Este campo no puede quedar vacio")
	private String rol;
	@Column(name = "create_at")
	private Date createAt;

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	public Rol() {
	
	}
	
	

	public Rol(Long id) {
		this.id = id;
	}

	public Rol(Long id, @NotEmpty String rol, Date createAt) {
		this.id = id;
		this.rol = rol;
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	@Override
	public String toString() {
		return "Rol{" +
				"id=" + id +
				", rol='" + rol + '\'' +
				", createAt=" + createAt +
				'}';
	}
}
