package org.davingci.ht.service;

import java.util.List;

import org.davingci.ht.dao.PostDao;
import org.davingci.ht.dao.ThumbUpDao;
import org.davingci.ht.domain.Post;
import org.davingci.ht.domain.ThumbUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ThumbUpServiceImpl {
	@Autowired
	private ThumbUpDao thumbUpDao;
	
	@Autowired
	private PostDao postDao;
	
	public boolean isThumbUped(Long pid, Long uid) {
		List<ThumbUp> list = thumbUpDao.findByPidAndUid(pid, uid);
		if(list != null && list.size()>0) {
			return true;
		}else return false;
	}
	public ThumbUp AddThumbUp(Long pid, Long uid) {
		ThumbUp thumbUp = new ThumbUp();
		thumbUp.setPid(pid);
		thumbUp.setUid(uid);
		Post post = postDao.getById(pid);
		post.setThumbUpNum(post.getThumbUpNum()+1);
		thumbUpDao.save(thumbUp);
		return thumbUp;
	}
	public void delThumbUp(Long pid, Long uid) {
		List<ThumbUp> list =  thumbUpDao.findByPidAndUid(pid, uid);
		Post post = postDao.getById(pid);
		post.setThumbUpNum(post.getThumbUpNum()-1);
		ThumbUp thumbUp = list.get(0);
		thumbUpDao.delete(thumbUp);	
	}

}
