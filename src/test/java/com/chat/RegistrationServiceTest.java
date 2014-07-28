package com.chat;


import com.chat.model.User;
import com.chat.services.IRegistrationService;
import com.chat.services.IUserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:all-spring-config.xml")
public class RegistrationServiceTest extends BasicUserTest {

    @Resource(name = "userService")
    private IUserService userService;

    @Resource(name = "registrationService")
    private IRegistrationService registrationService;


    @Before
    public void clearDB() {
        userService.deleteAllUsers();
    }

    @Test
    public void registrationValidateUserTest() {

        User testUser = createUser("testName", "testPassword", false);
        Map<String, Object> resultMap = registrationService.registration(testUser);
        assertEquals(null, resultMap);
        User savedUser = userService.getAllUsers().get(0);
        assertTrue(equalsUsers(testUser, savedUser));
        clearDB();

        User testUser2 = createUser("", "", false);
        resultMap = registrationService.registration(testUser2);
        assertEquals(2, resultMap.size());
        clearDB();

        User testUser3 = createUser("testName", "", false);
        resultMap = registrationService.registration(testUser3);
        assertEquals(1, resultMap.size());
        clearDB();

        User testUser4 = createUser("", "testPassword", false);
        resultMap = registrationService.registration(testUser4);
        assertEquals(1, resultMap.size());
        clearDB();

    }

    @Test
    public void registerUserTest() {
        User testUser = createUser("user", "password", false);
        List<User> list = userService.getAllUsers();
        assertEquals(0, list.size());
        registrationService.registerUser(testUser);
        list = userService.getAllUsers();
        assertEquals(1, list.size());
        assertTrue(equalsUsers(testUser, list.get(0)));
    }

}
