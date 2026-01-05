package it.pittysoft.affetti.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import it.pittysoft.affetti.entity.Users;
import it.pittysoft.affetti.repository.UsersRepository;

@Service
public class DatabaseUserDetailsService  implements UserDetailsService {
	
	 @Autowired
	 private UsersRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username)
		throws UsernameNotFoundException {
		Optional<Users> user = userRepository.findByUsername(username);
		
		if(user.isPresent()) {
			return new DatabaseUserDetails(user.get());
		} else {
			throw new UsernameNotFoundException("Username not found");
		}
	}

}
