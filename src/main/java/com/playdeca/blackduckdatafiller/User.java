package com.playdeca.blackduckdatafiller;

import java.sql.Date;

public class User {
    private String phoneNumber;
    private String fullName;
    private String password;
    private Date dateTimeString;
    
    public User(String phoneNumber, String fullName, String password, Date dateTimeString) {
        this.phoneNumber = phoneNumber;
        this.fullName = fullName;
        this.password = password;
        this.dateTimeString = dateTimeString;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public String getPassword() {
        return password;
    }
    
    public Date getDateTimeString() {
        return dateTimeString;
    }
}
