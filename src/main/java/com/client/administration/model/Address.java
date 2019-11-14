package com.client.administration.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Client entity to persist data to the database
 * Field validation are defined in this class
 *
 * @author Julian Vasa
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street = "";

    private String city = "";

    private String country = "";

    private String zip = "";

}
