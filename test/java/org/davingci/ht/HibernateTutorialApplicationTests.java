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
		
		Post p3 = new Post();
		p3.setTitle("post title 3");
		p3.setContent("post content 3");
		p3.setThumbUpNum(10);
				
		User user3= new User();
		user3.setUsername("user3");
		user3.setPassword("123");
		p3.setUser(user3);
		userServiceImpl.save(user3);
		postServiceImpl.save(p3);	
	
		User user4 = new User();
		user4.setUsername("user4");
		user4.setPassword("123");
		userServiceImpl.save(user4);
		Post p4 = new Post();
		p4.setTitle("post title 4");
		p4.setContent("post content 4");
		p4.setThumbUpNum(10);
		p4.setUser(user4);
		postServiceImpl.save(p4);
		
		
	}

}
