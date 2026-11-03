package com.studentmanagement.exception;

/**
 * Exception thrown when attempting to enroll a student in a course they are
 * already actively enrolled in.
 */
public class DuplicateEnrollmentException extends Exception {
    private final int studentId;
    private final String courseCode;

    public DuplicateEnrollmentException(int studentId, String courseCode) {
        super(String.format("Enrollment failed: Student with ID %d is already enrolled in course '%s'.",
                studentId, courseCode));
        this.studentId = studentId;
        this.courseCode = courseCode;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }
}
