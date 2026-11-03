package com.sms.util;

import com.sms.exception.InvalidStudentDataException;
import com.sms.model.Student;

import java.util.regex.Pattern;

/**
 * Utility class providing validation methods for student entity fields and console inputs.
 */
public class InputValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@([A-Za-z0-9]+(-[A-Za-z0-9]+)*\\.)+[A-Za-z]{2,63}$"
    );

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^\\d{10}$"
    );

    private static final Pattern NAME_PATTERN = Pattern.compile(
            "^[a-zA-Z\\s.'-]{2,50}$"
    );

    /**
     * Validates an entire Student object.
     *
     * @param student The student object to validate
     * @throws InvalidStudentDataException if any field is invalid
     */
    public static void validateStudent(Student student) throws InvalidStudentDataException {
        if (student == null) {
            throw new InvalidStudentDataException("Student", "Student record cannot be null.");
        }
        validateId(student.getId());
        validateName(student.getName());
        validateAge(student.getAge());
        validateCourse(student.getCourse());
        validateGpa(student.getGpa());
        validateEmail(student.getEmail());
        validatePhone(student.getContactNumber());
    }

    public static void validateId(int id) throws InvalidStudentDataException {
        if (id <= 0) {
            throw new InvalidStudentDataException("ID", "Student ID must be a positive integer greater than zero.");
        }
    }

    public static void validateName(String name) throws InvalidStudentDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidStudentDataException("Name", "Student name cannot be empty or blank.");
        }
        String trimmed = name.trim();
        if (trimmed.length() < 2 || trimmed.length() > 50) {
            throw new InvalidStudentDataException("Name", "Name length must be between 2 and 50 characters.");
        }
        if (!NAME_PATTERN.matcher(trimmed).matches()) {
            throw new InvalidStudentDataException("Name", "Name can only contain alphabets, spaces, apostrophes, and hyphens.");
        }
    }

    public static void validateAge(int age) throws InvalidStudentDataException {
        if (age < 15 || age > 100) {
            throw new InvalidStudentDataException("Age", "Student age must be between 15 and 100 years.");
        }
    }

    public static void validateCourse(String course) throws InvalidStudentDataException {
        if (course == null || course.trim().isEmpty()) {
            throw new InvalidStudentDataException("Course", "Course name cannot be empty or blank.");
        }
        String trimmed = course.trim();
        if (trimmed.length() < 2 || trimmed.length() > 60) {
            throw new InvalidStudentDataException("Course", "Course length must be between 2 and 60 characters.");
        }
    }

    public static void validateGpa(double gpa) throws InvalidStudentDataException {
        if (Double.isNaN(gpa) || gpa < 0.0 || gpa > 4.0) {
            throw new InvalidStudentDataException("GPA", "GPA must be a valid number between 0.00 and 4.00.");
        }
    }

    public static void validateEmail(String email) throws InvalidStudentDataException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidStudentDataException("Email", "Email address cannot be empty or blank.");
        }
        String trimmed = email.trim();
        if (!EMAIL_PATTERN.matcher(trimmed).matches()) {
            throw new InvalidStudentDataException("Email", "Invalid email format. Example: alex.smith@university.edu");
        }
    }

    public static void validatePhone(String phone) throws InvalidStudentDataException {
        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidStudentDataException("Phone", "Contact number cannot be empty.");
        }
        // Remove spaces, hyphens, and parenthesis for checking
        String cleanPhone = phone.replaceAll("[\\s\\-\\(\\)]", "");
        if (!PHONE_PATTERN.matcher(cleanPhone).matches()) {
            throw new InvalidStudentDataException("Phone", "Contact number must be exactly 10 digits (e.g., 9876543210).");
        }
    }
}
