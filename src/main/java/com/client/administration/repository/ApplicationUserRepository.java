package com.client.administration.repository;

import com.client.administration.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository to persist users who are authorized to perform requests on the Client Resource
 *
 * @author julianvasa
 */
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    /** findByUsername is used by the authentication service: ApplicationUserDetailService */
    ApplicationUser findByUsername(String username);
}
