package net.queencoder.springboot.model;

import java.util.*;

import jakarta.persistence.*;
import static jakarta.persistence.FetchType.*;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String username;
	
	private String password;
	
	@ManyToMany(fetch = EAGER)
	private Collection<Role> roles = new ArrayList<>();

}
