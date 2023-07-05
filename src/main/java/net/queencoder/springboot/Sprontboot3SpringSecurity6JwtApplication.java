package net.queencoder.springboot;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import net.queencoder.springboot.model.Role;
import net.queencoder.springboot.model.User;

import net.queencoder.springboot.service.UserService;

@SpringBootApplication
public class Sprontboot3SpringSecurity6JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sprontboot3SpringSecurity6JwtApplication.class, args);
		
	}
	@Bean 
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new User(null, "Juliette Nkwor", "juliettejuli@yahoo.com", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Jane Maker", "janemaker@yahoo.com", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Ogechi Okafor", "ogechiokafor@yahoo.com", "1234", new ArrayList<>()));
			userService.saveUser(new User(null, "Onyinyeamara Onwudinjo ", "onyinyeamara@yahoo.com", "1234", new ArrayList<>()));
			
			userService.addRoleToUser("ogechiokafor@yahoo.com", "ROLE_MANAGER");
			userService.addRoleToUser("onyinyeamara@yahoo.com", "ROLE_ADMIN");
			
			userService.addRoleToUser("juliettejuli@yahoo.com", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("juliettejuli@yahoo.com", "ROLE_ADMIN");
			userService.addRoleToUser("juliettejuli@yahoo.com", "ROLE_USER");
			
			userService.addRoleToUser("janemaker@yahoo.com", "ROLE_USER");
			userService.addRoleToUser("janemaker@yahoo.com", "ROLE_ADMIN");

		};
	}

}
