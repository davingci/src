package org.davingci.ht.dao;

import org.davingci.ht.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long> {

	Comment getById(Long id);


}
