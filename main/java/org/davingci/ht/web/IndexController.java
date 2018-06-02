package org.davingci.ht.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.davingci.ht.domain.Post;
import org.davingci.ht.domain.User;
import org.davingci.ht.service.PostServiceImpl;
import org.davingci.ht.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.mysql.jdbc.log.Log;

import org.davingci.ht.web.WebSecurityConfig;
import org.hibernate.service.spi.ServiceException;

@Controller
public class IndexController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @Autowired
    private PostServiceImpl postServiceImpl;
	
    @RequestMapping("/admin")
    public String toAdmin() {
    	return "admin";
    }
    
    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response, Model model) {
    	HttpSession session = request.getSession();
    	if (session.getAttribute(WebSecurityConfig.SESSION_KEY) == null)
    		{model.addAttribute("loginUser", null);
    		}else {    	
    			String username = String.valueOf(session.getAttribute(WebSecurityConfig.SESSION_KEY));
    			//login user
    			User loginUser = userServiceImpl.findByUsername(username);
    			model.addAttribute("loginUser", loginUser);
    			}
    	//post list for index
    	List<Post> postList = postServiceImpl.list();
    	model.addAttribute("postList", postList);  
    	//user list from each post
    	List<User> userList = new ArrayList<>();
    		for(Post post : postList) {
    			userList.add(post.getUser()); }
    		model.addAttribute("userList", userList);
    	return "index";
    }
   
    
    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup() {
    	return "signup";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> signupAction(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session, Model model)
    {
    	Map<String, Object> map = new HashMap<>();
    	
    	if("".equals(password)) {
    		map.put("success", false);
        	map.put("message", "no password");
        	return map;
        }
    	
    	User retUsr = userServiceImpl.findByUsername(username);
    	if(retUsr != null)
    	{
    		map.put("success", false);
    		map.put("message", "username existed");
    		return map;
       	}
    	try {
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(password);
    	userServiceImpl.save(user);
    	model.addAttribute("user", user);
    	map.put("success", true);
    	map.put("message", "login successfully");
    	return map;
    	}catch(ServiceException e) {
    		e.printStackTrace();
    		map.put("success", false);
    		map.put("message", "signup error");
    		return map;
    	}
 
    	
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
    	return "login";
    } 
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String loginPost(HttpServletRequest request, Map<String, Object> map){
    	System.out.println("IndexController.login");
    	
    	String exception = (String) request.getAttribute("shiroLoginFailure");
    	String msg = "";
    	if(exception != null) {
    		if(UnknownAccountException.class.getName().equals(exception)) {
    			System.out.println("UnknowAccountExcetion: ");
    			msg = "UnknowAccountException: ";
    		} else if (IncorrectCredentialsException.class.getName().equals(exception)) {
    			System.out.println("IncorrectCredentialsException: ");
    			msg = "IncorrectCredentialsException: ";
    		} else if("kaptchaValidateFailed".equals(exception)) {
    			System.out.println("kaptahcValidateFailed");
    			msg = "kaptchaValidateFailed";
    		} else {
    			msg = "else >> " + exception;
    			System.out.println("else -->" + exception);
    		}
    	}
    	map.put("msg", msg);
    	return "/login";

    	
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
    	session.removeAttribute(WebSecurityConfig.SESSION_KEY);
    	return "redirect:/";
    }
}
