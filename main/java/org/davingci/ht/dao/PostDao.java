package org.davingci.ht.dao;

import org.davingci.ht.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, Long> {
	public Post getById(Long id);

	public Post getByTitle(String title);
}
