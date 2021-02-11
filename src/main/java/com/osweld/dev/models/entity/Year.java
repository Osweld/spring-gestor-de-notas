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
@Table(name = "years")
public class Year implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_year")
	private Long id;
	@NotNull(message = "Este campo no puede quedar vacio")
	private Integer year;
	@Column(name = "create_at")
	private Date createAt;

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	public Year() {
	}
	
	

	public Year(Long id) {
		this.id = id;
	}

	public Year(Long id, Integer year, Date createAt) {
		this.id = id;
		this.year = year;
		this.createAt = createAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Year{" +
				"id=" + id +
				", year=" + year +
				", createAt=" + createAt +
				'}';
	}
}
