package main;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by Ryan on 11/20/2016.
 */
public class UserTest {

    @Test
    public void testPasswordHash() {
        User u = new User();
        u.setUserName("user1");
        u.setPassword("password".hashCode());

        assertNotEquals("password", u.getPassword());
    }
}
