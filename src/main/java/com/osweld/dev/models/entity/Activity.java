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
@Table(name = "activities")
public class Activity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_activity")//Hay que cambiarlo por activity mas adelante
	private Long id;
	@NotEmpty
	private String activity;
	@Column(name = "create_at")
	private Date createAt;
	

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	

	public Activity() {
	
	}
	
	



	public Activity(Long id) {
		this.id = id;
	}



	public Activity(Long id, @NotEmpty String activity, Date createAt) {
		this.id = id;
		this.activity = activity;
		this.createAt = createAt;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getActivity() {
		return activity;
	}


	public void setActivity(String activity) {
		this.activity = activity;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	
}
