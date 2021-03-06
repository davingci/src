package org.davingci.ht.web;

import org.davingci.ht.domain.Permission;
import org.davingci.ht.domain.Role;
import org.davingci.ht.service.PermissionServiceImpl;
import org.davingci.ht.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private PermissionServiceImpl permissionServiceImpl;

    @RequestMapping("/list")
    public String list(ModelMap model) {
        List<Role> roles = roleServiceImpl.list();
        model.addAttribute("roles", roles);

        return "role/list";
    }

    @RequestMapping(value= "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        List<Permission> permissions = permissionServiceImpl.list();
        model.addAttribute("permissions",permissions);
        return "role/add-multiselect";
    }

    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public String addAction(@RequestParam("rolename") String rolename, @RequestParam("to[]") String[] permissionIds) {

        Role role = new Role(rolename);

        for(String permissionId : permissionIds) {
            Integer pId = Integer.valueOf(permissionId);
            Permission permission = permissionServiceImpl.getById(pId);
            role.getPermissions().add(permission);
        }

        roleServiceImpl.save(role);
        return "redirect:/role/list";
    }
    
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String edit(ModelMap model, @PathVariable String id) {
    	Integer pId = Integer.valueOf(id);
    	Role role = roleServiceImpl.getById(pId);
    	model.addAttribute("role", role);
    	List<Permission> permissionChecked = role.getPermissions();
    	List<Permission> permissionList = permissionServiceImpl.list();
    	List<Permission> permissionNotChecked = new ArrayList<Permission>();
    	for(Permission p:permissionList) {
    		if(!permissionChecked.contains(p))
    		 permissionNotChecked.add(p);
    	}
    	model.addAttribute("permissionChecked", permissionChecked);
    	model.addAttribute("permissionNotChecked", permissionNotChecked);
    	return "/role/edit-multiselect";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam("id") String id, @RequestParam("rolename") String rolename, @RequestParam("to[]") String[] permissionIds) {
    	Integer pId = Integer.valueOf(id);
    	Role role = roleServiceImpl.getById(pId);
    	role.setRolename(rolename);
        for(String permissionId : permissionIds) {
            Integer ppId = Integer.valueOf(permissionId);
            Permission permission = permissionServiceImpl.getById(ppId);
            role.getPermissions().add(permission);
        }

        roleServiceImpl.save(role);
        return "redirect:/role/list";
    }

    @RequestMapping(value= "/delete/{id}", method = RequestMethod.GET)
    public String deleteAction(@PathVariable String id) {
        Integer rId = Integer.valueOf(id);
        roleServiceImpl.deleteById(rId);
        return "redirect:/role/list";
    }
}
