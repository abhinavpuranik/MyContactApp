//author: Developer
//version : 12.0

//Simple authentication

package com.seveneleven.mycontact.user.auth;

import com.seveneleven.mycontact.user.model.User;

import java.util.List;
import java.util.Optional;

public class BasicAuth implements Authentication {

    private List<User> users;

    public BasicAuth(List<User> users) {
        this.users = users;
    }

    @Override
    public Optional<User> login(String email, String password) {

        String hashed = PasswordHasher.hash(password);

        return users.stream()
                .filter(u -> u.getEmailAddress().equals(email)
                        && u.getPassword().equals(hashed))
                .findFirst();
    }
}