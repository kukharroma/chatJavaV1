package com.chat.mockTest;


import com.chat.dao.IUserDAO;
import com.chat.model.User;
import com.chat.services.impl.UserService;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/all-spring-config.xml")
public class UserServiceMockTest {

    @Resource(name = "userService")
    private UserService userService;

    private IUserDAO userDAOMock;

    @Before
    public void setUp() {
        userDAOMock = createMock(IUserDAO.class);
        userService.setUserDAO(userDAOMock);
    }

    @Test
    public void testLoadUserByUserName() {
        User user = createUser("name", "password", false);
        expect(userDAOMock.loadUserByUsername(user.getName())).andReturn(user);
        replay(userDAOMock);
        userService.loadUserByUsername(user.getName());
        verify(userDAOMock);
    }

    @Test
    public void testGetAllOnlineUsers() {
        expect(userDAOMock.getAllOnlineUser()).andReturn(new ArrayList<User>());
        replay(userDAOMock);
        userService.getAllOnlineUser();
        verify(userDAOMock);
    }

    @Test
    public void testGetAllUsers() {
        expect(userDAOMock.getAllUsers()).andReturn(new ArrayList<User>());
        replay(userDAOMock);
        userService.getAllUsers();
        verify(userDAOMock);
    }

    @Test
    public void testDeleteAllUsers(){
        userDAOMock.deleteAllUsers();
        replay(userDAOMock);
        userService.deleteAllUsers();
        verify(userDAOMock);
    }


    /**
     * Creates an instance of User
     *
     * @param name     name of user
     * @param password user's password
     * @param online   true if user is online, false if user is offline.
     * @return created instance of User
     */
    private User createUser(String name, String password, Boolean online) {
        User user = new User();
        user.setId(new ObjectId());
        user.setName(name);
        user.setPassword(password);
        user.setOnline(online);
        return user;
    }

}
