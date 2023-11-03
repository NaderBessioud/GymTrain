package tn.gymapp.Entities;

import java.io.Serializable;
import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Weighthistory implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idwh", nullable = false)
    private Long idwh;
	@Column(name="weight")
	private float weight;
	@Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
	
	@ManyToOne
	private User userhw;
	
	public Weighthistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Weighthistory(float weight, Date date, User userhw) {
		super();
		this.weight = weight;
		this.date = date;
		this.userhw = userhw;
	}


	public Long getIdwh() {
		return idwh;
	}
	public void setIdwh(Long idwh) {
		this.idwh = idwh;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUserhw() {
		return userhw;
	}
	public void setUserhw(User userhw) {
		this.userhw = userhw;
	}
	
	

}
