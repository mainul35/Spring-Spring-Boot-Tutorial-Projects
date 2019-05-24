package com.springtutorials.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import com.springtutorials.Dao.UserDao;
import com.springtutorials.Model.User;

public class UserDaoImpl implements UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public void saveOrUpdate(User user) {
		// TODO Auto-generated method stub
		String sql = "";
		if(getAll().stream().filter(u->(user.getUsername().equals(u.getUsername()))).findAny().isPresent()){
			sql = "UPDATE `userdetails` SET `name` = ?, `email` = ?, `password` = ?, `phoneNo` = ? WHERE `username` = ?";
			jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(),
					user.getPhoneNo(), user.getUsername());
			System.out.println("Updating...");
		}else{
			sql = "insert into `userdetails`" + "(`name`, `email`, `password`, `username`, `phoneNo`) "
					+ "values(?,?,?,?,?)";
			jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPassword(), user.getUsername(),
					user.getPhoneNo());
			System.out.println("Inserting...");
		}
	}

	public boolean delete(Integer id) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM `userdetails` WHERE `id`=?";
		jdbcTemplate.update(sql, id);
		System.out.println("Deleted user with Id "+id+" Successfully.");
		return false;
	}

	public User get(Integer id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM `userdetails` where `id`="+id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<User>() {
			
	        public User extractData(ResultSet rs) throws SQLException,
	                DataAccessException {
	            if (rs.next()) {
	            User user = new User();
	            user.setId(rs.getInt("id"));
	            user.setEmail(rs.getString("email"));
	            user.setName(rs.getString("name"));
	            user.setPassword(rs.getString("password"));
	            user.setUsername(rs.getString("username"));
	            user.setPhoneNo(rs.getString("phoneNo"));
	            
	            return user;
	            }
				return null;
	        }
	 
	    });
		
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM `userdetails`";
	    List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
	 
	        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	        	User user = new User();
	 
	            user.setId(rs.getInt("id"));
	            user.setEmail(rs.getString("email"));
	            user.setName(rs.getString("name"));
	            user.setPassword(rs.getString("password"));
	            user.setUsername(rs.getString("username"));
	            user.setPhoneNo(rs.getString("phoneNo"));
	 
	            return user;
	        }
	 
	    });
	 
	    return userList;
	}

}
