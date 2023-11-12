package tn.gymapp.Services;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tn.gymapp.Entities.Role;
import tn.gymapp.Entities.User;
import tn.gymapp.Entities.Weighthistory;
import tn.gymapp.Repositories.UserRep;
import tn.gymapp.Repositories.WeighthisRep;
import tn.gymapp.dto.Nutrition;

@Service
@RequiredArgsConstructor
public class UserService  {
	@Autowired
	private UserRep userrep;
	
	@Autowired
	private final PasswordEncoder encoder;
	
	@Autowired
	private WeighthisRep wrep;
	
	public User findByid(long id) {
		return userrep.findById(id).get();
	}


	public User AddUser(User u) {
		
		return userrep.save(u);
	}

	
	@Transactional
	public User UpdateUser(User u) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date currentDate=new Date();
		try {
			currentDate = dateFormat.parse(dateFormat.format(new Date()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
       
		Weighthistory weighthistory=new Weighthistory();
		
			
	
		if(wrep.findByUserhwOrderByDateDesc(u).size() >0) {
			 
			if(u.getWeight()!=0) {
			if(wrep.findByUserhwOrderByDateDesc(u).get(0).getDate().compareTo(currentDate)!= 0) {
			
				weighthistory.setUserhw(u);
				weighthistory.setWeight(u.getWeight());
				weighthistory.setDate(new Date());
				weighthistory=wrep.save(weighthistory);
				
			}
			else {
				if( wrep.findByUserhwOrderByDateDesc(u).get(0).getWeight() != u.getWeight()) {
					weighthistory.setUserhw(u);
					weighthistory.setWeight(u.getWeight());
					weighthistory.setDate(new Date());
					weighthistory=wrep.save(weighthistory);
				}
			}
			}
			
		}
		else {
			
			weighthistory.setUserhw(u);
			weighthistory.setWeight(u.getWeight());
			weighthistory.setDate(new Date());
			weighthistory=wrep.save(weighthistory);
			
		
		}
		 if (u.getWeighthistories() == null) {
		        u.setWeighthistories(new HashSet<>());
		    }
		
		 u.getWeighthistories().add(weighthistory);
		
		u.setEnabled(true);
		u.setRole(Role.User);
		
		return userrep.save(u);
	}

	
	public void DeleteUser(Long id) {
		User u= userrep.findById(id).orElse(null);
		userrep.delete(u);		
	}

	
	public List<User> Displayusers() {
		
		return (List<User>) userrep.findAll();
	}
	
	public UserDetailsService userDetailService() {
		return new UserDetailsService() {
			
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				// TODO Auto-generated method stub
				 return   userrep.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
			}
		};
	}

	
	@Transactional
	public void UpdateWeight(float w, Long id) {
		User user=userrep.findById(id).get();
		user.setWeight(w);
		user.setPassword(encoder.encode(user.getPassword()));
		Weighthistory weighthistory=new Weighthistory();
		weighthistory.setUserhw(user);
		weighthistory.setDate(new Date());
		weighthistory.setWeight(w);
		wrep.save(weighthistory);
		
		userrep.save(user);
	}


	public boolean updatePassword(String pass,long id) {
		try {
			User user = userrep.findById(id).get();
			user.setPassword(encoder.encode(pass));
			
			userrep.save(user);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
		
	}


	public User getUserById(long id) {
		
		return userrep.findById(id).get();
	}
	
	public boolean checkcpass(String pass,long id) {
		User user=userrep.findById(id).get();
		
		return(user.getPassword().equals(encoder.encode(pass)));
	}
	
	public User updateWorkoutRoutine(User u) {
		User user= userrep.findById(u.getIdu()).get();
		user.setWorkoutroutine(u.getWorkoutroutine());
		return userrep.save(user);
		
	}
	
	
	public Nutrition getUserNutrition(long id,float pers,float nb) {
		Nutrition nutrition=new Nutrition();
		User user=userrep.findById(id).get();
		double preteinGram = user.getWeight()*2.2*nb;  
		double fatGram = user.getWeight()*2.2*0.5;   
		double callories = user.getWeight() * 2.2 *pers;
		double carbsGram = (callories - (preteinGram * 4 + fatGram * 9)) / 4;
		carbsGram = Double.parseDouble(String.format("%.2f", carbsGram).replace(",", "."));
		nutrition.setProtein(preteinGram);
		nutrition.setFats(fatGram);
		nutrition.setCarbs(carbsGram);
		nutrition.setCallories(callories);
		
		
		return nutrition;
	}
	
	

}
