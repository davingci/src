package org.davingci.ht.dao;

import org.davingci.ht.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role,Integer> {

    public void deleteById(Integer id);

    public Role getById(Integer id);


    public Role getByRolename(String username);
}
