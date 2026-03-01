
//author: Developer
//version : 12.0
//Password must have caps, symbols and 8 characters. REGEX validation

package com.seveneleven.mycontact.user.validation;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])" +
            "(?=.*[A-Z])" +
            "(?=.*\\d)" +
            "(?=.*[@#$%^&+=!])" +
            ".{8,}$";

    private static final Pattern pattern =
            Pattern.compile(PASSWORD_REGEX);

    public static boolean isValid(String password) {

        if (password == null || password.isBlank()) {
            return false;
        }

        return pattern.matcher(password).matches();
    }
}