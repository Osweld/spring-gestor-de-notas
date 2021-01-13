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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;



@Entity
@Table(name = "assignments")
public class Assignment implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_assignment")
	private Long id;
	@NotNull
	private Short percent;
	@DecimalMax(value = "10")
	@DecimalMin(value = "0")
	private Double score;
	@Column(name = "create_at")
	private Date createAt;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_subjects_per_semester")
	private SubjectsPerSemester subjectsPerSemester;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_activities")
	private Activity activity;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_activity_number")
	private ActivityNumber activityNumber;
	
	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

}
