package com.client.administration.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Client entity to persist data to the database
 * Field validation are defined in this class
 *
 * @author Julian Vasa
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Please fill in the First Name")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "First Name should contains only letters!")
    @Length(min = 2)
    private String firstName;

    @NotEmpty(message = "Please fill in the Last Name")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Last Name should contains only letters!")
    @Length(min = 2)
    private String lastName;

    @NotEmpty(message = "Please fill in the Email")
    @Pattern(regexp = ".+@.+\\..+", message = "Wrong email!")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name="address")
    private Address address;

    @Pattern(regexp = "^[0-9+]*$", message = "Phone should contains only numbers and/or +!")
    private String phone = "";
}
