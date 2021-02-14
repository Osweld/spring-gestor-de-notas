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
	@NotNull(message = "Este campo no puede quedar vacio")
	private Short percent;
	@DecimalMax(value = "10",message = "No puede ser mayor a 10")
	@DecimalMin(value = "0",message = "No puede ser menor a 0")
	private Double score;
	@NotNull(message = "No puede estar vacio")
	private Boolean finish;
	@Column(name = "create_at")
	private Date createAt;
	@ManyToOne
	@JoinColumn(name = "subjectspersemester_id_subjects_per_semester")
	private SubjectsPerSemester subjectsPerSemester;
	@ManyToOne
	@JoinColumn(name = "activities_id_activity")
	private Activity activity;
	@ManyToOne
	@JoinColumn(name = "activitynumber_id_activity_number")
	private ActivityNumber activityNumber;
	
	@PrePersist
	public void prePersist(){
		createAt = new Date();
	}

	public Assignment() {
	
	}
	
	

	public Assignment(Long id) {
		this.id = id;
	}

	public Assignment(Long id, @NotNull Short percent, @DecimalMax("10") @DecimalMin("0") Double score, Date createAt,
			Boolean finish, SubjectsPerSemester subjectsPerSemester, Activity activity, ActivityNumber activityNumber) {
		this.id = id;
		this.percent = percent;
		this.score = score;
		this.createAt = createAt;
		this.subjectsPerSemester = subjectsPerSemester;
		this.activity = activity;
		this.activityNumber = activityNumber;
		this.finish = finish;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Short getPercent() {
		return percent;
	}

	public void setPercent(Short percent) {
		this.percent = percent;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public SubjectsPerSemester getSubjectsPerSemester() {
		return subjectsPerSemester;
	}

	public void setSubjectsPerSemester(SubjectsPerSemester subjectsPerSemester) {
		this.subjectsPerSemester = subjectsPerSemester;
	}

	public Boolean getFinish() {
		return finish;
	}

	public void setFinish(Boolean finish) {
		this.finish = finish;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public ActivityNumber getActivityNumber() {
		return activityNumber;
	}

	public void setActivityNumber(ActivityNumber activityNumber) {
		this.activityNumber = activityNumber;
	}


	@Override
	public String toString() {
		return "Assignment{" +
				"id=" + id +
				", percent=" + percent +
				", score=" + score +
				", finish=" + finish +
				", createAt=" + createAt +
				'}';
	}
}
