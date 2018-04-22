package org.davingci.ht.web;

import org.davingci.ht.domain.Permission;
import org.davingci.ht.service.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionServiceImpl permissionServiceImpl;

    @RequestMapping("/list")
    public String list(ModelMap model) {
        List<Permission> permissions = permissionServiceImpl.list();
        model.addAttribute("permissions",permissions);
        return "permission/list";
    }

    @RequestMapping(value= "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        Permission permission = new Permission();
        model.addAttribute(permission);
        return "permission/add";
    }

    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public String addAction(Permission permission, BindingResult bindingResult, ModelMap model) {
        permissionServiceImpl.save(permission);
        return "redirect:/permission/list";
    }

    @RequestMapping(value= "/update/{id}", method = RequestMethod.GET)
    public String edit(ModelMap model, @PathVariable String id) {
    	Integer pId = Integer.valueOf(id);
    	Permission permission = permissionServiceImpl.getById(pId);
    	model.addAttribute("permission",permission);
    	return "permission/edit";
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update( HttpServletRequest request) {
    	Permission p = permissionServiceImpl.getById(Integer.valueOf(request.getParameter("id")));
    	p.setPermissionName(request.getParameter("permissionName"));
        permissionServiceImpl.save(p);
    	return "redirect:/permission/list";
    } 
    
    @RequestMapping(value= "/delete/{id}", method = RequestMethod.GET)
    public String deleteAction(@PathVariable String id) {
        Integer pId = Integer.valueOf(id);
        permissionServiceImpl.deleteById(pId);
        return "redirect:/permission/list";
    }

}
