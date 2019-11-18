package com.client.administration.security;

import com.client.administration.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static java.util.Collections.emptyList;

/**
 * RegistrationUserDetailService is injected in the Web Security configuration
 * It is called during authentication process to check if the user exists
 *
 * @author julianvasa
 */
@Component(value = "userDetailService")
class ApplicationUserDetailService implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository userRepository;

    /* Check if username exists in the DB */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.client.administration.model.ApplicationUser user = userRepository.findByUsername(username);
        return new User(String.valueOf(Objects.requireNonNull(user).getUsername()), user.getPassword(), emptyList());
    }
}
