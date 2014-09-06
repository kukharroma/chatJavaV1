package com.chat.mockTest;


import com.chat.model.User;
import com.chat.services.IRegistrationService;
import com.chat.services.IUserService;
import com.chat.services.validator.IUserValidator;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/all-spring-config.xml")
public class RegServiceMockTest {

    @Resource(name = "registrationService")
    private IRegistrationService registrationService;

    private IUserValidator userValidatorMock;

    private IUserService userServiceMock;

    @Before
    public void setUp() {
        userValidatorMock = createMock(IUserValidator.class);
        userServiceMock = createMock(IUserService.class);
        registrationService.setUserValidator(userValidatorMock);
        registrationService.setUserService(userServiceMock);
    }


    @Test
    public void testRegistration() {
        User user = createUser("name", "password", true);
        expect(userValidatorMock.validateUser(anyObject(User.class))).andReturn(new HashMap<String, Object>());
        userServiceMock.save(user);
        replay(userValidatorMock);
        replay(userServiceMock);
        registrationService.registration(user);
        verify(userServiceMock, userValidatorMock);
    }

    @Test
    public void testRegisterUser() {
        User user = createUser("name", "password", true);
        userServiceMock.save(user);
        replay(userServiceMock);
        registrationService.registerUser(user);
        verify(userServiceMock);
    }

    @Test
    public void testSetAuthority() {
        User user = createUser("name", "password", true);
        replay(userServiceMock);
        registrationService.setAuthority(user);
        verify(userServiceMock);
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
