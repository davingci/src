package org.davingci.ht.dao;

import java.util.List;

import org.davingci.ht.domain.ThumbUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbUpDao extends JpaRepository<ThumbUp, Long> {

	List<ThumbUp> findByPidAndUid(Long pid, Long uid);
	
}
