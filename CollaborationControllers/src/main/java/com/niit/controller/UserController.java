package com.niit.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.niit.DAO.UserDAO;
import com.niit.model.User;

@RestController
public class UserController {

	@Autowired
	UserDAO userDAO;

	/*
	 * @GetMapping("/getAllUsers") public ResponseEntity<List<User>> getAllUsers() {
	 * List<User> users = userDAO.getAllUser();
	 * 
	 * return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	 * 
	 * }
	 */

	@PostMapping("/registerUser")
	public ResponseEntity<User> registerUser(@RequestBody User user) {

		// blog.setStatus("NA");
		// blog.setLikes(0);
		// blog.setLoginname("Ravi Kumar");
		// blog.setUsername(session.getAttribute("username").toString());
		// String date = dt.getDateTime();

		// boolean value = blogDAO.addBlog(blog);

		// System.out.println("user Full Name:"+user.getFullName());
		// System.out.println("user Name:"+user.getUsername());

		if (userDAO.registerUser(user)) {

			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(value = "/checklogin")
	public ResponseEntity<User> checklogin(@RequestBody User user, HttpSession session) {

		
		
		System.out.println(user.getUserName()+" -------------------------- "+user.getPassword());
		if (userDAO.checkUser(user))

		{
			
			
			User tempUser = userDAO.getUser(user.getUserName());
			
			
			session.setAttribute("u", tempUser.getUserName());
			System.out.println(session.getAttribute("u").toString());
			return new ResponseEntity<User>(tempUser, HttpStatus.OK);

		} else {
			return new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED);
		}
	}

	/*
	 * @GetMapping("/getById/{userId}") public
	 * ResponseEntity<String>approveUser(@PathVariable("userId")int userId) { User
	 * user=(User)userDAO.getById(userId); if(userDAO.saveUser(user)) { return new
	 * ResponseEntity<String>("Approved",HttpStatus.OK); } else { return new
	 * ResponseEntity<String>("Error",HttpStatus.NOT_FOUND);
	 * 
	 * 
	 * } } /*
	 * 
	 * @GetMapping("/rejectBlog/{blogId}") public
	 * ResponseEntity<String>rejectBlog(@PathVariable("blogId")int blogId) { Blog
	 * blog=(Blog)blogDAO.getBlog(blogId); if(blogDAO.rejectBlog(blog)) { return new
	 * ResponseEntity<String>("rejected",HttpStatus.OK); } else { return new
	 * ResponseEntity<String>("Error",HttpStatus.NOT_FOUND);
	 * 
	 * 
	 * } }
	 * 
	 * @GetMapping("/deleteBlog/{blogId}") public
	 * ResponseEntity<String>deleteBlog(@PathVariable("blogId")int blogId) {
	 * 
	 * if(blogDAO.deleteBlog(blogId)) { return new
	 * ResponseEntity<String>("deleted",HttpStatus.OK); } else { return new
	 * ResponseEntity<String>("Error",HttpStatus.NOT_FOUND);
	 * 
	 * 
	 * } }
	 * 
	 * @GetMapping("/incrementLike/{blogId}") public
	 * ResponseEntity<String>incrementBlog(@PathVariable("blogId")int blogId) { Blog
	 * blog=(Blog)blogDAO.getBlog(blogId); if(blogDAO.incrementLike(blog)) { return
	 * new ResponseEntity<String>("incremented",HttpStatus.OK); } else { return new
	 * ResponseEntity<String>("Error",HttpStatus.OK);
	 * 
	 * } }
	 * 
	 * @GetMapping("/getBlog/{blogId}") public
	 * ResponseEntity<String>getBlog(@PathVariable("blogId")int blogId) { Blog
	 * blog=(Blog)blogDAO.getBlog(blogId); if(blog!=null) { return new
	 * ResponseEntity<String>("Get a Blog",HttpStatus.OK); } else { return new
	 * ResponseEntity<String>("Error",HttpStatus.NOT_FOUND);
	 * 
	 * } }
	 */

}
