package com.client.administration.controller;

import com.client.administration.model.ApplicationUser;
import com.client.administration.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest Controller to allow user registration
 * It's mandatory to authenticate (with a user and password stored in the local DB) before performing requests to the Client Resource
 *
 * @author julianvasa
 */
@RestController
@RequestMapping("/users")
class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* Create a new ApplicationUser in the local DB */
    @PostMapping("/sign-up")
    public void signUp(@RequestBody ApplicationUser user) {
        userService.signUp(user);
    }
}
