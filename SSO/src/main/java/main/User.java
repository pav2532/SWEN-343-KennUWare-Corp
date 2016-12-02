package main;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@NamedQueries({
        @NamedQuery(name = "findUserByUsername", query = "select u from User u where u.userName = :userName")
})


@Entity
@Table
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private int password;
    private String sessionID = "inactive";

    public User(){}

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getSessionID() {
        return sessionID;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public boolean authenticate(int password) {
        return this.password == password;
    }

}
