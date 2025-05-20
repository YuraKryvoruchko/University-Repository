package models;

import java.sql.Date;

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private Date createdAtDate;
    private UserCategory category;

    public User(int userId, String username, String password, String email, Date createdAtDate, UserCategory category){
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAtDate = createdAtDate;
        this.category = category;
    }

    public enum UserCategory{
        PASSENGER,
        CASHIER,
        ADMINISTRATOR
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAtDate() {
        return createdAtDate;
    }
    public void setCreatedAtDate(Date createdAtDate) {
        this.createdAtDate = createdAtDate;
    }

    public UserCategory getCategory() {
        return category;
    }
    public void setCategory(UserCategory category) {
        this.category = category;
    }
}
