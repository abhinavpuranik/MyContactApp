//author: Developer
//version : 12.0

package com.seveneleven.mycontact.user.auth;

import com.seveneleven.mycontact.user.model.*;
import com.seveneleven.mycontact.user.validation.*;

public class UserService {

    public User register(String type,
                         String email,
                         String password,
                         String username,
                         int age) {

        
        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("Invalid email format");
        }

  
        if (!PasswordValidator.isValid(password)) {
            throw new IllegalArgumentException(
                    "Password must contain uppercase, lowercase, digit, special char and be 8+ characters"
            );
        }

        String hashedPassword = PasswordHasher.hash(password);


        if (type.equalsIgnoreCase("FREE")) {
            return new FreeUser(email, hashedPassword, username, age);

        } else if (type.equalsIgnoreCase("PREMIUM")) {
            return new PremiumUser(email, hashedPassword, username, age);

        } else {
            throw new IllegalArgumentException("Invalid user type");
        }
    }
}