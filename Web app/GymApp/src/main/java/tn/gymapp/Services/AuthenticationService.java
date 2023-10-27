package tn.gymapp.Services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tn.gymapp.Entities.Role;
import tn.gymapp.Entities.User;
import tn.gymapp.Repositories.UserRep;
import tn.gymapp.dto.JwtAuthenticationResponse;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
	@Autowired
	private final UserRep rep;
	
	@Autowired
	private final PasswordEncoder encoder;
	
	@Autowired
	private final AuthenticationManager authenticationManager;
	@Autowired
	private final JWTService jwtService;
	
	public User singup(User user) {
		user.setEnabled(true);
		user.setRole(Role.User);
		user.setPassword(encoder.encode(user.getPassword()));
		return rep.save(user);
	}
	
	public JwtAuthenticationResponse signin(String email, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		var user = rep.findByEmail(email).orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
		var jwt =jwtService.generateToken(user);
		var refreshtoken = jwtService.generateRefreshToken(new HashMap<>(), user);
		JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse();
		authenticationResponse.setToken(jwt);
		authenticationResponse.setRefreshtoken(refreshtoken);
		return authenticationResponse;
		
	}
	
	public JwtAuthenticationResponse refreshToken(String token) {
		String useremain = jwtService.extractUsername(token);
		User user = rep.findByEmail(useremain).orElseThrow();
		if(jwtService.TokenIsValid(token, user)) {
			var jwt = jwtService.generateToken(user);
			JwtAuthenticationResponse authenticationResponse = new JwtAuthenticationResponse();
			authenticationResponse.setToken(jwt);
			authenticationResponse.setRefreshtoken(token);
			return authenticationResponse;
		}
		return null;
	}
	
}
