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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="persons")
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_person")
	private Long id;
	@NotEmpty(message = "Este campo no puede quedar vacio")
	private String name;
	@NotEmpty(message = "Este campo no puede quedar vacio")
	private String lastname;
	@NotNull(message = "Este campo no puede quedar vacio")
	@Temporal(value = TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past(message = "La fecha de nacimientyo es incorrecta")
	private Date birthdate;
	@NotEmpty(message = "Este campo no puede quedar vacio")
	@Email(message = "El email no es valido")
	@Column(unique = true)
	private String email;
	@NotEmpty(message = "Este campo no puede quedar vacio")
	private String gender;
	@Column(name = "create_at")
	private Date createAt;
	@OneToOne()
	@JoinColumn(name = "careers_id_career")
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

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", name='" + name + '\'' +
				", lastname='" + lastname + '\'' +
				", birthdate=" + birthdate +
				", email='" + email + '\'' +
				", gender='" + gender + '\'' +
				", createAt=" + createAt +
				'}';
	}
}
