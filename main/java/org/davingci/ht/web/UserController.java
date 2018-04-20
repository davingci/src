package org.davingci.ht.web;

import org.davingci.ht.domain.Role;
import org.davingci.ht.domain.User;
import org.davingci.ht.service.RoleServiceImpl;
import org.davingci.ht.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @RequestMapping("/list")
    public String list(ModelMap model) {
        List<User> users =  userServiceImpl.list();
        model.addAttribute("users",users);
        return "user/list";
    }

    @RequestMapping(value="/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        List<Role> roles = roleServiceImpl.list();
        model.addAttribute("roles",roles);
        return "user/add";
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addAction(@RequestParam("username") String username, @RequestParam("roleIds") String[] roleIds) {
        User user = new User(username);
        for(String roldId : roleIds) {
            Integer rId = Integer.valueOf(roldId);
            Role role = roleServiceImpl.getById(rId);
            user.getRoles().add(role);
        }
        userServiceImpl.save(user);
        return "redirect:/user/list";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        userServiceImpl.deleteById(id);
        return "redirect:/user/list";
    }
}
