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
    private Integer id;

    private String username;
    
    private String password;
    
 
    @ManyToMany
    @JoinTable (name="user_role",
        joinColumns = {@JoinColumn(name="role_id")},
        inverseJoinColumns = {@JoinColumn(name="user_id")})
    private Set<Role> roles = new HashSet<>();
    
    @OneToMany(targetEntity = Post.class, cascade= {CascadeType.ALL}, fetch = FetchType.LAZY,
    		mappedBy = "user")    
    private List<Post> posts = new ArrayList<>();

    @OneToMany(targetEntity = Comment.class, cascade= {CascadeType.ALL}, fetch = FetchType.LAZY,
    		mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
    
    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
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
                ", username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
