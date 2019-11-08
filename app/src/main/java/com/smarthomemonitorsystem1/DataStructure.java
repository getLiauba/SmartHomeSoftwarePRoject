package com.smarthomemonitorsystem1;

public class DataStructure {
    private String username;
    private String password;
    private String timestamp;
    private String password2;
    private String email;
    private String dob;


    public DataStructure(){

    }



    public DataStructure(String username, String password,String password2,String email,String dob, String timestamp) {
        this.username=username;
        this.password = password;
        this.password2 = password2;
        this.email = email;
        this.dob = dob;
        this.timestamp = timestamp;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;

    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
