package tn.gymapp.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
public class Exercice  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ide", nullable = false)
    private Long ide;
	@Column(name="label")
	private String label;
	@Column(name="description")
	private String description;
	@Column(name="nbsets")
	private int nbsets;
	@Column(name="nberep")
	private int nberep;
	@Column(name="resistance")
	private float resistance;
	@Column(name="muscle")
	private String muscle;
	@Column(name="bodypart")
	private String bodypart;
	
	
	@ManyToOne
	private Workout workout;
	


	
	
	

}
