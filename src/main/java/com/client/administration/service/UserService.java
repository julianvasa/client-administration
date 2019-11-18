package com.client.administration.service;

import com.client.administration.model.ApplicationUser;

/**
 * User service handles all operations with users that are allowed to perform request on the Client Resource (in this case, just create new user)
 *
 * @author julianvasa
 */
public interface UserService {
    /* Create a new ApplicationUser in the DB */
    void signUp(ApplicationUser user);
}
