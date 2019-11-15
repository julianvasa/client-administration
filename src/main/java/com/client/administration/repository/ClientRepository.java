package com.client.administration.repository;

import com.client.administration.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Spring Data Repository to persist clients in the database and to expose the data as a rest resource
 *
 * @author julianvasa
 */
@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {
    @RestResource(path = "byLastName", rel = "byLastName")
    Page<Client> findAllByLastNameAndDeactivatedIsFalse(@Param("lastname") String lastName, Pageable page);

    @RestResource(path = "byZipCode", rel = "byZipCode")
    Page<Client> findAllByAddressZipAndDeactivatedIsFalse(@Param("zip") int zip, Pageable page);

    @RestResource(path = "byCity", rel = "byCity")
    Page<Client> findAllByAddressCityAndDeactivatedIsFalse(@Param("city") String city, Pageable page);

    @RestResource(path = "byCountry", rel = "byCountry")
    Page<Client> findAllByAddressCountryAndDeactivatedIsFalse(@Param("country") String country, Pageable page);

    @RestResource(path = "byCityAndCountry", rel = "byCityAndCountry")
    Page<Client> findAllByAddressCityAndAddressCountryAndDeactivatedIsFalse(@Param("city") String city, @Param("country") String country, Pageable page);

}
