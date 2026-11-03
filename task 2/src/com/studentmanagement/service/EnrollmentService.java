package com.studentmanagement.service;

import com.studentmanagement.exception.CourseFullException;
import com.studentmanagement.exception.CreditLimitExceededException;
import com.studentmanagement.exception.DuplicateEnrollmentException;
import com.studentmanagement.model.Course;
import com.studentmanagement.model.Enrollment;
import com.studentmanagement.model.Grade;
import com.studentmanagement.model.Student;

/**
 * Service interface specifying enrollment management business operations.
 * Demonstrates the OOP concept of Abstraction and Interface Segregation.
 */
public interface EnrollmentService {
    /**
     * Enrolls a student into a designated course.
     *
     * @param student The student wishing to enroll.
     * @param course  The course being requested.
     * @return Newly formed Enrollment record.
     * @throws CourseFullException          if course capacity is exhausted.
     * @throws DuplicateEnrollmentException if student is already registered.
     * @throws CreditLimitExceededException if enrollment breaches student credit limit.
     */
    Enrollment enrollStudent(Student student, Course course)
            throws CourseFullException, DuplicateEnrollmentException, CreditLimitExceededException;

    /**
     * Drops a student from an enrolled course and updates seat count.
     *
     * @param student The student.
     * @param course  The course to drop.
     * @return true if dropped successfully, false otherwise.
     */
    boolean dropStudent(Student student, Course course);

    /**
     * Records a letter grade for a student in a course.
     *
     * @param student The student.
     * @param course  The course.
     * @param grade   The letter grade to assign.
     * @return true if updated, false if enrollment not found.
     */
    boolean recordGrade(Student student, Course course, Grade grade);

    /**
     * Overloaded method: Records a numeric score (0-100) which converts to a letter grade.
     * Demonstrates Static Polymorphism.
     *
     * @param student      The student.
     * @param courseCode   The course code.
     * @param numericScore The percentage score.
     * @return true if updated, false if enrollment not found.
     */
    boolean recordGrade(Student student, String courseCode, double numericScore);
}
