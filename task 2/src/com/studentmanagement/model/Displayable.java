package com.studentmanagement.model;

/**
 * Interface defining a contract for entities that can format and display
 * their detailed state. Demonstrates OOP Abstraction.
 */
public interface Displayable {
    /**
     * Prints a formatted representation of the entity to standard output.
     */
    void displayDetails();

    /**
     * Returns a structured summary string of the entity.
     *
     * @return Formatted multi-line string representing entity state.
     */
    String getDetails();
}
