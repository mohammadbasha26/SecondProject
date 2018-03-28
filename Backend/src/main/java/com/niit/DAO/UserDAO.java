package com.niit.DAO;

import java.util.List;

import com.niit.model.Job;
import com.niit.model.User;

public interface UserDAO {


	public boolean addUser(User user);

	public boolean deleteUser(int userId);

	public boolean updateUser(int userId);

	public List<Job> listUsers(String username);

	public boolean approvalUser(User user);

	public boolean rejectUser(User user);

	public Job getUser(int userId);

	public List<Job> listAllUsers();

	
	
}
