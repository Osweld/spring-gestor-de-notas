package com.osweld.dev.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "subjects")
public class Subject implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_subject")
	private Long id;
	@NotEmpty(message = "Este campo no puede quedar vacio")
	private String subject;
	@Column(name = "create_at")
	private Date createAt;

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	public Subject() {
	}
	
	

	public Subject(Long id) {
		this.id = id;
	}

	public Subject(Long id, @NotEmpty String subject, Date createAt) {
		this.id = id;
		this.subject = subject;
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	@Override
	public String toString() {
		return "Subject{" +
				"id=" + id +
				", subject='" + subject + '\'' +
				", createAt=" + createAt +
				'}';
	}
}
