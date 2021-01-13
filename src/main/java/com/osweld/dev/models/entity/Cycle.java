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

import com.sun.istack.NotNull;

@Entity
@Table(name = "cycles")
public class Cycle implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cycle")
	private Long id;
	@NotNull
	private String cycle;
	@Column(name = "create_at")
	private Date createAt;

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}
}
