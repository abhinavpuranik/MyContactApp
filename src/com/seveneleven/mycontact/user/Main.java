//author: Developer
//version : 2.0

package com.seveneleven.mycontact.user;

import com.seveneleven.mycontact.user.auth.*;
import com.seveneleven.mycontact.user.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

    	//UC1

        UserService userService = new UserService();

        User user = userService.register(
                "FREE",
                "abhinav@email.com",
                "Password@123",
                "Abhinav",
                22
        );

        System.out.println("User Registered Successfully!");
        System.out.println("User Type: " + user.getUserType());

        // Store registered users
        List<User> users = new ArrayList<>();
        users.add(user);

        //UC2

        Authentication auth;

        // Change this to "OAUTH" to test OAuth
        //Can be BASIC or OAUTH
        String authType = "BASIC";

        if (authType.equalsIgnoreCase("BASIC")) {
            auth = new BasicAuth(users);
        } else {
            auth = new OAuthAuth(users);
        }

        Optional<User> loggedIn =
                auth.login("abhinav@email.com", "Password@123");

        if (loggedIn.isPresent()) {

            User sessionUser = loggedIn.get();

            System.out.println("Login Successful!");
            System.out.println("Welcome " + sessionUser.getUserName());
            System.out.println("User Type: " + sessionUser.getUserType());

        } else {
            System.out.println("Login Failed!");
        }
    }
}