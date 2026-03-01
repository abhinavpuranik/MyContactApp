//author: Developer
//version : 12.0

package com.seveneleven.mycontact.user.validation;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final Pattern pattern =
            Pattern.compile(EMAIL_REGEX);

    public static boolean isValid(String email) {

        if (email == null || email.isBlank()) {
            return false;
        }

        return pattern.matcher(email).matches();
    }
}