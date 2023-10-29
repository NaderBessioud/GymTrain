package tn.gymapp.Config;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import tn.gymapp.Services.JWTService;
import tn.gymapp.Services.UserService;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	
	private final JWTService jwtService;
	
	
	private final UserService userService;
	
	
	
	  public JwtAuthenticationFilter(UserService userService) {
	        this.userService = userService;
	        this.jwtService=new JWTService();
	        
	    }
	  public JwtAuthenticationFilter() {
		this.jwtService = new JWTService();
		this.userService = null;
			
		}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		
		if(StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader, "Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(7);
		userEmail = jwtService.extractUsername(jwt);
		
		if(StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userService.loadUserByUsername(userEmail);
			if(jwtService.TokenIsValid(jwt, userDetails)) {
				SecurityContext contextHolder =SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(userDetails, contextHolder);
				token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				contextHolder.setAuthentication(token);
				SecurityContextHolder.setContext(contextHolder);
				
				
			}
		}
		
		filterChain.doFilter(request, response);
	}
	@Bean
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
