package com.chat;

import com.chat.model.User;
import com.chat.services.impl.RegistrationService;
import com.chat.services.impl.UserService;
import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Represents methods which execute tests
 * on class RegistrationService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/all-spring-config.xml")
public class RegistrationServiceTest extends Assert {

    @Resource(name = "registrationService")
    private RegistrationService registrationService;

    @Resource(name = "userService")
    private UserService userService;

    /**
     * Clears database from all registered users
     * before and after each test
     */
    @Before
    @After
    public void clearDB() {
        userService.deleteAllUsers();
    }

    /**
     * Tests registration a user who is valid
     */
    @Test
    public void testRegistrationUser() {
        User user = createUser("name", "password", false);
        Map<String, Object> map = registrationService.registration(user);
        assertNull(map);
        User testUser = (User) userService.loadUserByUsername("name");
        assertTrue(equalsUsers(user, testUser));
    }

    /**
     * Tests registration a user who is not valid.
     * User has empty mame.
     */
    @Test
    public void testRegistrationUserEmptyName() {
        User user = createUser("", "password", false);
        Map<String, Object> map = registrationService.registration(user);
        assertNotNull(map);
        assertTrue(map.size() == 1);
        assertTrue(map.containsKey("nameFailed"));
    }

    /**
     * Tests registration a user who is not valid.
     * User has empty password.
     */
    @Test
    public void testRegistrationUserEmptyPassword() {
        User user = createUser("name", "", false);
        Map<String, Object> map = registrationService.registration(user);
        assertNotNull(map);
        assertTrue(map.size() == 1);
        assertTrue(map.containsKey("passwordFailed"));
    }

    /**
     * Tests registration a user who is not valid.
     * User has empty password and name.
     */
    @Test
    public void testRegistrationUserEmptyPasswordAndName() {
        User user = createUser("", "", false);
        Map<String, Object> map = registrationService.registration(user);
        assertNotNull(map);
        assertTrue(map.size() == 2);
        assertTrue(map.containsKey("passwordFailed"));
        assertTrue(map.containsKey("nameFailed"));
    }

    /**
     * Tests registration a user into database
     * User is valid.
     */
    @Test
    public void testRegisterUser() {
        User user = createUser("name", "password", false);
        registrationService.registerUser(user);
        User testUser = (User) userService.loadUserByUsername("name");
        assertTrue(equalsUsers(user, testUser));
    }

    /**
     * Tests registration a user into database
     * when user has empty name
     */
    @Test
    public void testRegisterUserEmptyName() {
        User user = createUser("", "password", false);
        registrationService.registerUser(user);
        User testUser = (User) userService.loadUserByUsername("");
        assertTrue(equalsUsers(user, testUser));
    }

    /**
     * Tests registration a user into database
     * when user has empty password
     */
    @Test
    public void testRegisterUserEmptyPassword() {
        User user = createUser("name", "", false);
        registrationService.registerUser(user);
        User testUser = (User) userService.loadUserByUsername("name");
        assertTrue(equalsUsers(user, testUser));
    }

    /**
     * Tests registration a user into database
     * when user has empty password and name
     */
    @Test
    public void testRegisterUserEmptyPasswordAndName() {
        User user = createUser("", "", false);
        registrationService.registerUser(user);
        User testUser = (User) userService.loadUserByUsername("");
        assertTrue(equalsUsers(user, testUser));
    }

    /**
     * Tests setting authority for user
     */
    @Test
    public void testSetAuthority() {
        User user = createUser("name", "password", false);
        registrationService.setAuthority(user);
        assertNotNull(user.getAuthorities());
    }

    /**
     * Tests setting authority if user is null
     */
    @Test(expected = NullPointerException.class)
    public void testSetAuthorityForNull() {
        registrationService.setAuthority(null);
    }

    /**
     * Equals two instance of User. If objects are equal
     * returns true else returns false.
     *
     * @param firstUser  user you want to compare
     * @param secondUser user with whom you want to compare
     * @return If objects are equal returns true else returns false.
     */
    private boolean equalsUsers(User firstUser, User secondUser) {
        boolean result = true;
        result = result && firstUser.getId().equals(secondUser.getId());
        result = result && firstUser.getUsername().equals(secondUser.getUsername());
        result = result && firstUser.getPassword().equals(secondUser.getPassword());
        result = result && firstUser.getName().equals(secondUser.getName());
        return result;
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
