package tn.gymapp.Entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


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
	@Column(name="iduser")
	private Long iduser;
	
	
	@ManyToOne
	private Muscle muscle;
	
	
	public Exercice() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIde() {
		return ide;
	}
	public void setIde(Long ide) {
		this.ide = ide;
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
	public int getNbsets() {
		return nbsets;
	}
	public void setNbsets(int nbsets) {
		this.nbsets = nbsets;
	}
	public int getNberep() {
		return nberep;
	}
	public void setNberep(int nberep) {
		this.nberep = nberep;
	}
	public float getResistance() {
		return resistance;
	}
	public void setResistance(float load) {
		this.resistance = load;
	}
	public Muscle getMuscle() {
		return muscle;
	}
	public void setMuscle(Muscle muscle) {
		this.muscle = muscle;
	}
	public Long getIduser() {
		return iduser;
	}
	public void setIduser(Long iduser) {
		this.iduser = iduser;
	}
	
	

	
	
	

}
