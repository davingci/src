package org.davingci.ht.dao;

import org.davingci.ht.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionDao extends JpaRepository<Permission,Integer> {

    public void deleteById(Integer id);

    public Permission getById(Integer id);
}
