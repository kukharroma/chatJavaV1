package com.chat.cucumber;


import com.chat.model.User;
import com.chat.services.impl.UserService;
import cucumber.api.java.en.Given;
import cucumber.runtime.java.StepDefAnnotation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@ContextConfiguration(locations = "classpath:/all-spring-config.xml")
public class UserServiceStepdefs {

    @Resource(name = "userService")
    private UserService userService;

    @Given("^save a user in db$")
    public void save_a_user_in_db() {
        User user = new User();
        user.setId(new ObjectId());
        user.setOnline(false);
        user.setName("testUser");
        user.setPassword("password");
        userService.save(user);
    }

    @Given("^get a user by name \"([^\"]*)\"$")
    public void get_a_user_by_name(String name) {
        User user = createUser("alex", "password", false);
        userService.save(user);
        User testUser = (User) userService.loadUserByUsername(name);
        assertTrue(testUser.equals(user));
    }

    @Given("^get all online users$")
    public void get_all_online_users() {
        userService.deleteAllUsers();
        User user1 = createUser("alex1", "password", true);
        User user2 = createUser("alex2", "password", true);
        User user3 = createUser("alex3", "password", true);
        User user4 = createUser("alex4", "password", true);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        List<User> list = userService.getAllUsers();
        assertTrue(!list.isEmpty());
        assertEquals(list.size(), 4);

    }

    @Given("^delete all users$")
    public void delete_all_users() {
        userService.deleteAllUsers();
        List<User> list = userService.getAllUsers();
        assertTrue(list.isEmpty());
        User user1 = createUser("alex1", "password", true);
        User user2 = createUser("alex2", "password", true);
        User user3 = createUser("alex3", "password", true);
        User user4 = createUser("alex4", "password", true);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        userService.getAllUsers();
        assertTrue(!list.isEmpty());
        assertEquals(list.size(), 4);
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
