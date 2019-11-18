package com.client.administration.service;

import com.client.administration.model.ApplicationUser;
import com.client.administration.repository.ApplicationUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * User service handles all operations with users that are allowed to perform request on the Client Resource (in this case, just create new user)
 *
 * @author julianvasa
 */
@Service
public class UserServiceImpl implements UserService {
    private final ApplicationUserRepository applicationUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(ApplicationUserRepository applicationUserRepository,
        BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /* Create a new ApplicationUser in the DB */
    @Override
    public void signUp(ApplicationUser user)  {
        /* Forward user creation to userRepository */
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }
}
