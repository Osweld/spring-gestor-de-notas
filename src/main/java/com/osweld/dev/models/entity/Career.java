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
@Table(name = "careers")
public class Career implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_career")
	private Long id;
	@NotEmpty
	private String career;
	@Column(name = "create_at")
	private Date createAt;
	
	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	public Career(Long id, @NotEmpty String career, Date createAt) {
	
		this.id = id;
		this.career = career;
		this.createAt = createAt;
	}

	public Career() {
	
	}
	
	

	public Career(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
	
	
	
	
}
