package com.arun.pg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.arun.pg.dao.BlogDAO;
import com.arun.pg.model.Blog;
import com.arun.pg.model.User;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:8887")
@RequestMapping(value = "/blog")
public class BlogController {

	@Autowired
	private BlogDAO blogDAO;

	/*
	 * with crossorigin annotaion another(which you are passing in origins)
	 * application can access your api which is running on another server
	 */
	@RequestMapping(value = "/allblogs", method = RequestMethod.GET)
	@ResponseBody
	public List<Blog> findAllBlog() {
		System.out.println(blogDAO.getAllBlogs());
		return blogDAO.getAllBlogs();
	}

	//insert img the blog
	@RequestMapping(value = "/insert/{title}/{ownerid}/{discription}/{cat}", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@PathVariable String title, @PathVariable String ownerid,@PathVariable String discription,
			@PathVariable String cat, UriComponentsBuilder ucBuilder) {
System.out.println("id = "+cat);
		Blog blog=new Blog();
		blog.setBlogTitle(title);
		blog.setBlogDiscription(discription);
		blog.setBlogStatus("unactive");
		blog.setBlogOwnerId(ownerid);
		blog.setBlogCategory(cat);
		System.out.print(blog);
		 blogDAO.insertBlog(blog);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") int id) {
		System.out.println("Updating User " + id);

		Blog b=blogDAO.getBlogById(id);

		if (b == null) {
			System.out.println("User with id " + id + " not found");
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}

		// currentUser.setUsername(user.getUserName());
		b.setBlogStatus("Active");

		blogDAO.updateBlog(b);		
		return new ResponseEntity<Blog>(b, HttpStatus.OK);
	}


}


