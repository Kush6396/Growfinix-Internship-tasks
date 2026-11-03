package com.studentmanagement.exception;

/**
 * Exception thrown when an enrollment attempt is made on a course that has
 * already reached its maximum student capacity.
 */
public class CourseFullException extends Exception {
    private final String courseCode;
    private final int capacity;

    public CourseFullException(String courseCode, int capacity) {
        super(String.format("Enrollment failed: Course '%s' has reached its maximum capacity of %d students.",
                courseCode, capacity));
        this.courseCode = courseCode;
        this.capacity = capacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getCapacity() {
        return capacity;
    }
}
