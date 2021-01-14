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

@Entity
@Table(name = "semesters")
public class Semester implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_semester")
	private Long id;
	@Column(name = "create_at")
	private Date createAt;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_user")
	private User user;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_year")
	private Year year;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cycle")
	private Cycle cycle;

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	public Semester() {
	}

	
	public Semester(Long id) {
		this.id = id;
	}

	public Semester(Long id, Date createAt, User user, Year year, Cycle cycle) {
		this.id = id;
		this.createAt = createAt;
		this.user = user;
		this.year = year;
		this.cycle = cycle;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}
	
	
	
	
}
