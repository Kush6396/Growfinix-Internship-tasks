package com.sms.exception;

/**
 * Exception thrown when student information fails business validation rules
 * (e.g., negative/out-of-range age, malformed email, empty name, invalid GPA).
 */
public class InvalidStudentDataException extends Exception {
    public InvalidStudentDataException(String message) {
        super(message);
    }

    public InvalidStudentDataException(String field, String reason) {
        super("Validation failed for field '" + field + "': " + reason);
    }
}
