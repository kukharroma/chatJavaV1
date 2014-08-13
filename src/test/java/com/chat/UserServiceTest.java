package com.chat;


import com.chat.model.User;
import com.chat.services.impl.UserService;
import de.flapdoodle.embedmongo.MongoDBRuntime;
import de.flapdoodle.embedmongo.MongodExecutable;
import de.flapdoodle.embedmongo.MongodProcess;
import de.flapdoodle.embedmongo.config.MongodConfig;
import de.flapdoodle.embedmongo.distribution.Version;
import de.flapdoodle.embedmongo.runtime.Network;
import org.bson.types.ObjectId;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Represents methods which execute tests
 * on class UserService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/all-spring-config.xml")
public class UserServiceTest extends Assert {

    @Resource(name = "userService")
    private UserService userService;

    private static MongodExecutable mongodExecutable;

    private static MongodProcess mongodProcess;

    /**
     * Starts in-memory Mongo DB process
     */
    @BeforeClass
    public static void setup() throws Exception {
        MongoDBRuntime runtime = MongoDBRuntime.getDefaultInstance();
        mongodExecutable = runtime.prepare(new MongodConfig(Version.V1_6_5, 12345, Network.localhostIsIPv6()));
        mongodProcess = mongodExecutable.start();
    }

    /**
     * Cleans in-memory Mongo DB process
     */
    @After
    public void shutDown() throws Exception {
        userService.deleteAllUsers();
        mongodExecutable.cleanup();
    }

    /**
     * Stops in-memory Mongo DB process
     */
    @AfterClass
    public static void mongodStop() {
        mongodProcess.stop();
    }

    /**
     * Tests saving a user
     */
    @Test
    public void testSaveUser() {
        User user = createUser("name", "password", false);
        List<User> list = userService.getAllUsers();
        assertTrue(list.isEmpty());
        userService.save(user);
        list = userService.getAllUsers();
        assertTrue(!list.isEmpty());
        assertTrue(equalsUsers(user, list.get(0)));
    }

    /**
     * Tests saving a user with empty password
     */
    @Test
    public void testSaveUserEmptyPassword() {
        User user = createUser("name", "", false);
        List<User> list = userService.getAllUsers();
        assertTrue(list.isEmpty());
        userService.save(user);
        list = userService.getAllUsers();
        assertTrue(!list.isEmpty());
        assertTrue(equalsUsers(user, list.get(0)));
    }

    /**
     * Tests saving a user with empty name
     */
    @Test
    public void testSaveUserEmptyName() {
        User user = createUser("", "password", false);
        List<User> list = userService.getAllUsers();
        assertTrue(list.isEmpty());
        userService.save(user);
        list = userService.getAllUsers();
        assertTrue(!list.isEmpty());
        assertTrue(equalsUsers(user, list.get(0)));
    }

    /**
     * Tests saving users and set them (isOnline = true)
     */
    @Test
    public void testSaveUserOnlineTrue() {
        User user = createUser("name", "password", true);
        List<User> list = userService.getAllUsers();
        assertTrue(list.isEmpty());
        userService.save(user);
        list = userService.getAllUsers();
        assertTrue(!list.isEmpty());
        assertTrue(equalsUsers(user, list.get(0)));
    }

    /**
     * Tests saving user with empty name and password and
     * set (isOnline = true)
     */
    @Test
    public void testSaveUserEmptyPassAndNameOnlineTrue() {
        User user = createUser("", "", true);
        List<User> list = userService.getAllUsers();
        assertTrue(list.isEmpty());
        userService.save(user);
        list = userService.getAllUsers();
        assertTrue(!list.isEmpty());
        assertTrue(equalsUsers(user, list.get(0)));
    }

    /**
     * Tests loading a user from database by name
     */
    @Test
    public void testLoadUserByUsername() {
        User user = createUser("name", "password", false);
        userService.save(user);
        User testUser = (User) userService.loadUserByUsername("name");
        assertTrue(equalsUsers(user, testUser));
    }

    /**
     * Tests loading a user from database by name
     * if name is empty
     */
    @Test
    public void testLoadUserByEmptyName() {
        User user = createUser("", "password", false);
        userService.save(user);
        User testUser = (User) userService.loadUserByUsername("");
        assertTrue(equalsUsers(user, testUser));
    }

