package com.springtutorials.DaoImpl;

import com.springtutorials.Dao.UserDao;
import com.springtutorials.JdbcManager.JdbcManager;
import com.springtutorials.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mainul35 on 9/23/2017.
 */
public class UserDaoImpl implements UserDao {

    @Autowired
    JdbcManager jdbcManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<String> params = new ArrayList<>();
        User user = new User();
        params.add(username);
        List<HashMap<String, Object>> maps = null;
        try {
            maps = jdbcManager.getQueryData("select * " +
                    "from  user u " +
                    "where u.username='"+username+"'", params);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
