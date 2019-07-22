package com.mainul35;

//import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.mainul35.model.User;
import com.mainul35.repository.UserRepository;
import com.mainul35.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MysqlConfig.class})
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
//        TransactionalTestExecutionListener.class,
//        DbUnitTestExecutionListener.class})
public class HelloTest {

    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Before
    public void init () {
        userService = new UserService();
        userService.setUserRepository(userRepository);
    }

    // This test will use repository instance to find all users
    @Test
    public void findAll() {
        assertThat(userRepository.findAll()).asList();
        System.out.println(Arrays.toString(userRepository.findAll().toArray()));
    }

    // This test will use service instance to find all users
    @Test
    public void addUser() {
        User user = new User();
        user.setName("Mainul Hasan");
        user.setEmail("mainuls18@gmail.com");
        user.setAge(25);
        user.setCountry("Bangladesh");
//        assertThat(userRepository.save(user)).isEqualTo(user);
        assertThat(userRepository.findAll()).asList();
        System.out.println(Arrays.toString(userService.getAllUsers().toArray()));
    }
}
