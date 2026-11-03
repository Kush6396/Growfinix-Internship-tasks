package com.studentmanagement.service;

import com.studentmanagement.exception.CourseFullException;
import com.studentmanagement.exception.CreditLimitExceededException;
import com.studentmanagement.exception.DuplicateEnrollmentException;
import com.studentmanagement.model.Course;
import com.studentmanagement.model.Enrollment;
import com.studentmanagement.model.Grade;
import com.studentmanagement.model.Student;

import java.util.Objects;

/**
 * Concrete implementation of the EnrollmentService interface.
 * Implements business logic and maintains integrity across Student and Course entities.
 */
public class EnrollmentServiceImpl implements EnrollmentService {

    @Override
    public Enrollment enrollStudent(Student student, Course course)
            throws CourseFullException, DuplicateEnrollmentException, CreditLimitExceededException {
        Objects.requireNonNull(student, "Student cannot be null.");
        Objects.requireNonNull(course, "Course cannot be null.");

        // Invariant 1: Check if course is already full
        if (course.isFull()) {
            throw new CourseFullException(course.getCourseCode(), course.getMaxCapacity());
        }

        // Invariant 2: Check for duplicate enrollment
        if (student.isEnrolledIn(course)) {
            throw new DuplicateEnrollmentException(student.getId(), course.getCourseCode());
        }

        // Invariant 3: Check student's polymorphic credit limits
        int currentCredits = student.getTotalEnrolledCredits();
        int attemptedCredits = course.getCredits();
        int maxCredits = student.getMaxAllowedCredits();

        if (currentCredits + attemptedCredits > maxCredits) {
            throw new CreditLimitExceededException(student.getId(), currentCredits, attemptedCredits, maxCredits);
        }

        // Execute enrollment
        Enrollment enrollment = student.enroll(course);
        course.incrementEnrolledCount();
        return enrollment;
    }

    @Override
    public boolean dropStudent(Student student, Course course) {
        if (student == null || course == null) return false;

        boolean removed = student.dropCourse(course);
        if (removed) {
            course.decrementEnrolledCount();
            return true;
        }
        return false;
    }

    @Override
    public boolean recordGrade(Student student, Course course, Grade grade) {
        if (student == null || course == null) return false;
        return student.assignGrade(course, grade);
    }

    @Override
    public boolean recordGrade(Student student, String courseCode, double numericScore) {
        if (student == null || courseCode == null) return false;
        return student.assignGrade(courseCode, numericScore);
    }
}
