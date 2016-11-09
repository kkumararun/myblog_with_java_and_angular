package com.arun.pg.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arun.pg.dao.BlogDAO;
import com.arun.pg.model.Blog;


@Controller
@RequestMapping(value="/blog") 
public class BlogController {

	@Autowired
private BlogDAO blogD;


	@RequestMapping(value="/allblogs", method=RequestMethod.GET)
	@ResponseBody
	public List<Blog> findAllBlog(){
		System.out.println(blogD.getAllBlogs());
		return blogD.getAllBlogs();
	}

}
