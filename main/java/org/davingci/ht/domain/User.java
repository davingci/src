package org.davingci.ht.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String username;
    
    private String name;
    
    private String password;
    
    private String salt;
    
    private byte state;//用户状态,0:创建未认证（比如没有激活，没有输入验证码等等）--等待验证的用户 , 1:正常状态,2：用户被锁定.
    
    public byte getState() {
		return state;
	}

	public void setState(byte state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


	//role control
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable (name="user_role",
        joinColumns = {@JoinColumn(name="user_id")},
        inverseJoinColumns = {@JoinColumn(name="role_id")})
    private List<Role> roles;
    
    
    //post and comment control
    @OneToMany(targetEntity = Post.class, cascade= {CascadeType.ALL}, fetch = FetchType.LAZY,
    		mappedBy = "user")    
    private List<Post> posts = new ArrayList<>();

    @OneToMany(targetEntity = Comment.class, cascade= {CascadeType.ALL}, fetch = FetchType.LAZY,
    		mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "relation",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "following_id"))
    private List<User> following;
        
    public List<User> getFollowing() {
		return following;
	}

	public void setFollowing(List<User> following) {
		this.following = following;
	}

	public List<User> getFollowers() {
		return followers;
	}

	
	@ManyToMany(mappedBy = "following")
    private List<User> followers;
    
    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void addPost(Post post) {
		posts.add(post);
		post.setUser(this);
	}

	public void removePost(Post post) {
		post.setUser(null);
		this.posts.remove(post);
	}
	
	public List<Comment> getComments(){
		return comments;
	}
	
	public void addComment(Comment comment) {
		comments.add(comment);
		comment.setUser(this);
	}
	
	public void removeComment(Comment comment) {
		comment.setUser(null);
		this.comments.remove(comment);
	}
	
	/* salt */
	
	public String getCredentialsSatl() {
		return this.username + this.salt;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username);
    }
	@Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username=" + username +  
                ",password=" + password + 
                ", salt =" + salt +
                ",state=" + state +
                ", roles=" + roles +
                "}";
    }
}
