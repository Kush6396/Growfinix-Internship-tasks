package com.sms.service;

import com.sms.exception.DuplicateStudentException;
import com.sms.exception.InvalidStudentDataException;
import com.sms.exception.StudentNotFoundException;
import com.sms.model.Student;
import com.sms.util.InputValidator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Service class managing student records stored in an in-memory ArrayList.
 * Implements business logic, validation, duplicate prevention, and search/sort operations.
 */
public class StudentManager {

    // Primary data store as required: ArrayList
    private final List<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    /**
     * Adds a new student record to the system after validation and duplicate check.
     *
     * @param student The student object to add
     * @throws DuplicateStudentException   if a student with the same ID already exists
     * @throws InvalidStudentDataException if student data violates validation rules
     */
    public void addStudent(Student student) throws DuplicateStudentException, InvalidStudentDataException {
        // Validate student entity data
        InputValidator.validateStudent(student);

        // Check for duplicate ID
        if (existsById(student.getId())) {
            throw new DuplicateStudentException(student.getId());
        }

        students.add(student);
    }

    /**
     * Retrieves an unmodifiable view of all student records.
     *
     * @return List of all students
     */
    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(new ArrayList<>(students));
    }

    /**
     * Gets the total count of registered students.
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     * Checks if a student with the specified ID already exists.
     */
    public boolean existsById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for a student by their unique ID.
     *
     * @param id Student ID
     * @return The found Student object
     * @throws StudentNotFoundException if no student has the given ID
     */
    public Student searchById(int id) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new StudentNotFoundException(id);
    }

    /**
     * Searches for students whose name contains the search keyword (case-insensitive).
     *
     * @param nameQuery Keyword to match in student name
     * @return List of matching students
     */
    public List<Student> searchByName(String nameQuery) {
        if (nameQuery == null || nameQuery.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String queryLower = nameQuery.trim().toLowerCase();
        List<Student> results = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(queryLower)) {
                results.add(s);
            }
        }
        return results;
    }

    /**
     * Searches for students by course name (case-insensitive).
     *
     * @param courseQuery Keyword to match in course name
     * @return List of matching students
     */
    public List<Student> searchByCourse(String courseQuery) {
        if (courseQuery == null || courseQuery.trim().isEmpty()) {
            return Collections.emptyList();
        }
        String queryLower = courseQuery.trim().toLowerCase();
        List<Student> results = new ArrayList<>();
        for (Student s : students) {
            if (s.getCourse().toLowerCase().contains(queryLower)) {
                results.add(s);
            }
        }
        return results;
    }

    /**
     * Searches for students whose GPA is within [minGpa, maxGpa].
     */
    public List<Student> searchByGpaRange(double minGpa, double maxGpa) {
        List<Student> results = new ArrayList<>();
        for (Student s : students) {
            if (s.getGpa() >= minGpa && s.getGpa() <= maxGpa) {
                results.add(s);
            }
        }
        return results;
    }

    /**
     * Updates an existing student's details. Only non-null / non-empty values are updated.
     *
     * @param id      The ID of the student to update
     * @param name    New name (optional)
     * @param age     New age (optional)
     * @param course  New course (optional)
     * @param gpa     New GPA (optional)
     * @param email   New email (optional)
     * @param contact New contact number (optional)
     * @throws StudentNotFoundException    if student with ID doesn't exist
     * @throws InvalidStudentDataException if updated values fail validation
     */
    public Student updateStudent(int id, String name, Integer age, String course, Double gpa, String email, String contact)
            throws StudentNotFoundException, InvalidStudentDataException {
        Student student = searchById(id);

        if (name != null && !name.trim().isEmpty()) {
            InputValidator.validateName(name);
            student.setName(name.trim());
        }
        if (age != null) {
            InputValidator.validateAge(age);
            student.setAge(age);
        }
        if (course != null && !course.trim().isEmpty()) {
            InputValidator.validateCourse(course);
            student.setCourse(course.trim());
        }
        if (gpa != null) {
            InputValidator.validateGpa(gpa);
            student.setGpa(gpa);
        }
        if (email != null && !email.trim().isEmpty()) {
            InputValidator.validateEmail(email);
            student.setEmail(email.trim());
        }
        if (contact != null && !contact.trim().isEmpty()) {
            InputValidator.validatePhone(contact);
            student.setContactNumber(contact.trim());
        }

        return student;
    }

    /**
     * Deletes a student record by ID.
     *
     * @param id Student ID to delete
     * @return The removed Student object
     * @throws StudentNotFoundException if student does not exist
     */
    public Student deleteStudent(int id) throws StudentNotFoundException {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId() == id) {
                iterator.remove();
                return s;
            }
        }
        throw new StudentNotFoundException(id);
    }

    /**
     * Clears all student records.
     */
    public void clearAllStudents() {
        students.clear();
    }

    /**
     * Sorts the student records using the provided Comparator.
     *
     * @param comparator Comparator to sort by
     */
    public void sortStudents(Comparator<Student> comparator) {
        students.sort(comparator);
    }

    /**
     * Calculates the average GPA of all students.
     */
    public double getAverageGpa() {
        if (students.isEmpty()) return 0.0;
        double sum = 0.0;
        for (Student s : students) {
            sum += s.getGpa();
        }
        return sum / students.size();
    }

    /**
     * Finds the student with the highest GPA.
     */
    public Optional<Student> getHighestGpaStudent() {
        return students.stream().max(Comparator.comparingDouble(Student::getGpa));
    }

    /**
     * Finds the student with the lowest GPA.
     */
    public Optional<Student> getLowestGpaStudent() {
        return students.stream().min(Comparator.comparingDouble(Student::getGpa));
    }

    /**
     * Computes the distribution of grades across students.
     */
    public Map<String, Long> getGradeDistribution() {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getGrade, Collectors.counting()));
    }

    /**
     * Computes the count of students per course.
     */
    public Map<String, Long> getCourseDistribution() {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getCourse, Collectors.counting()));
    }

    /**
     * Populates the system with realistic sample student records for demonstration and testing.
     */
    public void loadSampleData() {
        try {
            if (!existsById(101)) {
                addStudent(new Student(101, "Alice Johnson", 20, "Computer Science", 3.85, "alice.j@university.edu", "9876543210"));
            }
            if (!existsById(102)) {
                addStudent(new Student(102, "Bob Williams", 22, "Mechanical Eng", 3.20, "bob.w@university.edu", "9123456780"));
            }
            if (!existsById(103)) {
                addStudent(new Student(103, "Catherine Davis", 21, "Data Science", 3.95, "cathy.d@university.edu", "9988776655"));
            }
            if (!existsById(104)) {
                addStudent(new Student(104, "David Miller", 19, "Computer Science", 2.80, "david.m@university.edu", "9011223344"));
            }
            if (!existsById(105)) {
                addStudent(new Student(105, "Emma Wilson", 23, "Electrical Eng", 3.60, "emma.w@university.edu", "9765432109"));
            }
        } catch (DuplicateStudentException | InvalidStudentDataException e) {
            // Should not occur for verified sample data
            System.err.println("Sample data load error: " + e.getMessage());
        }
    }
}
