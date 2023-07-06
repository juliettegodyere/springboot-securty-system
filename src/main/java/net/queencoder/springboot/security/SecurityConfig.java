package net.queencoder.springboot.security;

import lombok.AllArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
	        
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }   

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager(new AuthenticationConfiguration()));
    	customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable()
                .authorizeHttpRequests((authorize) -> {
                	authorize.requestMatchers("/api/login/**", "/api/token/refesh").permitAll();
                	authorize.requestMatchers("GET", "/api/users/**").hasAnyAuthority("ROLE_USER");
                	authorize.requestMatchers("POST", "/api/users/save/**").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_ADMIN");
                	authorize.anyRequest().authenticated();
                });
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
       
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(
        		new CustomAuthorizationFilter(),
        		UsernamePasswordAuthenticationFilter.class
        		);
        return http.build();
    }
    
  
}

