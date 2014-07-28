package com.chat;

import com.chat.model.User;
import com.chat.services.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:all-spring-config.xml")
public class UserServiceTest extends BasicUserTest {

    @Resource(name = "userService")
    private IUserService userService;

    @Before
    public void clearDB() {
        userService.deleteAllUsers();
    }

    @Test
    public void saveUserTest() {
        List<User> list = userService.getAllUsers();
        assertEquals(0, list.size());
        User testUser = createUser("name", "password", true);
        userService.save(testUser);
        list = userService.getAllUsers();
        assertEquals(1, list.size());
        assertTrue(equalsUsers(testUser, list.get(0)));

    }


    @Test
    public void getAllUsersTest() {
        for (int k = 0; k < 25; k++) {
            userService.save(createUser("name" + k, "password", true));
        }
        List<User> list = userService.getAllUsers();
        assertEquals(25, list.size());
        clearDB();

        for (int k = 0; k < 5; k++) {
            userService.save(createUser("name" + k, "password", true));
        }
        list = userService.getAllUsers();
        assertEquals(5, list.size());
        clearDB();

        for (int k = 0; k < 0; k++) {
            userService.save(createUser("name" + k, "password", true));
        }
        list = userService.getAllUsers();
        assertEquals(0, list.size());
        clearDB();


    }

    @Test
    public void getAllOnlineUserTest() {
        for (int k = 0; k < 25; k++) {
            boolean online = false;
            if (k % 5 == 0) {
                online = true;
            }
            userService.save(createUser("name" + k, "password", online));
        }
        List<User> list = userService.getAllOnlineUser();
        assertEquals(5, list.size());
        clearDB();

        for (int k = 0; k < 10; k++) {
            boolean online = false;
            if (k % 5 == 0) {
                online = true;
            }
            userService.save(createUser("name" + k, "password", online));
        }
        list = userService.getAllOnlineUser();
        assertEquals(2, list.size());
        clearDB();


        for (int k = 0; k < 10; k++) {
            userService.save(createUser("name" + k, "password", false));
        }
        list = userService.getAllOnlineUser();
        assertEquals(0, list.size());
        clearDB();

        for (int k = 0; k < 10; k++) {
            userService.save(createUser("name" + k, "password", true));
        }
        list = userService.getAllOnlineUser();
        assertEquals(10, list.size());
        clearDB();

    }

}
