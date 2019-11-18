package com.smarthomemonitorsystem1;

public class Member {

    private String username;
    private String password;
    private String repassword;
    private String email;
    private String dob;

    private int numPhoto;

    public int getNumPhoto() {
        return numPhoto;
    }

    public String getUsername() {
        return username;
    }

    public void setNumPhoto(int numPhoto) {
        this.numPhoto = numPhoto;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public String getRepassword() {
        return repassword;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }

    public Member() {



    }
}
