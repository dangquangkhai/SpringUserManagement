package com.usermanagement.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.Auth.AuthenticationManagerService;
import com.usermanagement.Lib.SessionHelper;
import com.usermanagement.Model.User;
import com.usermanagement.Provider.UserProvider;

@Controller
public class AccountController {
	@Autowired
	private AuthenticationManagerService authenticationManager;

	@Autowired
	UserProvider _provider = new UserProvider();
	
	@Autowired
	SessionHelper helper = new SessionHelper();
	
	@GetMapping(value = "/Account/Index", produces = MediaType.ALL_VALUE)
	public String Index() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			return "/";
		}

		return "Account/Index";
	}

	@PostMapping(value = "/Account/Login", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Map<String, Object> Login(HttpServletRequest req, @RequestBody Map<String, Object> LoginForm) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String email = mapper.convertValue(LoginForm.get("Email"), String.class);
			String password = mapper.convertValue(LoginForm.get("Password"), String.class);
			System.out.println("email = " + LoginForm);
			User user = _provider.loginByEmailAndPass(email, password);
			HashMap<String, Object> obj = new HashMap<>();
			if (user != null) {
				UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
				token.setDetails(user);
				Authentication authentication = authenticationManager.authenticate(token);
				SecurityContext sc = SecurityContextHolder.getContext();
				sc.setAuthentication(authentication);
				HttpSession session = req.getSession(true);
				session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
				obj.put("success", true);
				return obj;
			}
			obj.put("success", false);
			obj.put("content", "Email hoặc mật khẩu không đúng");
			return obj;
		} catch (Exception e) {
			// TODO: handle exception
			HashMap<String, Object> obj = new HashMap<>();
			obj.put("success", false);
			obj.put("content", "Some thing wrong happen");

			return obj;
		}

	}

	@GetMapping(value = "/Account/Logout")
	public String Logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/Account/Index?logout";// You can redirect wherever you want, but generally it's a good
												// practice to show login screen again.
	}

}
