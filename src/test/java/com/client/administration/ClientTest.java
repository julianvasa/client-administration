package com.client.administration;

import com.client.administration.model.Address;
import com.client.administration.model.Client;
import org.junit.Before;
import org.junit.Test;

import javax.validation.*;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClientTest {
    private static final String PHONE_NUMBER_NOT_CORRECT = "Phone should contains only numbers and/or +!";
    private static final String FIRST_NAME_SHOULD_CONTAINS_ONLY_LETTERS_NO_NUMBERS_OR_SPECIAL_CHARACTERS = "First Name should contains only letters!";
    private static final String LAST_NAME_SHOULD_CONTAINS_ONLY_LETTERS_NO_NUMBERS_OR_SPECIAL_CHARACTERS = "Last Name should contains only letters!";
    private static final String NAME_LENGTH_RANGE = "length must be between 2 and 2147483647";
    private static final String WRONG_EMAIL = "Not a well-formed email address";
    private static final String PLEASE_FILL_IN_THE_EMAIL = "Please fill in the Email";
    private static final String PLEASE_FILL_IN_THE_LAST_NAME = "Please fill in the Last Name";
    private static final String PLEASE_FILL_IN_THE_FIRST_NAME = "Please fill in the First Name";
    private static final String FIRST_NAME = "First";
    private static final String LAST_NAME = "Last";
    private static final String EMAIL = "user@test.com";
    private static final String PHONE = "+1212123123123";
    private static final String SHORT_FIRST_NAME = "a";
    private static final String WRONG_EMAIL_ADDRESS = "usert.com";
    private static final String WRONG_LAST_NAME = "123LastName";
    private static final String WRONG_FIRST_NAME = "123name";
    private static final String WRONG_PHONE = " 2 3324243424";
    private static final Address ADDRESS = Address.builder().street("Street").city("City").country("Country").zip(1000).build();
    private static Validator validator;

    @Before
    public void setup() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void createNewUser_thenNoErrors() {
        Client client = new Client();
        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        client.setEmail(EMAIL);
        client.setAddress(ADDRESS);

        Optional<ConstraintViolation<Client>> violation = validator.validate(client).stream().findFirst();
        if (violation.isPresent()) {
            throw new ValidationException(violation.get().getMessage());
        }
    }

    @Test
    public void whenCreatingWithNameTooShort_thenValidationErrorsAreThrown() {
        Client client = new Client();
        client.setFirstName(SHORT_FIRST_NAME);
        client.setLastName(LAST_NAME);
        client.setEmail(EMAIL);
        client.setAddress(ADDRESS);

        Optional<ConstraintViolation<Client>> violation = validator.validate(client).stream().findFirst();
        assertTrue(violation.isPresent());
        assertEquals(NAME_LENGTH_RANGE, violation.get().getMessage());
    }

    @Test
    public void whenCreatingWithoutEmail_thenValidationErrorsAreThrown() {
        Client client = new Client();
        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        client.setAddress(ADDRESS);

        Optional<ConstraintViolation<Client>> violation = validator.validate(client).stream().findFirst();
        assertTrue(violation.isPresent());
        assertEquals(PLEASE_FILL_IN_THE_EMAIL, violation.get().getMessage());

    }

    @Test
    public void whenCreatingWitoutLastName_thenValidationErrorsAreThrown() {
        Client client = new Client();
        client.setFirstName(FIRST_NAME);
        client.setEmail(EMAIL);

        Optional<ConstraintViolation<Client>> violation = validator.validate(client).stream().findFirst();
        assertTrue(violation.isPresent());
        assertEquals(PLEASE_FILL_IN_THE_LAST_NAME, violation.get().getMessage());

    }

    @Test
    public void whenCreatingWitoutFirstName_thenValidationErrorsAreThrown() {
        Client client = new Client();
        client.setLastName(LAST_NAME);
        client.setEmail(EMAIL);
        client.setAddress(ADDRESS);

        Optional<ConstraintViolation<Client>> violation = validator.validate(client).stream().findFirst();
        assertTrue(violation.isPresent());
        assertEquals(PLEASE_FILL_IN_THE_FIRST_NAME, violation.get().getMessage());
    }

    @Test
    public void whenCreatingFirstNameWithNumbers_thenValidationErrorsAreThrown() {
        Client client = new Client();
        client.setFirstName(WRONG_FIRST_NAME);
        client.setLastName(LAST_NAME);
        client.setEmail(EMAIL);
        client.setAddress(ADDRESS);

        Optional<ConstraintViolation<Client>> violation = validator.validate(client).stream().findFirst();
        assertTrue(violation.isPresent());
        assertEquals(FIRST_NAME_SHOULD_CONTAINS_ONLY_LETTERS_NO_NUMBERS_OR_SPECIAL_CHARACTERS, violation.get().getMessage());
    }
    @Test
    public void whenCreatingLastNameWithNumbers_thenValidationErrorsAreThrown() {
        Client client = new Client();
        client.setFirstName(FIRST_NAME);
        client.setLastName(WRONG_LAST_NAME);
        client.setEmail(EMAIL);
        client.setAddress(ADDRESS);

        Optional<ConstraintViolation<Client>> violation = validator.validate(client).stream().findFirst();
        assertTrue(violation.isPresent());
        assertEquals(LAST_NAME_SHOULD_CONTAINS_ONLY_LETTERS_NO_NUMBERS_OR_SPECIAL_CHARACTERS, violation.get().getMessage());
    }

    @Test
    public void whenCreatingEmailNotCorrect_thenValidationErrorsAreThrown() {
        Client client = new Client();
        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        client.setEmail(WRONG_EMAIL_ADDRESS);
        client.setAddress(ADDRESS);
        client.setPhone(PHONE);

        Optional<ConstraintViolation<Client>> violation = validator.validate(client).stream().findFirst();
        assertTrue(violation.isPresent());
        assertEquals(WRONG_EMAIL, violation.get().getMessage());
    }

    @Test
    public void whenCreatingPhoneNotCorrect_thenValidationErrorsAreThrown() {
        Client client = new Client();
        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        client.setEmail(EMAIL);
        client.setAddress(ADDRESS);
        client.setPhone(WRONG_PHONE);

        Optional<ConstraintViolation<Client>> violation = validator.validate(client).stream().findFirst();
        assertTrue(violation.isPresent());
        assertEquals(PHONE_NUMBER_NOT_CORRECT, violation.get().getMessage());
    }

    @Test
    public void whenCreatingPhoneCorrect_thenNoValidationErrorsAreThrown() {
        Client client = new Client();
        client.setFirstName(FIRST_NAME);
        client.setLastName(LAST_NAME);
        client.setEmail(EMAIL);
        client.setAddress(ADDRESS);
        client.setPhone(PHONE);

        Optional<ConstraintViolation<Client>> violation = validator.validate(client).stream().findFirst();
        if (violation.isPresent()) {
            throw new ValidationException(violation.get().getMessage());
        }
    }
}
