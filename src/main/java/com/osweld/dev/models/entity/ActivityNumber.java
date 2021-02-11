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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "activitynumber")
public class ActivityNumber implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_activity_number")
	private Long id;
	@NotNull(message = "Este campo no puede quedar vacio")
	private Short number;
	@Column(name = "create_at")
	private Date createAt;


	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}


	public ActivityNumber() {
		
	}
	
	


	public ActivityNumber(Long id) {
		this.id = id;
	}


	public ActivityNumber(Long id, @NotNull Short number, Date createAt) {
		this.id = id;
		this.number = number;
		this.createAt = createAt;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Short getNumber() {
		return number;
	}


	public void setNumber(Short number) {
		this.number = number;
	}


	public Date getCreateAt() {
		return createAt;
	}


	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	@Override
	public String toString() {
		return "ActivityNumber{" +
				"id=" + id +
				", number=" + number +
				", createAt=" + createAt +
				'}';
	}
}
