package org.davingci.ht.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.davingci.ht.domain.User;
import org.davingci.ht.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FollowController {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@RequestMapping(value = "/following/{id}",  method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> followingAction(@PathVariable("id") Integer id, HttpServletRequest request, HttpServletResponse response, Model model){
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		Integer loginUserId = (Integer) session.getAttribute("loginUserId");
		User user =  userServiceImpl.getById(id);
		User loginUser = userServiceImpl.getById(loginUserId);
		List<User> userFollowingList = loginUser.getFollowing();
		userFollowingList.add(user);
		user.setFollowing(userFollowingList);
		userServiceImpl.save(loginUser);
		map.put("success", true);
		map.put("message", loginUser.getUsername() + " is following " + user.getUsername());
		return map;
	}

}
