package org.davingci.ht.service;

import java.util.List;

import org.davingci.ht.dao.CommentDao;
import org.davingci.ht.dao.PostDao;
import org.davingci.ht.domain.Comment;
import org.davingci.ht.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CommentServiceImpl {

	@Autowired
	private CommentDao commentDao;
	
	   public void save(Comment comment) {
		   commentDao.save(comment);
	    }

	    public List<Comment> list() {
	        return commentDao.findAll();
	    }

	    public void delete(Comment comment) {
	        commentDao.delete(comment);
	    }

	    public Comment getById(Long id) {
	        return commentDao.getById(id);
	    }

}
