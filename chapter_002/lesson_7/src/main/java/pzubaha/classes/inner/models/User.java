package pzubaha.classes.inner.models;

import java.sql.Timestamp;

/**
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class User {
    private int userId;
    private String userName;
    private String userEmail;
    private java.sql.Timestamp userRegistration;

    public User(int userId, String userName, String userEmail, Timestamp userRegistration) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userRegistration = userRegistration;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Timestamp getUserRegistration() {
        return userRegistration;
    }

    public void setUserRegistration(Timestamp userRegistration) {
        this.userRegistration = userRegistration;
    }
}
