package tn.gymapp.Entities;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
@Data
@RequiredArgsConstructor
@Entity
public class Muscle implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idm", nullable = false)
    private Long idm;
	@Column(name="label")
	private String label;
	@Column(name="description")
	private String description;
	
	@ManyToOne
	private Bodypart bodypart;
	
	@OneToMany(mappedBy = "muscle")
	@ToString.Exclude // Exclude from toString
    @EqualsAndHashCode.Exclude // Exclude from equals and hashCode
    @JsonIgnore
	private Set<Exercice> exercicesm;

}
