package com.kiran.SpringbootProject;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserSevice {
	@Autowired
	UserRepository userRepository;

	public boolean saveUser(User newUser) {
		if (newUser.getPassword().equals(newUser.getConfPassword())) {
			Optional<User> userEmail =userRepository.findByEmail(newUser.getEmail());
			if(userEmail.isPresent()) {
				return false;
			}
			else {
				return userRepository.save(newUser) != null;
		}
		}else
			return false;
	}

	public List<User> getAllUsers() {
		return	userRepository.findAll();
	}

	public boolean loginValidate(User user) {
		Optional<User> emailAndPass = userRepository.findByPasswordAndEmail(user.getPassword(),user.getEmail());
		
		if (emailAndPass.isPresent()) 
			return true;
		else
			return false;
	}

	public User updateUserById(Integer id) {
	 User user = userRepository.findById(id).get();
	 return user;
	}

    public boolean saveUpUser(User newUser) {
		Optional<User> userEmail =userRepository.findByEmail(newUser.getEmail());
			if(userEmail.isPresent()) {
				return false;				
			}
			else {
				return userRepository.save(newUser) != null;
		}
	}

	public void deleteUserById(Integer id) {
		userRepository.deleteById(id);
	}

	
	public User searchUser(User user) {
		return userRepository.findByEmailOrId(user.getEmail(), user.getId());
	}

	public boolean resetPassword(User_Password password) {
		if (password.getPassword().equals(password.getConfPassword())) {
			User user = userRepository.findByEmail(password.getEmail()).get();
			user.setPassword(password.getPassword());
			userRepository.save(user);
			return true;
		}
		else
			return false;
	}
	
}
