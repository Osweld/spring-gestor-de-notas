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
@Table(name = "subjectsPerCareer")
public class SubjectsPerCareer implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	@Column(name = "id_subjects_per_career")
	private Long id;
	@Column(name = "create_at")
	private Date createAt;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_career")
	private Career career;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cycle")
	private Cycle cycle;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_subject")
	private Subject subject;
	

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

}
