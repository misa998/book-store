package com.misa.knjizara.controller;

import com.misa.knjizara.entity.SecuredUser;
import com.misa.knjizara.entity.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class CustomInMemoryUserDetailsServiceTest {

    private String inputUsername;
    private String expected;
    private static CustomInMemoryUserDetailsService userDetailsService;

    public CustomInMemoryUserDetailsServiceTest(String inputUsername, String expected) {
        this.inputUsername = inputUsername;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection setUp(){
        List<UserDetails> users = List.of(
                new SecuredUser(new User("misa", "123")),
                new SecuredUser(new User("pera", "9090")),
                new SecuredUser(new User("glisa", "password")),
                new SecuredUser(new User("zorz", "pass"))
        );
        userDetailsService = new CustomInMemoryUserDetailsService(users);

        return Arrays.asList(new Object[][]{
                {users.get(0).getUsername(), "misa"},
                {users.get(1).getUsername(), "pera"},
                {users.get(2).getUsername(), "glisa"},
                {users.get(3).getUsername(), "zorz"},
        });
    }

    @Test
    public void loadUserByUsernameTest(){
        Assertions.assertEquals(
                userDetailsService.loadUserByUsername(inputUsername).getUsername(),
                expected
        );
    }

    @Test (expected = UsernameNotFoundException.class)
    public void loadUserByUsernameTestException(){
        userDetailsService.loadUserByUsername(inputUsername + " ");
    }

    @Test
    public void loadUserByUsernameTestExceptionMessage(){
        String testUserName = inputUsername + 1;
        Exception exception = Assertions.assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(testUserName));
        String expectedMessage = testUserName + " not found";

        Assertions.assertTrue(exception.getMessage().contains(expectedMessage));
    }
}
