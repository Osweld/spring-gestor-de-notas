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
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.NotNull;

@Entity
@Table(name="persons")
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_person")
	private Long id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String lastname;
	@NotNull
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthdate;
	@NotEmpty
	@Email
	@Column(unique = true)
	private String email;
	@NotEmpty
	private String gender;
	@Column(name = "create_at")
	private Date createAt;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_career")
	private Career career;

	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	public Person() {
	
	}
	
	

	public Person(Long id) {
		this.id = id;
	}

	public Person(Long id, @NotEmpty String name, @NotEmpty String lastname, Date birthdate,
			@NotEmpty @Email String email, @NotEmpty String gender, Date createAt, Career career) {
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.birthdate = birthdate;
		this.email = email;
		this.gender = gender;
		this.createAt = createAt;
		this.career = career;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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
	
	
	
}
