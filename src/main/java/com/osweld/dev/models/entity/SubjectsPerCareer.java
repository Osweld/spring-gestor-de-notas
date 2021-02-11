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
@Table(name = "subjectspercareer")
public class SubjectsPerCareer implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name = "id_subjects_per_career")
	private Long id;
	@Column(name = "create_at")
	private Date createAt;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "careers_id_career")
	private Career career;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cycles_id_cycle")
	private Cycle cycle;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subjects_id_subject")
	private Subject subject;
	

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}


	public SubjectsPerCareer() {
	
	}
	
	


	public SubjectsPerCareer(Long id) {
		this.id = id;
	}


	public SubjectsPerCareer(Long id, Date createAt, Career career, Cycle cycle, Subject subject) {
		this.id = id;
		this.createAt = createAt;
		this.career = career;
		this.cycle = cycle;
		this.subject = subject;
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


	public Career getCareer() {
		return career;
	}


	public void setCareer(Career career) {
		this.career = career;
	}


	public Cycle getCycle() {
		return cycle;
	}


	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}


	public Subject getSubject() {
		return subject;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	@Override
	public String toString() {
		return "SubjectsPerCareer{" +
				"id=" + id +
				", createAt=" + createAt +
				'}';
	}
}
