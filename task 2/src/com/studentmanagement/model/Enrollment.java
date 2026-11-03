package com.studentmanagement.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Associative entity linking a Student and a Course.
 * Represents enrollment state, date, and academic performance (Grade/Score).
 * Demonstrates encapsulation and object association.
 */
public class Enrollment implements Displayable {
    private final String enrollmentId;
    private final Student student;
    private final Course course;
    private final LocalDate enrollmentDate;
    private Grade grade;
    private Double numericScore;

    public Enrollment(String enrollmentId, Student student, Course course, LocalDate enrollmentDate) {
        if (enrollmentId == null || enrollmentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Enrollment ID cannot be blank.");
        }
        this.enrollmentId = enrollmentId.trim();
        this.student = Objects.requireNonNull(student, "Student cannot be null.");
        this.course = Objects.requireNonNull(course, "Course cannot be null.");
        this.enrollmentDate = (enrollmentDate != null) ? enrollmentDate : LocalDate.now();
        this.grade = Grade.IN_PROGRESS;
        this.numericScore = null;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = (grade != null) ? grade : Grade.IN_PROGRESS;
    }

    public Double getNumericScore() {
        return numericScore;
    }

    public void setNumericScore(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100.");
        }
        this.numericScore = score;
        this.grade = Grade.fromNumericScore(score);
    }

    @Override
    public void displayDetails() {
        System.out.println(getDetails());
    }

    @Override
    public String getDetails() {
        String scoreStr = (numericScore != null) ? String.format("%.1f%%", numericScore) : "N/A";
        return String.format("  [%s] %s (%d cr) | Grade: %s | Score: %s | Enrolled: %s",
                course.getCourseCode(), course.getCourseName(), course.getCredits(),
                grade.name(), scoreStr, enrollmentDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enrollment)) return false;
        Enrollment that = (Enrollment) o;
        return Objects.equals(enrollmentId, that.enrollmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrollmentId);
    }

    @Override
    public String toString() {
        return String.format("Enrollment[%s: %s in %s]", enrollmentId, student.getFullName(), course.getCourseCode());
    }
}
