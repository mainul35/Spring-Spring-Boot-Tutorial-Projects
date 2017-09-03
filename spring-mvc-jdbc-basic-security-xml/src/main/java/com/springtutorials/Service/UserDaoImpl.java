package com.springtutorials.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import com.springtutorials.Dao.UserDao;
import com.springtutorials.Model.Role;
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
		List<User> users = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		User user = new User();
		for(Map row: rows){
			user.setId((Integer)row.get("id"));
			user.setName((String)row.get("name"));
			user.setEmail((String)row.get("email"));
			user.setUsername((String)row.get("username"));
			user.setPassword((String)row.get("password"));
			user.setEnabled((boolean)row.get("enabled"));
			sql = "SELECT r.role_id, r.role_name "
					+ "FROM `roles` r, `userdetails` ud, `user_role` ur "
					+ "WHERE r.role_id = ur.role_id and ur.user_id = ud.id and ud.id = "+id;
			List<Role> roles = new ArrayList<>();
			List<Map<String, Object>> rows2 = new ArrayList<>();
			for(Map roleTableRow : rows2){
				Role role = new Role();
				role.setAuthority((String)roleTableRow.get("role_name"));
				roles.add(role);
			}
			user.setAuthorities(roles);
		}
		return user;
	}

	@Override
	public User getUserByUserName(String username) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM `userdetails` where `username`="+username;
		List<User> users = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		User user = new User();
		for(Map row: rows){
			user.setId((Integer)row.get("id"));
			user.setName((String)row.get("name"));
			user.setEmail((String)row.get("email"));
			user.setUsername((String)row.get("username"));
			user.setPassword((String)row.get("password"));
			user.setEnabled((boolean)row.get("enabled"));
			sql = "SELECT r.role_id, r.role_name "
					+ "FROM `roles` r, `userdetails` ud, `user_role` ur "
					+ "WHERE r.role_id = ur.role_id and ur.user_id = ud.id and ud.id = "+user.getId();
			List<Role> roles = new ArrayList<>();
			List<Map<String, Object>> rows2 = new ArrayList<>();
			for(Map roleTableRow : rows2){
				Role role = new Role();
				role.setAuthority((String)roleTableRow.get("role_name"));
				roles.add(role);
			}
			user.setAuthorities(roles);
		}
		return user;
	}

	public List<User> getAll() {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM `userdetails`";
		List<User> users = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		
		for(Map row: rows){
			User user = new User();
			user.setId((Integer)row.get("id"));
			user.setName((String)row.get("name"));
			user.setEmail((String)row.get("email"));
			user.setUsername((String)row.get("username"));
			user.setPassword((String)row.get("password"));
			user.setEnabled(((Integer)row.get("enabled"))==1?true:false);
			sql = "SELECT r.role_id, r.role_name "
					+ "FROM `roles` r, `userdetails` ud, `user_role` ur "
					+ "WHERE r.role_id = ur.role_id and ur.user_id = ud.id and ud.id = "+user.getId();
			List<Role> roles = new ArrayList<>();
			List<Map<String, Object>> rows2 = new ArrayList<>();
			for(Map roleTableRow : rows2){
				Role role = new Role();
				role.setAuthority((String)roleTableRow.get("role_name"));
				roles.add(role);
			}
			user.setAuthorities(roles);
			users.add(user);
		}
		return users;
	}

}
