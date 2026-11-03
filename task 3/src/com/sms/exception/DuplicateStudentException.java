package com.sms.exception;

/**
 * Exception thrown when an attempt is made to insert a student whose ID already exists.
 */
public class DuplicateStudentException extends Exception {
    public DuplicateStudentException(String message) {
        super(message);
    }

    public DuplicateStudentException(int studentId) {
        super("A student with ID " + studentId + " already exists in the system.");
    }
}
