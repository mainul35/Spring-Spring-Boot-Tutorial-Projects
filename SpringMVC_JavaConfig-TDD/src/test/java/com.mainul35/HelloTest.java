package com.mainul35;

import com.mainul35.model.User;
import com.mainul35.repository.UserRepository;
import com.mainul35.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HelloTest {
    private UserService userService;

    private UserRepository userRepositoryMock;

    @Before
    public void setUp() {
        userService = new UserService();

        userRepositoryMock = Mockito.mock(UserRepository.class);
        userService.setUserRepository(userRepositoryMock);
    }

    @Test
    public void saveUserTest () {
        User user = new User();
        user.setId(2L);
        user.setName("Syed Mainul Hasan");
        user.setAge(25);
        user.setCountry("Bangladesh");
        user.setEmail("mainuls181@gmail.com");
        user.setSex("Male");
        user.setPhoneNumber("+8801634440004");
        userRepositoryMock.save(user);

        Mockito.verify(userRepositoryMock, Mockito.times(1)).save(user);

    }

    @Test
    public void findUserTest () {
        User user = userService.getUser("mainuls18@gmail.com");

        Mockito.when(userRepositoryMock.save(Mockito.any(User.class))).thenReturn(user);

        User found = userRepositoryMock.findByEmail("mainuls18@gmail.com");

        Assert.assertEquals((long)user.getId(), 1L);

    }

}
