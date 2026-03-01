//author: Developer
//version : 12.0

//Authentication interface to support multiple authentication types

package com.seveneleven.mycontact.user.auth;

import com.seveneleven.mycontact.user.model.User;
import java.util.Optional;

public interface Authentication {

    Optional<User> login(String identifier, String credential);
}