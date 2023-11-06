package tn.gymapp.Entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@Entity
public class User implements  UserDetails, Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idu", nullable = false)
    private Long idu;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    
    @Column(name = "image")
    private String image;
    @Column(name = "gender")
    private String gender;
  
    @Column(name = "weight")
    private float weight;
    @Column(name = "height")
    private float height;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Temporal(TemporalType.DATE)
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "aboutme")
    private String aboutMe;
    @Column(name = "workoutroutine")
    private String workoutroutine;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    @Column(name="enabled")
    private boolean enabled;
    
    @JsonIgnore
    @OneToMany(mappedBy = "userw")
    @ToString.Exclude // Exclude from toString
    @EqualsAndHashCode.Exclude // Exclude from equals and hashCode
    private Set<Workout> workouts;
    @JsonIgnore
    @OneToMany(mappedBy = "userhw")
    private Set<Weighthistory> weighthistories;
    
    
    
	public User() {
		super();
	}
	
	
	
	
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

      
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

    @JsonIgnore
	@Override
	public String getUsername() {
		return email;
	}
    @JsonIgnore
	@Override
	public boolean isAccountNonExpired() {

		return true;
	}
    @JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
    @JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
    @JsonIgnore
	@Override
	public boolean isEnabled() {
		return enabled;
	}







	
	
   
    
    
    

}
