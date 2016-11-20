package main.Services;

import com.sun.org.apache.xerces.internal.impl.dv.xs.BooleanDV;
import main.User;
import com.google.gson.Gson;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.hibernate.Query;
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.UUID;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import org.apache.commons.codec.binary.Base64;


public class AuthenticationServices {

    public static String login(String username, String password, Session dbSession){
        User user = new User();
        Gson gson = new Gson();

        Query query = dbSession.getNamedQuery("findUserByUsername").setString("userName", username);
        List<User> results = (List<User>)query.list();
        // Should only be one result
        for(User u: results) {
            if(u.authenticate(password.hashCode())) {
                String uniqueID = UUID.randomUUID().toString();
                u.setSessionID(uniqueID);
                dbSession.save(u);

                return uniqueID;
            }
        }

        // Fine for now, but should eventually use http status codes instead
        return "invalid";
    }

    public static User createUser(String username, String password, Session dbSession){

        User newUser = new User();

        newUser.setPassword(password.hashCode());
        newUser.setUserName(username);

        dbSession.save(newUser);
        return newUser;
    }

    public static User editUser(String username, String newUserName, String newPassword, String sessionID, Session dbSession){

        Query query = dbSession.getNamedQuery("findUserByUsername").setString("userName", username);
        List<User> results = (List<User>)query.list();
        // Should only be one result
        for(User u: results) {
            if(sessionID.equals(u.getSessionID())) {

                u.setUserName(newUserName);
                u.setPassword(newPassword.hashCode());

                dbSession.save(u);
                return u;
            }
        }

        return null;
    }

    public static Boolean verifySession(String username, String sessionID, Session dbSession){

        Query query = dbSession.getNamedQuery("findUserByUsername").setString("userName", username);
        List<User> results = (List<User>)query.list();
        // Should only be one result
        for(User u: results) {
            if(sessionID.equals(u.getSessionID())) {
                return true;
            }
        }

        return false;
    }

    public static Boolean logout(String username, String sessionID, Session dbSession){

        Query query = dbSession.getNamedQuery("findUserByUsername").setString("userName", username);
        List<User> results = (List<User>)query.list();
        // Should only be one result
        for(User u: results) {
            if(sessionID.equals(u.getSessionID())) {
                u.setSessionID("inactive");
                dbSession.save(u);
                return true;
            }
        }

        return false;
    }

    public static Boolean deleteUser(String username, String sessionID, Session dbSession){

        Query query = dbSession.getNamedQuery("findUserByUsername").setString("userName", username);
        List<User> results = (List<User>)query.list();
        // Should only be one result
        for(User u: results) {
            if(sessionID.equals(u.getSessionID())) {
                dbSession.delete(u);
                return true;
            }
        }

        return false;
    }



}

