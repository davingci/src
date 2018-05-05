package org.davingci.ht.service;

import java.util.List;

import org.davingci.ht.dao.PostDao;
import org.davingci.ht.domain.Post;
import org.davingci.ht.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PostServiceImpl {
	@Autowired
	private PostDao postDao;
	
	   public void save(Post post) {
	        postDao.save(post);
	    }

	    public List<Post> list() {
	        return postDao.findAll();
	    }

	    public void delete(Post post) {
	        postDao.delete(post);
	    }

	    public Post getById(Long id) {
	        return postDao.getById(id);
	    }

	    public Post getByTitle(String title) {
	        return postDao.getByTitle(title);
	    }

}
