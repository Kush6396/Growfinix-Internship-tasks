package com.studentmanagement.model;

/**
 * Concrete class representing a university faculty instructor.
 * Demonstrates:
 *  - Inheritance: Inherits common personal attributes from Person.
 *  - Polymorphism: Overrides abstract getRole() and getDetails() methods.
 */
public class Instructor extends Person {
    private String department;
    private String officeRoom;
    private double salary;

    public Instructor(int id, String firstName, String lastName, String email, String department, String officeRoom, double salary) {
        super(id, firstName, lastName, email);
        setDepartment(department);
        setOfficeRoom(officeRoom);
        setSalary(salary);
    }

    public Instructor(int id, String firstName, String lastName, String email, String department) {
        this(id, firstName, lastName, email, department, "TBA", 75000.0);
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null || department.trim().isEmpty()) {
            throw new IllegalArgumentException("Department cannot be null or empty.");
        }
        this.department = department.trim();
    }

    public String getOfficeRoom() {
        return officeRoom;
    }

    public void setOfficeRoom(String officeRoom) {
        this.officeRoom = officeRoom != null ? officeRoom.trim() : "TBA";
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative.");
        }
        this.salary = salary;
    }

    @Override
    public String getRole() {
        return "Instructor";
    }

    @Override
    public String getDetails() {
        return String.format("Instructor [ID: %d] %s | Dept: %s | Office: %s | Email: %s",
                getId(), getFullName(), department, officeRoom, getEmail());
    }
}
