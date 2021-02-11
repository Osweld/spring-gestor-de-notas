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
@Table(name = "subjectspersemester")
public class SubjectsPerSemester implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_subjects_per_semester")
	private Long id;
	@Column(name = "create_at")
	private Date createAt;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "semesters_id_semester")
	private Semester semester;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subjectspercareer_id_subjects_per_career")
	private SubjectsPerCareer subjectsPerCareer;
	

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}


	public SubjectsPerSemester() {
		
	}

	

	public SubjectsPerSemester(Long id) {
		this.id = id;
	}


	public SubjectsPerSemester(Long id, Date createAt, Semester semester, SubjectsPerCareer subjectsPerCareer) {
		this.id = id;
		this.createAt = createAt;
		this.semester = semester;
		this.subjectsPerCareer = subjectsPerCareer;
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


	public Semester getSemester() {
		return semester;
	}


	public void setSemester(Semester semester) {
		this.semester = semester;
	}


	public SubjectsPerCareer getSubjectsPerCareer() {
		return subjectsPerCareer;
	}


	public void setSubjectsPerCareer(SubjectsPerCareer subjectsPerCareer) {
		this.subjectsPerCareer = subjectsPerCareer;
	}


	@Override
	public String toString() {
		return "SubjectsPerSemester{" +
				"id=" + id +
				", createAt=" + createAt +
				'}';
	}
}
