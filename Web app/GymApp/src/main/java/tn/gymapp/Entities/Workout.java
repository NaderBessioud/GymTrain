package tn.gymapp.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@Entity
public class Workout implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idw", nullable = false)
    private Long idw;
	@Column(name="title")
	private String title;
	@Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
	@Column(name="type")
	private TypeWorkout type;
	@Temporal(TemporalType.DATE)
  
	
	
	@ManyToOne
	private User userw;
	@ManyToMany(mappedBy = "workout")
	@ToString.Exclude // Exclude from toString
    @EqualsAndHashCode.Exclude // Exclude from equals and hashCode
  
	private Set<Exercice> exercices;
	public Workout(String title, Date date, TypeWorkout type) {
		super();
		this.title = title;
		this.date = date;
		this.type = type;
	}
	
	
	
	

	
	
	
	
	

}
