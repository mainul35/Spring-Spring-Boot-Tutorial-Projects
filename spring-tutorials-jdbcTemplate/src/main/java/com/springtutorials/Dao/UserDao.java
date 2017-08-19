package com.springtutorials.Dao;

import java.util.List;


import com.springtutorials.Model.User;

public interface UserDao {

	public void saveOrUpdate(User user);
	
	public boolean delete(Integer id);
	
	public User get(Integer id);
	
	public List<User> getAll();
}
