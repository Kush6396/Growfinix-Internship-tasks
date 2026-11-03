package com.studentmanagement.model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Abstract base class representing an individual within the educational institution.
 * Demonstrates:
 *  - Encapsulation: Private member variables with protected/public accessors and mutators,
 *    input validation rules guarding internal object state.
 *  - Abstraction: Declares abstract methods that subclasses must supply concrete logic for.
 */
public abstract class Person implements Displayable {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    /**
     * Default constructor for flexibility.
     */
    public Person() {
        this(0, "Unknown", "Unknown", "unknown@university.edu", "N/A");
    }

    /**
     * Parameterized constructor demonstrating constructor chaining.
     */
    public Person(int id, String firstName, String lastName, String email) {
        this(id, firstName, lastName, email, "N/A");
    }

    /**
     * Full parameterized constructor validating essential invariants.
     */
    public Person(int id, String firstName, String lastName, String email, String phoneNumber) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    // --- Abstract Methods (Abstraction) ---
    /**
     * Subclasses must provide the role description (e.g., Undergraduate, Graduate, Instructor).
     */
    public abstract String getRole();

    // --- Concrete Methods & Getters/Setters (Encapsulation) ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("ID cannot be negative. Received: " + id);
        }
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("First name cannot be null or empty.");
        }
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Last name cannot be null or empty.");
        }
        this.lastName = lastName.trim();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || !EMAIL_PATTERN.matcher(email.trim()).matches()) {
            throw new IllegalArgumentException("Invalid email format: " + email);
        }
        this.email = email.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = (phoneNumber != null && !phoneNumber.trim().isEmpty()) ? phoneNumber.trim() : "N/A";
    }

    @Override
    public void displayDetails() {
        System.out.println(getDetails());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("[%s ID: %d] %s (%s)", getRole(), id, getFullName(), email);
    }
}
