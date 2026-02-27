//author: Developer
//version : 2.0

//Simulating OAtuh using a random token

package com.seveneleven.mycontact.user.auth;

import com.seveneleven.mycontact.user.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OAuthAuth implements Authentication {

    private List<User> users;

    public OAuthAuth(List<User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> login(String email, String ignoredPassword) {

        Optional<User> user = users.stream()
                .filter(u -> u.getEmailAddress().equals(email))
                .findFirst();

        if (user.isPresent()) {
            String dummyToken = UUID.randomUUID().toString();
            System.out.println("OAuth token generated: " + dummyToken);
        }

        return user;
    }
}