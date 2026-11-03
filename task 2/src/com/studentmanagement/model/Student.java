package com.studentmanagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class representing a generic Student.
 * Extends Person to inherit identity and contact fields.
 * Demonstrates:
 *  - Inheritance: Subclasses Person and delegates to super constructors.
 *  - Encapsulation: Protects internal enrollments collection with defensive copies.
 *  - Polymorphism: Declares abstract methods for credit limits and tuition calculation.
 *  - Method Overloading (Static Polymorphism): Overloaded enroll and assignGrade methods.
 */
public abstract class Student extends Person {
    private String academicYear;
    private final List<Enrollment> enrollments;

    public Student() {
        super();
        this.academicYear = "Freshman";
        this.enrollments = new ArrayList<>();
    }

    public Student(int id, String firstName, String lastName, String email, String academicYear) {
        super(id, firstName, lastName, email);
        setAcademicYear(academicYear);
        this.enrollments = new ArrayList<>();
    }

    public Student(int id, String firstName, String lastName, String email, String phoneNumber, String academicYear) {
        super(id, firstName, lastName, email, phoneNumber);
        setAcademicYear(academicYear);
        this.enrollments = new ArrayList<>();
    }

    // --- Abstract Methods (Runtime Polymorphism) ---
    /**
     * Returns the maximum credits this category of student is allowed to register for in a term.
     */
    public abstract int getMaxAllowedCredits();

    /**
     * Calculates the student's tuition based on enrolled credits and program-specific rules.
     */
    public abstract double calculateTuition(double baseRatePerCredit);

    // --- Encapsulation: Defensive access to collections ---

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("Academic year cannot be blank.");
        }
        this.academicYear = academicYear.trim();
    }

    /**
     * Encapsulation: Returns an unmodifiable view of enrollments to prevent unauthorized external mutation.
     */
    public List<Enrollment> getEnrollments() {
        return Collections.unmodifiableList(enrollments);
    }

    public int getTotalEnrolledCredits() {
        int total = 0;
        for (Enrollment enrollment : enrollments) {
            total += enrollment.getCourse().getCredits();
        }
        return total;
    }

    public boolean isEnrolledIn(Course course) {
        if (course == null) return false;
        for (Enrollment e : enrollments) {
            if (e.getCourse().equals(course)) {
                return true;
            }
        }
        return false;
    }

    // --- Method Overloading (Compile-Time / Static Polymorphism) ---

    /**
     * Overloaded method 1: Enroll with course object.
     */
    public Enrollment enroll(Course course) {
        return enroll(course, Grade.IN_PROGRESS);
    }

    /**
     * Overloaded method 2: Enroll with course object and initial grade.
     */
    public Enrollment enroll(Course course, Grade initialGrade) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null.");
        }
        if (isEnrolledIn(course)) {
            throw new IllegalStateException("Student is already enrolled in " + course.getCourseCode());
        }
        if (getTotalEnrolledCredits() + course.getCredits() > getMaxAllowedCredits()) {
            throw new IllegalStateException(String.format(
                    "Credit limit exceeded. Enrolled: %d, Adding: %d, Max Allowed: %d",
                    getTotalEnrolledCredits(), course.getCredits(), getMaxAllowedCredits()));
        }

        String enrollmentId = "ENR-" + getId() + "-" + course.getCourseCode();
        Enrollment enrollment = new Enrollment(enrollmentId, this, course, LocalDate.now());
        enrollment.setGrade(initialGrade);
        enrollments.add(enrollment);
        return enrollment;
    }

    /**
     * Overloaded method 1 for grading: By Course object and Grade enum.
     */
    public boolean assignGrade(Course course, Grade grade) {
        if (course == null) return false;
        return assignGrade(course.getCourseCode(), grade);
    }

    /**
     * Overloaded method 2 for grading: By Course code string and Grade enum.
     */
    public boolean assignGrade(String courseCode, Grade grade) {
        for (Enrollment e : enrollments) {
            if (e.getCourse().getCourseCode().equalsIgnoreCase(courseCode)) {
                e.setGrade(grade);
                return true;
            }
        }
        return false;
    }

    /**
     * Overloaded method 3 for grading: By Course code string and numeric score (0-100).
     */
    public boolean assignGrade(String courseCode, double numericScore) {
        for (Enrollment e : enrollments) {
            if (e.getCourse().getCourseCode().equalsIgnoreCase(courseCode)) {
                e.setNumericScore(numericScore);
                return true;
            }
        }
        return false;
    }

    public boolean dropCourse(Course course) {
        if (course == null) return false;
        Enrollment toRemove = null;
        for (Enrollment e : enrollments) {
            if (e.getCourse().equals(course)) {
                toRemove = e;
                break;
            }
        }
        if (toRemove != null) {
            enrollments.remove(toRemove);
            return true;
        }
        return false;
    }

    /**
     * Calculates the Cumulative Grade Point Average (GPA) across completed courses.
     *
     * @return GPA on a 4.0 scale, or 0.0 if no completed courses.
     */
    public double calculateGPA() {
        double totalWeightedPoints = 0.0;
        int completedCredits = 0;

        for (Enrollment e : enrollments) {
            if (e.getGrade().isCompleted()) {
                int credits = e.getCourse().getCredits();
                totalWeightedPoints += (e.getGrade().getGradePoint() * credits);
                completedCredits += credits;
            }
        }

        if (completedCredits == 0) {
            return 0.0;
        }
        return totalWeightedPoints / completedCredits;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String getDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("[%s] ID: %d | %s | Year: %s | GPA: %.2f | Credits: %d/%d\n",
                getRole(), getId(), getFullName(), academicYear, calculateGPA(),
                getTotalEnrolledCredits(), getMaxAllowedCredits()));
        sb.append("  Email: ").append(getEmail()).append(" | Phone: ").append(getPhoneNumber()).append("\n");
        sb.append("  Enrolled Courses (").append(enrollments.size()).append("):\n");
        if (enrollments.isEmpty()) {
            sb.append("    (No courses currently enrolled)\n");
        } else {
            for (Enrollment e : enrollments) {
                sb.append("  ").append(e.getDetails()).append("\n");
            }
        }
        return sb.toString();
    }
}
