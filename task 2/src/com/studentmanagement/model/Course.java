package com.studentmanagement.model;

import java.util.Objects;

/**
 * Abstract class representing an academic course offering.
 * Demonstrates:
 *  - Encapsulation: Course code, capacity, and credit values are guarded by invariants.
 *  - Abstraction: Abstract methods define delivery format and fee calculation variations.
 *  - Association: Aggregates an Instructor object.
 */
public abstract class Course implements Displayable {
    private String courseCode;
    private String courseName;
    private int credits;
    private int maxCapacity;
    private int enrolledCount;
    private Instructor instructor;

    public Course(String courseCode, String courseName, int credits, int maxCapacity) {
        this(courseCode, courseName, credits, maxCapacity, null);
    }

    public Course(String courseCode, String courseName, int credits, int maxCapacity, Instructor instructor) {
        setCourseCode(courseCode);
        setCourseName(courseName);
        setCredits(credits);
        setMaxCapacity(maxCapacity);
        this.enrolledCount = 0;
        this.instructor = instructor;
    }

    // --- Abstract Methods (Abstraction) ---
    /**
     * Describes the delivery modality of the course (e.g. Online, In-Person, Hybrid).
     */
    public abstract String getDeliveryFormat();

    /**
     * Calculates total fees for this specific course offering based on modality and lab requirements.
     */
    public abstract double calculateCourseFees(double baseFeePerCredit);

    // --- Concrete Encapsulated Methods ---

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Course code cannot be blank.");
        }
        this.courseCode = courseCode.trim().toUpperCase();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be blank.");
        }
        this.courseName = courseName.trim();
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if (credits < 1 || credits > 6) {
            throw new IllegalArgumentException("Course credits must be between 1 and 6. Received: " + credits);
        }
        this.credits = credits;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException("Max capacity must be greater than zero. Received: " + maxCapacity);
        }
        this.maxCapacity = maxCapacity;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public boolean isFull() {
        return enrolledCount >= maxCapacity;
    }

    public void incrementEnrolledCount() {
        if (isFull()) {
            throw new IllegalStateException("Cannot increment: Course is already full.");
        }
        this.enrolledCount++;
    }

    public void decrementEnrolledCount() {
        if (this.enrolledCount > 0) {
            this.enrolledCount--;
        }
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public void displayDetails() {
        System.out.println(getDetails());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(courseCode, course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (%d cr) | Enrolled: %d/%d",
                courseCode, courseName, credits, enrolledCount, maxCapacity);
    }
}
