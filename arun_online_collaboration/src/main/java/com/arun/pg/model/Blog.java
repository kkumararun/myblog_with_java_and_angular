package com.arun.pg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int blogId;

	@NotNull
	@NotEmpty
	String blogTitle;

	@NotNull
	@NotEmpty
	String blogCategory;

	@NotNull
	@NotEmpty
	String blogDiscription;;

	@NotNull
	@NotEmpty
	String blogStatus;

	@NotNull
	@NotEmpty
	String blogOwnerId;

	public int getBlogId() {
		return blogId;
	}

	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}

	public String getBlogTitle() {
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}

	public String getBlogCategory() {
		return blogCategory;
	}

	public void setBlogCategory(String blogCategory) {
		this.blogCategory = blogCategory;
	}

	public String getBlogDiscription() {
		return blogDiscription;
	}

	public void setBlogDiscription(String blogDiscription) {
		this.blogDiscription = blogDiscription;
	}

	public String getBlogStatus() {
		return blogStatus;
	}

	public void setBlogStatus(String blogStatus) {
		this.blogStatus = blogStatus;
	}

	public String getBlogOwnerId() {
		return blogOwnerId;
	}

	public void setBlogOwnerId(String blogOwnerId) {
		this.blogOwnerId = blogOwnerId;
	}

	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", blogTitle=" + blogTitle + ", blogDiscription=" + blogDiscription
				+ ", blogStatus=" + blogStatus + ", blogOwnerId=" + blogOwnerId + "]";
	}

}
