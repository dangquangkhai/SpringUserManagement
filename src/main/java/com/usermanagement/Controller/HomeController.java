package com.usermanagement.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.Model.User;
import com.usermanagement.Provider.UserProvider;

@Controller
public class HomeController {

	@Autowired
	UserProvider _provider = new UserProvider();

	@RequestMapping(value = { "/", "/Home/Index" }, method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public String Index() {
		return "Home/Index";
	}
	
	@RequestMapping(value  = "/Home/CreateUser", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public String CreateUser() {
		
		return "CreateUser";
	}
		
	
	@RequestMapping(value = "/Home/GetAllUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> GetAllUser() {
		HashMap<String, Object> obj = new HashMap<>();
		obj.put("success", true);
		obj.put("content", _provider.getAllUser());
		return obj;
	}
	
	
	@RequestMapping(value = "/Home/CreateUser", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Map<String, Object> CreateUser(@RequestBody HashMap<String, Object> newUser){
		HashMap<String, Object> obj = new HashMap<>();
/*		obj.put("success", _provider.CreateUser(newUser));*/
		ObjectMapper mapper = new ObjectMapper(); 
		User select = mapper.convertValue(newUser.get("newUser"), User.class);
		Object newObj = mapper.convertValue(newUser.get("pojo"), Object.class);
		System.out.println("Data = "+ newUser.get("newUser"));
		System.out.println("Value = "+ newUser);
		System.out.println("Obj = "+newObj);
		System.out.println("User = "+ select);
		obj.put("success", true);
		return obj;
	}
	

}
