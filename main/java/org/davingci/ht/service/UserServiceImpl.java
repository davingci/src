package org.davingci.ht.service;

import org.davingci.ht.dao.UserDao;
import org.davingci.ht.domain.Role;
import org.davingci.ht.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl {

    @Autowired
    private UserDao userDao;

    public void save(User user) {
        userDao.save(user);
    }

    public List<User> list() {
        return userDao.findAll();
    }
    public User getById(Integer id) {
        return userDao.getById(id);
    }
    public User getByUsername(String username) {
    	return userDao.getByUsername(username);
    }
    
    public void delete(User user) {
        userDao.delete(user);
    }

    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }


}
