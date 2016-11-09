package com.arun.pg.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arun.pg.model.Blog;

@Repository("blogDAO")
@Transactional
public class BlogDAOImpl implements BlogDAO {

	@Autowired
	private SessionFactory session;

	@Override
	public List<Blog> getAllBlogs() {
		return session.getCurrentSession().createQuery("from Blog").list();
	}

	@Override
	public void insertBlog(Blog b) {
		session.getCurrentSession().persist(b);

	}

	@Override
	public void deleteBlogById(int blogId) {

		session.getCurrentSession().delete(getBlogById(blogId));
	}

	@Override
	public void updateBlog(Blog blog) {
		session.getCurrentSession().update(blog);

	}

	@Override
	public Blog getBlogById(int blogId) {
		Blog blog = session.getCurrentSession().get(Blog.class, new Integer(blogId));
		return blog;
	}

	@Override
	public List<Blog> getBlogByOwnerId(String ownerId) {

		Query query = session.getCurrentSession().createQuery("from Blog WHERE blogOwnerId=?");
		query.setParameter(0, ownerId);
		return query.list();
	}

}
