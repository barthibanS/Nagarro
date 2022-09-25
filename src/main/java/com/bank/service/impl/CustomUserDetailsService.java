package com.bank.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.model.User;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		List<User> userList = getUserList();
		User user = userList.stream().filter(a -> a.getUsername().equals(s)).findFirst().orElse(null);
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
		}

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRoleName()));

		return new org.springframework.security.core.userdetails.User(user.getUsername(),
				user.getPassword(), authorities);
	}

	private List<User> getUserList() {
		
		List<User> userlist = new ArrayList<>();
		User user = new User();
		user.setUsername("admin");
		user.setPassword("$2a$10$Q.Yef6zPou4o0sp86z0h/uAHbP41nWSf3tygxB0Ae0QnWzwEjbdk6");
		user.setRoleName("ADMIN_USER");
		userlist.add(user);
		
		User user1 = new User();
		user1.setUsername("user");
		user1.setPassword("$2a$10$Lhc4zQ3zLOAszQXVMtGnh.yU.8T.oWa8lm5XZ5ezXN8gbP01J4n9i");
		user1.setRoleName("STANDARD_USER");
		userlist.add(user1);
		return userlist;
	}
}

