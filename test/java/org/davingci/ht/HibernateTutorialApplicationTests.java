package org.davingci.ht;
import org.davingci.ht.domain.*;
import org.davingci.ht.service.*;
import org.davingci.ht.dao.ThumbUpDao;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.davingci.ht.dao.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class HibernateTutorialApplicationTests {

	@Autowired
    private UserServiceImpl userServiceImpl;
	
	@Autowired
	private CommentServiceImpl commentServiceImpl;
	
	@Autowired
	private PostServiceImpl postServiceImpl;
	
	@Autowired
	private ThumbUpServiceImpl thumbUpServiceImpl;
	
	@Autowired
	private ThumbUpDao thumbUpDao;
	
	@Test
	public void contextLoads() {
		
		User user1 = userServiceImpl.getByUsername("user1");
		Post post2 = postServiceImpl.getByTitle("post title 2");
		Long uid = user1.getId().longValue();
		Long pid = post2.getId();
		ThumbUp thumbUp = new ThumbUp();
		thumbUp.setPid(pid);
		thumbUp.setUid(uid);
		thumbUpDao.save(thumbUp);

		
		
	}

}
