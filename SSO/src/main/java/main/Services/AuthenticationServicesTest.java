package main.Services;


import com.sun.org.apache.xpath.internal.operations.Bool;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.runner.RunWith;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gson.Gson;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import org.mockito.Mockito;

import main.User;
import main.Services.AuthenticationServices;
import org.omg.CORBA.SystemException;
import spark.Request;
import spark.Response;

@RunWith(MockitoJUnitRunner.class)

public class AuthenticationServicesTest {

    @Test
    public void createUserTest() throws Exception {

        Session mockedSession = mock(Session.class);

        User testUser = new User();
        testUser.setUserName("testUser");
        testUser.setPassword(48690);

        assertEquals(testUser.getUserName(), AuthenticationServices.createUser("testUser", "123", mockedSession).getUserName());
        assertEquals(testUser.getPassword(), AuthenticationServices.createUser("testUser", "123", mockedSession).getPassword());

    }

    @Test
    public void loginTest() throws Exception{

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Request req  = mock(Request.class);
        spark.Session sess = mock(spark.Session.class);
        List<String> uids = mock(List.class);

        when(req.session(true)).thenReturn(sess);
        when(sess.id()).thenReturn("1212345");

        AuthenticationServices.createUser("testUser", "123", session);

        assertEquals("1212345", AuthenticationServices.login("testUser","123", req, session, uids));
        session.close();
        sessionFactory.close();
    }

    @Test
    public void editUserTest() throws Exception{

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Request req  = mock(Request.class);
        spark.Session sess = mock(spark.Session.class);
        List<String> uids = mock(List.class);

        when(req.session(true)).thenReturn(sess);
        when(sess.id()).thenReturn("1212345");

        AuthenticationServices.createUser("testUser", "123", session);
        String sessionID = AuthenticationServices.login("testUser", "123", req, session, uids);

        User testUser = AuthenticationServices.editUser("testUser","edit", "321", sessionID, session);

        assertEquals("edit", testUser.getUserName());
        assertEquals("321".hashCode(), testUser.getPassword());
        session.close();
        sessionFactory.close();
    }

    @Test
    public void verifySessionTest() throws Exception{

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Response res = mock(Response.class);
        Request req  = mock(Request.class);
        spark.Session sess = mock(spark.Session.class);
        List<String> uids = mock(List.class);

        when(req.session(true)).thenReturn(sess);
        when(sess.id()).thenReturn("1212345");

        AuthenticationServices.createUser("testUser", "123", session);
        String sessionID = AuthenticationServices.login("testUser", "123", req, session, uids);

        assertEquals("valid", AuthenticationServices.verifySession("testUser", sessionID, session, res));
        session.close();
        sessionFactory.close();
    }

    @Test
    public void logoutTest() throws Exception{

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Response res = mock(Response.class);
        Request req  = mock(Request.class);
        spark.Session sess = mock(spark.Session.class);
        List<String> uids = mock(List.class);

        when(req.session(true)).thenReturn(sess);
        when(sess.id()).thenReturn("1212345");

        AuthenticationServices.createUser("testUser", "123", session);
        String sessionID = AuthenticationServices.login("testUser", "123", req, session, uids);

        assertEquals("localhost:3000/kennuware/sso/login", AuthenticationServices.logout("testUser", sessionID, session, res));
        session.close();
        sessionFactory.close();
    }

    @Test
    public void deleteUserTest() throws Exception{

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Request req  = mock(Request.class);
        spark.Session sess = mock(spark.Session.class);
        List<String> uids = mock(List.class);

        when(req.session(true)).thenReturn(sess);
        when(sess.id()).thenReturn("1212345");

        AuthenticationServices.createUser("testUser", "123", session);
        String sessionID = AuthenticationServices.login("testUser", "123", req, session, uids);
        Boolean test = true;

        assertEquals(test, AuthenticationServices.deleteUser("testUser", sessionID, session));
        session.close();
        sessionFactory.close();
    }


}
