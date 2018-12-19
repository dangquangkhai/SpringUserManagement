package com.usermanagement.Lib;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.usermanagement.Model.User;
import com.usermanagement.Provider.UserRepository;

@Component
public class SessionHelper {

	@Autowired
	UserRepository userRepository;

	public User getCurrentUser() {
/*		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof LoginUserDetail) {
			User user = ((LoginUserDetail) principal).getLoginUser();
			System.out.println("Hello World");
			return user;
		} else {
			User user = userRepository.getUserByEmail(principal.toString());
			System.out.println(principal.toString());
			return user;
		}*/
		String email = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		if (email != null && !email.equals("")) {
			User user = userRepository.getUserByEmail(email);
			if (user != null) {
				return user;
			}
		}
		User user = new User();
		user.setEmail("guest@abc.com");
		user.setFullName("guest");
		user.setID(-1);
		return user;
		
	}

}
