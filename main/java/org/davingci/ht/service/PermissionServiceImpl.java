package org.davingci.ht.service;

import org.davingci.ht.dao.PermissionDao;
import org.davingci.ht.domain.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class PermissionServiceImpl {

    @Autowired
    private PermissionDao permissionDao;

    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    public List<Permission> list() {
        return permissionDao.findAll();
    }

    public void delete(Permission permission) {
        permissionDao.delete(permission);
    }

    public void deleteById(Integer id) {
        permissionDao.deleteById(id);
    }

    public Permission getById(Integer id) {
        return permissionDao.getById(id);
    }
}
