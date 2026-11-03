package com.sms.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Model class representing a Student entity in the Student Management System.
 */
public class Student implements Serializable, Comparable<Student> {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private int age;
    private String course;
    private double gpa;
    private String email;
    private String contactNumber;

    /**
     * Default constructor
     */
    public Student() {
    }

    /**
     * Parameterized constructor
     *
     * @param id            Unique student identifier
     * @param name          Student full name
     * @param age           Student age
     * @param course        Enrolled course / department
     * @param gpa           Grade Point Average (0.00 to 4.00)
     * @param email         Valid email address
     * @param contactNumber 10-digit phone number
     */
    public Student(int id, String name, int age, String course, double gpa, String email, String contactNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.gpa = gpa;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Computes the letter grade corresponding to the student's GPA.
     *
     * @return Letter grade (A, B, C, D, or F)
     */
    public String getGrade() {
        if (gpa >= 3.70) return "A+";
        if (gpa >= 3.30) return "A";
        if (gpa >= 3.00) return "B+";
        if (gpa >= 2.70) return "B";
        if (gpa >= 2.00) return "C";
        if (gpa >= 1.00) return "D";
        return "F";
    }

    /**
     * Formats the student information as a single row in an ASCII table.
     */
    public String toFormattedRow() {
        return String.format("| %-6d | %-20s | %-4d | %-22s | %-5.2f | %-5s | %-26s | %-12s |",
                id,
                truncate(name, 20),
                age,
                truncate(course, 22),
                gpa,
                getGrade(),
                truncate(email, 26),
                contactNumber);
    }

    private String truncate(String val, int maxLen) {
        if (val == null) return "";
        if (val.length() <= maxLen) return val;
        return val.substring(0, maxLen - 3) + "...";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public String toString() {
        return String.format(
                "Student ID   : %d\n" +
                "Name         : %s\n" +
                "Age          : %d\n" +
                "Course       : %s\n" +
                "GPA          : %.2f (Grade: %s)\n" +
                "Email        : %s\n" +
                "Contact      : %s",
                id, name, age, course, gpa, getGrade(), email, contactNumber
        );
    }
}
