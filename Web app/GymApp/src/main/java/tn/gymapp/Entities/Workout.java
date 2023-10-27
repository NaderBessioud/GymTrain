package tn.gymapp.Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

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
    @Column(name = "skipdate")
    private Date skipdate;
	
	
	@ManyToOne
	private User userw;
	@ManyToMany(mappedBy = "workoutsm")
	private Set<Muscle> muscles;
	
	
	
	
	public Workout() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdw() {
		return idw;
	}
	public void setIdw(Long idw) {
		this.idw = idw;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public TypeWorkout getType() {
		return type;
	}
	public void setType(TypeWorkout type) {
		this.type = type;
	}
	public Date getSkipdate() {
		return skipdate;
	}
	public void setSkipdate(Date skipdate) {
		this.skipdate = skipdate;
	}
	public User getUserw() {
		return userw;
	}
	public void setUserw(User userw) {
		this.userw = userw;
	}
	public Set<Muscle> getMuscles() {
		return muscles;
	}
	public void setMuscles(Set<Muscle> muscles) {
		this.muscles = muscles;
	}
	
	
	
	
	

}
