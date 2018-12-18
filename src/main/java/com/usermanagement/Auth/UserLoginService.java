package com.usermanagement.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.usermanagement.Lib.CryptoHash;
import com.usermanagement.Model.User;
import com.usermanagement.Provider.UserProvider;

public class UserLoginService implements UserDetailsService{

	@Autowired
	UserProvider _provider = new UserProvider(); 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UserDetails loadUserByUsername(String email, String password) throws UsernameNotFoundException {
		
/*		// TODO Auto-generated method stub
		User user = _provider.loginByEmailAndPass(email, password);
		System.out.println("Email = "+user.getEmail());
	    UserBuilder builder = null;
		if (user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(user.getEmail());
			builder.password(user.getPassword());
			
			return builder.build();
		}*/
	    UserBuilder builder = null;
		builder = org.springframework.security.core.userdetails.User.withUsername(email);
		builder.password(CryptoHash.Encrypt(password));
		builder.roles("ADMIN");
		
		return builder.build();
	}

}
