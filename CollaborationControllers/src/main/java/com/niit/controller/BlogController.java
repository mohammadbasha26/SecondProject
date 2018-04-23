package com.niit.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.BlogDAO;
import com.niit.model.Blog;

@RestController
public class BlogController {

	@Autowired
	BlogDAO blogDAO;
	

	@GetMapping(value = "/demo")
	public ResponseEntity<String> demo() {

		return new ResponseEntity<String>("Hello Spring Restful API..............", HttpStatus.OK);

	}

	@GetMapping("/getAllBlogs")
	public ResponseEntity<List<Blog>> getAllBlogs() {
		List<Blog> blogs = blogDAO.listAllBlogs();

		return new ResponseEntity<List<Blog>>(blogs, HttpStatus.OK);

	}

	@PostMapping("/addBlog")
	public ResponseEntity<Blog> addBlog(@RequestBody Blog blog,HttpSession session) {

	
		blog.setStatus("NA");
		String uname=session.getAttribute("u").toString();
		blog.setLoginname(uname);
		blog.setCreateDate(new Date());
		// boolean value = blogDAO.addBlog(blog);
		
		System.out.println("Blog Name:"+blog.getBlogName());
		System.out.println("Blog Content:"+blog.getBlogContent());

		if (blogDAO.addBlog(blog)) 
		{

			return new ResponseEntity<Blog>(blog, HttpStatus.OK);
		} 
		else
		{
			return new ResponseEntity<Blog>(blog, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
@GetMapping("/approveBlog/{blogId}")
public ResponseEntity<Blog>approveBlog(@PathVariable("blogId")int blogId)
{
	Blog blog=(Blog)blogDAO.getBlog(blogId);
	if(blogDAO.approvalBlog(blog))
	{
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		
		
	}
}

@GetMapping("/rejectBlog/{blogId}")
public ResponseEntity<Blog>rejectBlog(@PathVariable("blogId")int blogId)
{
	Blog blog=blogDAO.getBlog(blogId);
	if(blogDAO.rejectBlog(blog))
	{
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		
		
	}
}
@GetMapping("/deleteBlog/{blogId}")
public ResponseEntity<Blog>deleteBlog(@PathVariable("blogId")int blogId)
{ Blog blog=blogDAO.getBlog(blogId);

	
	if(blogDAO.deleteBlog(blogId))
	{
		return new ResponseEntity<Blog>(blog,HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		
		
	}
}

@GetMapping("/incrementLike/{blogId}")
public ResponseEntity<String>incrementBlog(@PathVariable("blogId")int blogId)
{
	Blog blog=(Blog)blogDAO.getBlog(blogId);
	if(blogDAO.incrementLike(blog))
	{
		return new ResponseEntity<String>("incremented",HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<String>("Error",HttpStatus.OK);
				
	}
}

@GetMapping("/getBlog/{blogId}")
public ResponseEntity<String>getBlog(@PathVariable("blogId")int blogId)
{
	Blog blog=(Blog)blogDAO.getBlog(blogId);
	if(blog!=null)
	{
		return new ResponseEntity<String>("Get a Blog",HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<String>("Error",HttpStatus.NOT_FOUND);
				
	}
}

@GetMapping("/showAllApprovedBlogs")
public ResponseEntity<List<Blog>> showAllAppovedBlogs(HttpSession session)
{
	//String uname=(session.getAttribute("u")).toString();
//	String loginname=(User)session.getAttribute("u")).getLoginname();
	
	String loginname=session.getAttribute("u").toString();
	
			List<Blog> listBlogs=blogDAO.listApprovedBlogs();
	if(listBlogs!=null)
	{
		return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		
		
	}
	else
	{
		return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
	
}

}
@GetMapping("/showUserBlogs")
public ResponseEntity<List<Blog>>userBlogs(HttpSession session)
{
	//String uname=(session.getAttribute("u")).toString();
//	String loginname=(User)session.getAttribute("u")).getLoginname();
	
	String loginname=session.getAttribute("u").toString();
	
			List<Blog> listBlogs=blogDAO.listBlogs(loginname);
	if(listBlogs!=null)
	{
		return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
		
		
	}
	else
	{
		return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
	
}

}

@GetMapping("/showAllBlogs")
public ResponseEntity<List<Blog>>showAllBlog()
{
	List<Blog> listAllBlogs=blogDAO.listAllBlogs();
	if(listAllBlogs!=null)
	{
		return new ResponseEntity<List<Blog>>(listAllBlogs,HttpStatus.OK);
		
	}
	else
	{
		return new ResponseEntity<List<Blog>>(listAllBlogs,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
}



}
