package com.misa.knjizara.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

public class SecuredUserTest {

    private static User user;

    @BeforeAll
    public static void setUp(){
        user = new User("misa", "321");
    }

    @Test
    public void getPasswordTest(){
        var securedUser = new SecuredUser(user);
        Assertions.assertEquals("321", securedUser.getPassword());
        Assertions.assertNotEquals("321 ", securedUser.getPassword());
    }

    @Test
    public void getAuthoritiesTest(){
        Assertions.assertEquals(1, numberOfAuthoritiesPresent("read"));
        Assertions.assertEquals(0, numberOfAuthoritiesPresent("delete"));
    }

    private int numberOfAuthoritiesPresent(String authority){
        var securedUser = new SecuredUser(user);
        int count = 0;
        for(GrantedAuthority auth : securedUser.getAuthorities()){
            if(auth.getAuthority().equals(authority))
                count++;
        }

        return count;
    }

    @Test
    public void isEnabledTest(){
        var securedUser = new SecuredUser(user);
        Assertions.assertTrue(securedUser.isEnabled());
    }

}
