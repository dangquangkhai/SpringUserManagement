package com.usermanagement.Provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.usermanagement.Lib.CryptoHash;
import com.usermanagement.Model.User;

import groovy.util.logging.Log4j;

@Component
public class UserProvider {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUser() {
		List<User> users = userRepository.findAll();
		for (User user : users) {
			user.setImage(null);
		}

		return users;
	}

	public Boolean CreateUser(User newUser) {

		try {
			newUser.setPassword(CryptoHash.Encrypt(newUser.getPassword()));
			userRepository.save(newUser);
			return true;
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			return false;
		}

	}

	public User getUserById(int id) {
		return userRepository.getUserById(id);
	}

	public User getUserByEmail(String Email) {
		try {
			return userRepository.getUserByEmail(Email);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e.getMessage());
			return null;
		}
	}

	public Boolean updateUser(int ID, User editUser) {
		try {
			User select = getUserById(ID);

			select.setAddress(editUser.getAddress());
			select.setBirthDay(editUser.getBirthDay());
			select.setEmail(editUser.getEmail());
			select.setFullName(editUser.getFullName());
			select.setImage(editUser.getImage());
			select.setPhone(editUser.getPhone());
			select.setPosition(editUser.getPosition());
			if (editUser.getPassword() == null || editUser.getPassword() == "") {
				select.setPassword(select.getPassword());
			} else {
				select.setPassword(CryptoHash.Encrypt(editUser.getPassword()));
			}

			userRepository.save(select);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public String name() {
		return "Hello";
	}

	public User loginByEmailAndPass(String Email, String Pass) {
		User user = userRepository.getUserByEmail(Email);
		if (user != null) {
			if (CryptoHash.Decrypt(user.getPassword()).equals(Pass)) {
				return user;
			}
		}
		
		return null;

	}
}