    /**
     * Tests getting all users from database
     */
    @Test
    public void testGetAllUsers() {
        User testUser1 = createUser("testUser1", "password1", false);
        User testUser2 = createUser("testUser2", "password2", false);
        User testUser3 = createUser("testUser3", "password3", false);
        User testUser4 = createUser("testUser4", "password4", false);
        User testUser5 = createUser("testUser5", "password5", false);
        User testUser6 = createUser("testUser6", "password6", false);
        User testUser7 = createUser("testUser7", "password7", false);
        User testUser8 = createUser("testUser8", "password8", false);

        userService.save(testUser1);
        userService.save(testUser2);
        userService.save(testUser3);
        userService.save(testUser4);
        userService.save(testUser5);
        userService.save(testUser6);
        userService.save(testUser7);
        userService.save(testUser8);

        List<User> testList = userService.getAllUsers();

        assertTrue(equalsUsers(testUser1, testList.get(0)));
        assertTrue(equalsUsers(testUser2, testList.get(1)));
        assertTrue(equalsUsers(testUser3, testList.get(2)));
        assertTrue(equalsUsers(testUser4, testList.get(3)));
        assertTrue(equalsUsers(testUser5, testList.get(4)));
        assertTrue(equalsUsers(testUser6, testList.get(5)));
        assertTrue(equalsUsers(testUser7, testList.get(6)));
        assertTrue(equalsUsers(testUser8, testList.get(7)));

    }

    /**
     * Tests getting all users from database which are online
     */
    @Test
    public void testGetAllOnlineUsers() {
        User testUser1 = createUser("testUser1", "password1", true);
        User testUser2 = createUser("testUser2", "password2", true);
        User testUser3 = createUser("testUser3", "password3", true);
        User testUser4 = createUser("testUser4", "password4", true);
        User testUser5 = createUser("testUser5", "password5", true);
        User testUser6 = createUser("testUser6", "password6", true);
        User testUser7 = createUser("testUser7", "password7", true);
        User testUser8 = createUser("testUser8", "password8", true);

        userService.save(testUser1);
        userService.save(testUser2);
        userService.save(testUser3);
        userService.save(testUser4);
        userService.save(testUser5);
        userService.save(testUser6);
        userService.save(testUser7);
        userService.save(testUser8);

        List<User> testList = userService.getAllOnlineUser();

        assertTrue(equalsUsers(testUser1, testList.get(0)) && testList.get(0).isOnline());
        assertTrue(equalsUsers(testUser2, testList.get(1)) && testList.get(1).isOnline());
        assertTrue(equalsUsers(testUser3, testList.get(2)) && testList.get(2).isOnline());
        assertTrue(equalsUsers(testUser4, testList.get(3)) && testList.get(3).isOnline());
        assertTrue(equalsUsers(testUser5, testList.get(4)) && testList.get(4).isOnline());
        assertTrue(equalsUsers(testUser6, testList.get(5)) && testList.get(5).isOnline());
        assertTrue(equalsUsers(testUser7, testList.get(6)) && testList.get(6).isOnline());
        assertTrue(equalsUsers(testUser8, testList.get(7)) && testList.get(7).isOnline());
    }

    /**
     * Gets all user which are not online
     */
    @Test
    public void testGetAllOnlineUsersEmpty() {
        User testUser1 = createUser("testUser1", "password1", false);
        User testUser2 = createUser("testUser2", "password2", false);
        User testUser3 = createUser("testUser3", "password3", false);
        User testUser4 = createUser("testUser4", "password4", false);

        userService.save(testUser1);
        userService.save(testUser2);
        userService.save(testUser3);
        userService.save(testUser4);

        List<User> testList = userService.getAllOnlineUser();
        assertTrue(testList.isEmpty());

    }

    /**
     * Tests deleting all users from database
     */
    @Test
    public void testDeleteAllUsers() {
        User testUser1 = createUser("testUser1", "password1", false);
        User testUser2 = createUser("testUser2", "password2", false);
        User testUser3 = createUser("testUser3", "password3", false);
        User testUser4 = createUser("testUser4", "password4", false);

        userService.save(testUser1);
        userService.save(testUser2);
        userService.save(testUser3);
        userService.save(testUser4);

        List<User> list = userService.getAllUsers();
        assertTrue(!list.isEmpty());
        userService.deleteAllUsers();
        list = userService.getAllUsers();
        assertTrue(list.isEmpty());

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
