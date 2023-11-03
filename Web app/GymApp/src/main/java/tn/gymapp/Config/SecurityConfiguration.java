package tn.gymapp.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;
import tn.gymapp.Entities.Role;
import tn.gymapp.Services.UserService;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration     {


	private final JwtAuthenticationFilter authenticationFilter;
	@Autowired
	private Passwordencode passwordencode;
	
	
	@Autowired
	private final UserService userService;




	
	public Customizer<CorsConfigurer<HttpSecurity>> getcorss(){
		return new Customizer<CorsConfigurer<HttpSecurity>>() {
			@Override
			public void customize(CorsConfigurer<HttpSecurity> t) {
				t.configurationSource(getCorsConfiguration());
			}
		};
	}
	
	public CorsConfigurationSource getCorsConfiguration() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOriginPatterns(Arrays.asList("http://localhost:4200"));
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return source;
	}

	@Bean
	public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{

			http.csrf().disable().cors(getcorss()).authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**")
			.permitAll()
			.requestMatchers("/api/admin").hasAnyAuthority(Role.Admin.name())
			.requestMatchers("/api/user").hasAnyAuthority(Role.User.name())
			
			.anyRequest().authenticated())
			
			.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authenticationProvider(authenticationProvider())
			.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
			}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userService.userDetailService());
		authenticationProvider.setPasswordEncoder(passwordencode.passwordEncoder());
		return authenticationProvider;
	}


	




	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}


	

}
