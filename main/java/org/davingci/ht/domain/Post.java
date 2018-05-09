package org.davingci.ht.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;



@Entity
@Table(name="post")
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	

	private String title;
	
		
	private String content;
	
	private int thumbUpNum;
	
	public int getThumbUpNum() {
		return thumbUpNum;
	}

	public void setThumbUpNum(int thumbUpNum) {
		this.thumbUpNum = thumbUpNum;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="posted_at")
	private Date postedAt = new Date();
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
	private User user;
	
	public Date getPostedAt() {
		return postedAt;
	}


	@OneToMany(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void addComment(Comment comment) {
		comments.add(comment);		
		comment.setPost(this);
	}
	
	public void removeComment(Comment comment) {
		comment.setPost(null);
		this.comments.remove(comment);
	}
	

}
