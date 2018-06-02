package org.davingci.ht.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.davingci.ht.domain.Post;
import org.davingci.ht.domain.User;
import org.davingci.ht.service.PostServiceImpl;
import org.davingci.ht.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class PostController {
	
    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @Autowired
    private PostServiceImpl postServiceImpl;
           
    @RequestMapping("/")
    public String list(ModelMap model) {
    	List<Post> postList = postServiceImpl.list();
    	model.addAttribute("postList", postList);
    	return "blog/list";
    }
    
    
    @RequestMapping(value = "/add",  method = RequestMethod.GET)
    public String add(ModelMap model, @RequestParam("id") String id) {
    	Long pId = Long.valueOf(id);
        User user = userServiceImpl.getById(pId);
        model.addAttribute("user",user);
        return "blog/add";
    }
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addAction(@RequestParam("id") String id, @RequestParam("title") String title, @RequestParam("content") String content) {
    
        
        return "redirect:/blog";
    }
}
