package net.queencoder.springboot.service;

import java.util.*;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.*;

import net.queencoder.springboot.repository.RoleRepository;
import net.queencoder.springboot.repository.UserRepository;
import net.queencoder.springboot.model.Role;
import net.queencoder.springboot.model.User;
import lombok.extern.slf4j.Slf4j;

@Service 
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceimpl implements  UserService, UserDetailsService{
	
	private final UserRepository userRepo;
	private final RoleRepository roleRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		}else {
			log.info("User found in the database: {}", username);
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
	}

	@Override
	public User saveUser(User user) {
		log.info("Saving user {} to the database", user.getName());
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("Saving role {} to the database", role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		log.info("Adding role {} to user {}", roleName, username );
		User user = userRepo.findByUsername(username);
		Role role = roleRepo.findByName(roleName);
		
		user.getRoles().add(role);
		
		/**One this method is done executing, because the class has @transactional, 
		 * its gonna go ahead and save everything into the database. So I don't have to call the save method.
		 * 
		 * 1. Transaction Management: It defines the boundaries of a transaction. A transaction represents a sequence of 
		 * operations that need to be executed as an atomic unit of work. The annotation ensures that if any part of the 
		 * transaction fails, all changes made within that transaction are rolled back, preserving data consistency. 
		 * 2. Concurrency Control: Transactions also provide mechanisms for concurrent access to shared resources, such as 
		 * a database. With the @Transactional annotation, you can define the isolation level for your transactions, 
		 * which determines how changes made by one transaction are visible to other transactions.
		 * 3. Automatic Commit/Rollback: By default, a method annotated with @Transactional will automatically commit the 
		 * transaction when the method completes successfully. However, if an exception occurs during the method execution, the transaction will be rolled back, undoing any changes made within that transaction.
		 * 4. Nested Transactions: The @Transactional annotation supports nesting of transactions. This means that a method 
		 * annotated with @Transactional can invoke other methods annotated with @Transactional, creating a hierarchical 
		 * structure of transactions. The outermost transaction controls the entire set of nested transactions and 
		 * determines the final outcome (commit or rollback). Configuration Flexibility: The @Transactional annotation
		 * allows you to configure various attributes, such as propagation behavior, isolation level, read-only mode, 
		 * rollback rules, and more. This flexibility enables you to customize the transactional behavior according to 
		 * your specific requirements.
		 * */
	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {}", username );
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUsers() {
		log.info("Fetching all users");
		return userRepo.findAll();
	}

}

