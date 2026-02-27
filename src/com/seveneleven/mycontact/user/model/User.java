package com.seveneleven.mycontact.user.model;

public abstract class User {

    private String emailAddress;
    private String password;
    private String userName;
    private int age;

    protected User(String emailAddress, String password,
                   String userName, int age) {

        this.emailAddress = emailAddress;
        this.password = password;
        this.userName = userName;
        this.age = age;
    }

    public String getEmailAddress() { return emailAddress; }
    public String getPassword() { return password; }
    public String getUserName() { return userName; }
    public int getAge() { return age; }

    public abstract String getUserType();
}