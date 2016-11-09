package com.arun.pg.dao;

import java.util.List;

import com.arun.pg.model.Blog;

public interface BlogDAO {

	public List<Blog> getAllBlogs();
	public void insertBlog(Blog b);
	public void deleteBlogById(int blogId);
	void updateBlog(Blog blog);
	public Blog getBlogById(int blogId);
	public List<Blog> getBlogByOwnerId(String ownerId);
}
