//author: Developer
//version : 12.0
package com.seveneleven.mycontact.user.model;

public class FreeUser extends User {

    public FreeUser(String email, String password,
                    String username, int age) {
        super(email, password, username, age);
    }

    @Override
    public String getUserType() {
        return "FREE";
    }
}