package org.davingci.ht.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.davingci.ht.domain.Post;
import org.davingci.ht.domain.User;
import org.davingci.ht.service.PostServiceImpl;
import org.davingci.ht.service.ThumbUpServiceImpl;
import org.davingci.ht.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ThumbUpController {
	
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private ThumbUpServiceImpl thumbUpServiceImpl;
	
	@RequestMapping(value = "/p/thumbup/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> thumbUp(@PathVariable("id") Long id, HttpServletRequest request, HttpServletResponse response, Model model) {
		Map<String, Object> map = new HashMap<>();
		HttpSession session = request.getSession();
		Long loginUserId = (Long) session.getAttribute("loginUserId");		
	if(thumbUpServiceImpl.isThumbUped(id, loginUserId)) {
		map.put("success", false);
		map.put("message", "Already done thumb up to this post");
		return map;
	}
	
	thumbUpServiceImpl.AddThumbUp(id, loginUserId);
	map.put("success", true);
	map.put("message", "thumb up done");
	Post post = postServiceImpl.getById(id);
	model.addAttribute("ThumbUpNum",post.getThumbUpNum());
	return map;
	}
}
