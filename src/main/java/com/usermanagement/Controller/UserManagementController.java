package com.usermanagement.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.Lib.SessionHelper;
import com.usermanagement.Model.User;
import com.usermanagement.Provider.UserProvider;

@Controller
public class UserManagementController {

	@Autowired
	UserProvider _provider = new UserProvider();

	@RequestMapping(value = "/UserManagement/Index", method = RequestMethod.GET)
	public String Index() {
		return "UserManagement/Index";
	}

	@RequestMapping(value = "/UserManagement/CreateUser", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public String CreateUser() {
		return "UserManagement/CreateUser";
	}

	@RequestMapping(value = "/UserManagement/CreateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> CreateUser(@RequestBody Map<String, Object> newUser) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			User user = mapper.convertValue(newUser.get("newUser"), User.class);
			HashMap<String, Object> obj = new HashMap<>();
			System.out.println("Data = " + _provider.getUserByEmail(user.getEmail()));
			if (_provider.getUserByEmail(user.getEmail()) != null) {
				obj.put("success", false);
				obj.put("content", "Email tồn tại");
			} else {
				obj.put("success", _provider.CreateUser(user));
			}
			return obj;
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> Error = new HashMap<>();
			Error.put("success", false);
			Error.put("content", "Some thing happen please return a moment");
			return Error;
		}

	}

	@RequestMapping(value = "/UserManagement/GetUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> GetUser() {
		HashMap<String, Object> obj = new HashMap<>();
		obj.put("success", true);
		obj.put("content", _provider.getAllUser());
		return obj;
	}

	@RequestMapping(value = "/UserManagement/EditUser", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public String EditUser(@RequestParam("ID") int ID, Model model) {
		model.addAttribute("UserID", ID);
		return "UserManagement/EditUser";
	}

	@GetMapping(value = "UserManagement/GetUserById", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> GetUserById(@RequestParam("ID") int ID) {
		User user = _provider.getUserById(ID);
		user.setPassword(null);
		HashMap<String, Object> obj = new HashMap<>();
		obj.put("success", true);
		obj.put("content", user);
		return obj;
	}

	@PostMapping(value = "/UserManagement/EditUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> EditUser(@RequestBody Map<String, Object> editUser) {
		ObjectMapper mapper = new ObjectMapper();
		User user = mapper.convertValue(editUser.get("editUser"), User.class);
		int UserId = mapper.convertValue(editUser.get("ID"), Integer.class);
		HashMap<String, Object> obj = new HashMap<>();
		obj.put("success", _provider.updateUser(UserId, user));
		return obj;
	}
	
	@GetMapping(value = "/UserManagement/CurrentUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> CurrentUser(){
		HashMap<String, Object> obj = new HashMap<>();
		obj.put("success", true);
		obj.put("content", new SessionHelper().getCurrentUser());
		return obj;
	}
	
	

}
