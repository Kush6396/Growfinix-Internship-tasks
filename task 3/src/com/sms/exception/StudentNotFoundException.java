package com.sms.exception;

/**
 * Exception thrown when a student record cannot be found by the provided identifier or criteria.
 */
public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(int studentId) {
        super("Student with ID " + studentId + " was not found in the records.");
    }
}
