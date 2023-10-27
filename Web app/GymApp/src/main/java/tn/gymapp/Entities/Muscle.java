package tn.gymapp.Entities;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Muscle implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idm", nullable = false)
    private Long idm;
	@Column(name = "label")
	private String label;
	@Column(name = "description")
	private String description;
	@Column(name = "bodypart")
	private BodyPart bodyPart;
	
	
	@ManyToMany
	private Set<Workout> workoutsm;
	@OneToMany(mappedBy = "muscle")
	private Set<Exercice> exercices; 
	
	
	public Muscle() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdm() {
		return idm;
	}
	public void setIdm(Long idm) {
		this.idm = idm;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<Exercice> getExercices() {
		return exercices;
	}
	public void setExercices(Set<Exercice> exercices) {
		this.exercices = exercices;
	}
	public BodyPart getBodyPart() {
		return bodyPart;
	}
	public void setBodyPart(BodyPart bodyPart) {
		this.bodyPart = bodyPart;
	}
	public Set<Workout> getWorkoutsm() {
		return workoutsm;
	}
	public void setWorkoutsm(Set<Workout> workoutsm) {
		this.workoutsm = workoutsm;
	}
	
	
	

}
