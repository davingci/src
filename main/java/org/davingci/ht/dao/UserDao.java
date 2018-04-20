package org.davingci.ht.dao;

import org.davingci.ht.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer> {

    public void deleteById(Integer id);
}