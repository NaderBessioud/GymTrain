package tn.gymapp.Config;



import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import tn.gymapp.Services.JWTService;
import tn.gymapp.Services.UserService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private final JWTService jwtService;
	
	@Autowired
	private final UserService userService;
	
	private RequestAttributeSecurityContextRepository repository = new RequestAttributeSecurityContextRepository();
	
	
	
	
	
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
		
			UserDetails userDetails = userService.userDetailService().loadUserByUsername(userEmail);
			
			if(jwtService.TokenIsValid(jwt, userDetails)) {
				SecurityContext contextHolder =SecurityContextHolder.createEmptyContext();
				
				UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(jwtService.extractUsername(jwt),null,userDetails.getAuthorities());
				
				UsernamePasswordAuthenticationToken token =new UsernamePasswordAuthenticationToken(userDetails, contextHolder);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				contextHolder.setAuthentication(authentication);
				SecurityContextHolder.setContext(contextHolder);
				repository.saveContext(contextHolder, request, response);


				
				
			}
			
		}
		
		filterChain.doFilter(request, response);
	}


}
