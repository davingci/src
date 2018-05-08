package org.davingci.ht.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thumb_up")
public class ThumbUp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private Long pid;  //post id
	
	
	private Long uid; // user id

	public Long getId() {
		return id;
	}

	public Long getPid() {
		return pid;
	}

	public Long getUid() {
		return uid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

}
