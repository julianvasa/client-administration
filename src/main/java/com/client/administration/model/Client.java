package com.client.administration.model;

import com.client.administration.audit.Audit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * Client entity to persist data to the database
 * Field validation are defined in this class
 *
 * @author julianvasa
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
@Audited
@Builder
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
    @Email(message = "Not a well-formed email address")
    private String email;

    @Pattern(regexp = "^[0-9+]*$", message = "Phone should contains only numbers and/or +!")
    private String phone = "";

    private boolean deactivated = false;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Address.class)
    @JoinColumn(name = "address")
    private Address address;

    @Embedded
    private Audit audit = new Audit();
}
