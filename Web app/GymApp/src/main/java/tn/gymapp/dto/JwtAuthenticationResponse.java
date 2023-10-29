package tn.gymapp.dto;

import lombok.Data;
import tn.gymapp.Entities.User;

@Data
public class JwtAuthenticationResponse {
	
	private User user;
	private String token;
	private String refreshtoken;

}
