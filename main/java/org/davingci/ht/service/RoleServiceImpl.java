package org.davingci.ht.service;

import org.davingci.ht.dao.RoleDao;
import org.davingci.ht.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class RoleServiceImpl {

    @Autowired
    private RoleDao roleDao;

    public void save(Role role) {
        roleDao.save(role);
    }

    public List<Role> list() {
        return roleDao.findAll();
    }

    public void delete(Role role) {
        roleDao.delete(role);
    }

    public Role getById(Integer id) {
        return roleDao.getById(id);
    }

    public void deleteById(Integer id) {
        roleDao.deleteById(id);
    }

    public Role getByRolename(String username) {
        return roleDao.getByRolename(username);
    }
}
