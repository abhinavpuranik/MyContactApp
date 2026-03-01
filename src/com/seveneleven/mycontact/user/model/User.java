//author: Developer
//version: 12.0

//User class which contains ability to change details

package com.seveneleven.mycontact.user.model;

import com.seveneleven.mycontact.user.auth.PasswordHasher;
import com.seveneleven.mycontact.user.validation.PasswordValidator;
import com.seveneleven.mycontact.contact.Model.Contact;
import java.util.ArrayList;
import java.util.List;
public abstract class User {

    private String emailAddress;
    private String password;
    private String userName;
    private int age;
    private String preference;
    
    private List<Contact> contacts = new ArrayList<>();

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
    public String getPreference() {
    	return preference;
    }

    public abstract String getUserType();
    
    
    public void updateUserName(String newUserName) {

        if (newUserName == null || newUserName.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        this.userName = newUserName;
    }
    
    public void updateAge(int newAge) {

        if (newAge <= 0) {
            throw new IllegalArgumentException("Invalid age");
        }

        this.age = newAge;
    }
    
    
    public void updatePreference(String newPreference) {

        if (newPreference == null || newPreference.isBlank()) {
            throw new IllegalArgumentException("Preference cannot be empty");
        }

        this.preference = newPreference;
    }
    
    
    public void changePassword(String oldPassword, String newPassword) {

        String hashedOld = PasswordHasher.hash(oldPassword);

        if (!this.password.equals(hashedOld)) {
            throw new IllegalArgumentException("Old password incorrect");
        }

        if (!PasswordValidator.isValid(newPassword)) {
            throw new IllegalArgumentException("New password is weak");
        }

        this.password = PasswordHasher.hash(newPassword);
    }
    
    public void addContact(Contact contact) {
    	contacts.add(contact);
    }
    
    public List<Contact> getContacts() {
    	return contacts;
    }
    
    


    
    

}