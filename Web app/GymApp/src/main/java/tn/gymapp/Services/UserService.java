package tn.gymapp.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tn.gymapp.Entities.User;
import tn.gymapp.Repositories.UserRep;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRep userrep;
	
	public User findByid(long id) {
		return userrep.findById(id).get();
	}


	public User AddUser(User u) {
		
		return userrep.save(u);
	}

	
	public User UpdateUser(User u) {
		
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
		return userrep.findByEmail(username).get();
	}

}
