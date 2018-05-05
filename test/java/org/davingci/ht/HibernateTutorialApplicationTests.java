package org.davingci.ht;
import org.davingci.ht.domain.*;
import org.davingci.ht.service.*;

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
	
	@Test
	public void contextLoads() {
		
		Post p1 = new Post();
		p1.setTitle("post title 1");
		p1.setContent("post content 1");
		
		Comment comment1 = new Comment();
		comment1.setText("user2 post is good");
			
		Post p2 = new Post();
		p2.setTitle("post title 2");
		p2.setContent("post content 2");
		Comment comment2 = new Comment();
		comment2.setText("user1 post is good");
				
		User user1= new User();
		user1.setUsername("user1");
		user1.setPassword("123");
		
		User user2 = new User();
		user2.setUsername("user2");
		user2.setPassword("123");

		p2.addComment(comment1);
		user1.addComment(comment1);
		user1.addPost(p2);			
		
		userServiceImpl.save(user1);
		
		
	}

}
