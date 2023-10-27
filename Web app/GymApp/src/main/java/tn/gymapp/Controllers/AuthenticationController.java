package tn.gymapp.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tn.gymapp.Entities.User;
import tn.gymapp.Services.AuthenticationService;
import tn.gymapp.dto.JwtAuthenticationResponse;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	@Autowired
	private final AuthenticationService authenticationService;
	
	@PostMapping("/signup")
	public User signup(@RequestBody User user){
		return authenticationService.singup(user);
	}
	
	@PostMapping("/signin")
	public JwtAuthenticationResponse signin(@RequestParam("email") String email, @RequestParam("password") String password) {
		return authenticationService.signin(email, password);
	}

	@PostMapping("/refresh")
	public JwtAuthenticationResponse refreshtoken(@RequestParam("token") String token) {
		return authenticationService.refreshToken(token);
	}
}
