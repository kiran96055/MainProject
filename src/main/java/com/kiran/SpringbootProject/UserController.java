
package com.kiran.SpringbootProject;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	@Autowired
	UserSevice userService;
	
	// welcome to Sathya Technologies...........
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	
	// sending an empty object to signup form to store new user data into that object.......
	@GetMapping("/register")
	public String registerView(Model model) {
		User newUser = new User();
		model.addAttribute("user",newUser);
		return "signup";
	}
	
	// showing userslist after successful registration ....
	@GetMapping("/userslist")
	public String showUsers(Model model) {
	 List<User> users =	userService.getAllUsers();
		model.addAttribute("users", users);
		return "userlist";
	}
	
	// saving the new user data into database 
	@PostMapping("/user_save")
	public String registerUser(User user, Model model) {
			return userService.saveUser(user)? "redirect:/userslist":"redirect:/register";
//		} else {
//			model.addAttribute("message","Email Existed");
//		return "redirect:/register";
//		}
	}
	
	// sending an empty object to login page to store login data
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("newUser", new User());
		return "login";
	}
	
	// validating the login details......................... 
	@RequestMapping("/login_process")
	public String loginValidation(User user, Model model) {
	
		return userService.loginValidate(user)?"success":"fail";
//		if(userService.loginValidate(user)){
//			model.addAttribute("flag", true);
//			return "redirect:/login";
//		}else {
//			model.addAttribute("flag", false);
//			return "redirect:/login";
//		}
	}
	
	// updating the user based on id........................
	@GetMapping("/update/{id}")
	public String showUpdate(@PathVariable(value = "id") Integer id, Model model) {
		User user = userService.updateUserById(id);
		model.addAttribute("user",user);
		return "update";
	}

	// to add the updated user to the database...........
	@PostMapping("/user_update")
	public String saveUpUser(User user, Model model) {
			return userService.saveUpUser(user) ? "redirect:/userslist":"redirect:/register";
	}


	
//	@PostMapping
//	public String updateUser(Model model) {
//		model.addAttribute("newUser", new User());
//		return "";
//	}
	
	// Deleting 
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable(value = "id") Integer id) {
		userService.deleteUserById(id);
		return "redirect:/userslist";
	}
	
	@GetMapping("/search")
	public String showSearch(Model model) {
		model.addAttribute("user", new User());
		return "search";
	}
	
	@PostMapping("/user_found")
	public String searchByIdOrEmail(User user, Model model) {
		  User u = userService.searchUser(user);
		  if(u != null) {
			  model.addAttribute("user",u);
			  return "search";
		  }
		  else {
			  return "searchfail";
		  }
	}
	
	@GetMapping("/reset")
	public String resetPass(Model model) {
		model.addAttribute("userPass", new User_Password());
		return "resetPass";
	}
	
	@PostMapping("/update_pass")
	public String updatePassword(Model model, User_Password password) {
		return userService.resetPassword(password)?"redirect:/userslist":"resetFail";
	}
	
	
}
