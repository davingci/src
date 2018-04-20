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

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    private PermissionServiceImpl permissionServiceImpl;

    @RequestMapping("/list")
    public String list(ModelMap model) {
        List<Role> roles = roleServiceImpl.list();
        model.addAttribute("roles", roles);

        return "/role/list";
    }

    @RequestMapping(value= "/add", method = RequestMethod.GET)
    public String add(ModelMap model) {
        List<Permission> permissions = permissionServiceImpl.list();
        model.addAttribute("permissions",permissions);
        return "role/add";
    }

    @RequestMapping(value= "/add", method = RequestMethod.POST)
    public String addAction(@RequestParam("rolename") String rolename, @RequestParam("permissionIds") String[] permissionIds) {

        Role role = new Role(rolename);

        for(String permissionId : permissionIds) {
            Integer pId = Integer.valueOf(permissionId);
            Permission permission = permissionServiceImpl.getById(pId);
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
