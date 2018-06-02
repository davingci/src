package org.davingci.ht.dao;

import org.davingci.ht.domain.Post;
import org.davingci.ht.domain.Role;
import org.davingci.ht.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Long> {

    public void deleteById(Long id);
    public User getById(Long id);


    public User findByUsername(String username);
   
}
