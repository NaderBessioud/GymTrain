package tn.gymapp.Services;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import tn.gymapp.Entities.User;
import tn.gymapp.Entities.Weighthistory;
import tn.gymapp.Repositories.UserRep;
import tn.gymapp.Repositories.WeighthisRep;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
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

	
	public User UpdateUser(User u) {
		u.setPassword(encoder.encode(u.getPassword()));
		return userrep.save(u);
	}

	
	public void DeleteUser(Long id) {
		User u= userrep.findById(id).orElse(null);
		userrep.delete(u);		
	}

	
	public List<User> Displayusers() {
		
		return (List<User>) userrep.findAll();
	}
	
	
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userrep.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
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

}
