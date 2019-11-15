package com.client.administration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * Client entity to persist data to the database
 * Field validation are defined in this class
 *
 * @author Julian Vasa
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
@Builder
@Audited
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street = "";

    private String city = "";

    private String country = "";

    private int zip;

}
