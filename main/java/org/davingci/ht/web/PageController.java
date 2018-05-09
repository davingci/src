package org.davingci.ht.web;

import java.util.List;

import org.davingci.ht.domain.Comment;
import org.davingci.ht.domain.Post;
import org.davingci.ht.domain.User;
import org.davingci.ht.service.PostServiceImpl;
import org.davingci.ht.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @Autowired
    private PostServiceImpl postServiceImpl;
	
	@RequestMapping("/p/{id}")
	public String blogPageLoad(@PathVariable String id, Model model) {
		Post post = postServiceImpl.getById(Long.parseLong(id));
		User user = post.getUser();
		
		List<Comment> comments = post.getComments();
		model.addAttribute("post", post);
		model.addAttribute("user", user);
		model.addAttribute("comments", comments);
		return "blog/blog";
	}

	@RequestMapping("/u/{id}")
	public String userPageLoad(@PathVariable String id, Model model) {
		User user = userServiceImpl.getById(Integer.valueOf(id));
		List<Post> postList = user.getPosts();
		model.addAttribute("user", user);
		model.addAttribute("postList", postList);
		return "blog/userpage";
	}
}
