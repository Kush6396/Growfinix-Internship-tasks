package com.studentmanagement.service;

import com.studentmanagement.exception.CourseFullException;
import com.studentmanagement.exception.CreditLimitExceededException;
import com.studentmanagement.exception.DuplicateEnrollmentException;
import com.studentmanagement.model.*;

import java.util.*;

/**
 * Registry component managing students, instructors, and courses.
 * Demonstrates:
 *  - Encapsulation: Internal registries stored in private collections.
 *  - Polymorphism: Operates on abstract types (Person, Student, Course) seamlessly.
 *  - Overloading: Multiple search and lookup signatures.
 */
public class UniversityRegistry {
    private final Map<Integer, Student> students = new LinkedHashMap<>();
    private final Map<String, Course> courses = new LinkedHashMap<>();
    private final Map<Integer, Instructor> instructors = new LinkedHashMap<>();
    private final EnrollmentService enrollmentService;

    public UniversityRegistry() {
        this.enrollmentService = new EnrollmentServiceImpl();
    }

    public EnrollmentService getEnrollmentService() {
        return enrollmentService;
    }

    // --- Entity Registration Methods ---

    public void registerStudent(Student student) {
        Objects.requireNonNull(student, "Student cannot be null.");
        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException("Student with ID " + student.getId() + " is already registered.");
        }
        students.put(student.getId(), student);
    }

    public void registerCourse(Course course) {
        Objects.requireNonNull(course, "Course cannot be null.");
        if (courses.containsKey(course.getCourseCode())) {
            throw new IllegalArgumentException("Course with code " + course.getCourseCode() + " already exists.");
        }
        courses.put(course.getCourseCode(), course);
    }

    public void registerInstructor(Instructor instructor) {
        Objects.requireNonNull(instructor, "Instructor cannot be null.");
        if (instructors.containsKey(instructor.getId())) {
            throw new IllegalArgumentException("Instructor with ID " + instructor.getId() + " is already registered.");
        }
        instructors.put(instructor.getId(), instructor);
    }

    // --- Overloaded Lookup Methods (Static Polymorphism) ---

    public Student findStudent(int id) {
        return students.get(id);
    }

    public Student findStudent(String email) {
        if (email == null) return null;
        for (Student s : students.values()) {
            if (s.getEmail().equalsIgnoreCase(email.trim())) {
                return s;
            }
        }
        return null;
    }

    public List<Student> findStudentsByName(String query) {
        List<Student> results = new ArrayList<>();
        if (query == null) return results;
        String q = query.trim().toLowerCase();
        for (Student s : students.values()) {
            if (s.getFullName().toLowerCase().contains(q)) {
                results.add(s);
            }
        }
        return results;
    }

    public Course findCourse(String courseCode) {
        if (courseCode == null) return null;
        return courses.get(courseCode.trim().toUpperCase());
    }

    public Instructor findInstructor(int id) {
        return instructors.get(id);
    }

    // --- Accessors returning unmodifiable views (Encapsulation) ---

    public Collection<Student> getAllStudents() {
        return Collections.unmodifiableCollection(students.values());
    }

    public Collection<Course> getAllCourses() {
        return Collections.unmodifiableCollection(courses.values());
    }

    public Collection<Instructor> getAllInstructors() {
        return Collections.unmodifiableCollection(instructors.values());
    }

    // --- Business / Reporting Operations (Runtime Polymorphism) ---

    /**
     * Demonstrates Dynamic Polymorphism: Calculates total tuition by invoking
     * calculateTuition() on each Student reference without knowing whether it is
     * Undergraduate or Graduate at compile time.
     */
    public double calculateTotalTuitionRevenue(double baseRatePerCredit) {
        double total = 0.0;
        for (Student student : students.values()) {
            total += student.calculateTuition(baseRatePerCredit);
        }
        return total;
    }

    /**
     * Seeds initial realistic university data for quick demonstration.
     */
    public void seedInitialData() {
        // 1. Create Instructors
        Instructor profSmith = new Instructor(101, "Alan", "Turing", "alan.turing@university.edu",
                "Computer Science", "Alan Hall 301", 110000);
        Instructor profCurie = new Instructor(102, "Marie", "Curie", "marie.curie@university.edu",
                "Physics", "Science Complex 105", 115000);
        Instructor profLovelace = new Instructor(103, "Ada", "Lovelace", "ada.lovelace@university.edu",
                "Software Engineering", "Tech Wing 204", 105000);

        registerInstructor(profSmith);
        registerInstructor(profCurie);
        registerInstructor(profLovelace);

        // 2. Create Courses (Both In-Person and Online)
        InPersonCourse cs101 = new InPersonCourse("CS101", "Introduction to OOP in Java",
                4, 3, profSmith, "Turing Labs", "Lab 2B", 75.0);
        OnlineCourse cs201 = new OnlineCourse("CS201", "Data Structures & Algorithms",
                4, 2, profLovelace, "Canvas & Zoom", "https://canvas.edu/cs201", true);
        InPersonCourse phy301 = new InPersonCourse("PHY301", "Quantum Mechanics & Computing",
                3, 20, profCurie, "Curie Science Hall", "Room 401", 120.0);
        OnlineCourse ai401 = new OnlineCourse("AI401", "Applied Artificial Intelligence",
                3, 15, profSmith, "Microsoft Teams", "https://teams.microsoft.com/ai401", true);

        registerCourse(cs101);
        registerCourse(cs201);
        registerCourse(phy301);
        registerCourse(ai401);

        // 3. Create Students (Both Undergraduate and Graduate)
        UndergraduateStudent alice = new UndergraduateStudent(1001, "Alice", "Johnson",
                "alice.j@university.edu", "Sophomore", "Computer Science", "Mathematics", true);
        UndergraduateStudent bob = new UndergraduateStudent(1002, "Bob", "Williams",
                "bob.w@university.edu", "Freshman", "Software Engineering");
        GraduateStudent charlie = new GraduateStudent(2001, "Charlie", "Davis",
                "charlie.d@university.edu", "Graduate Year 2", "Distributed Systems & Cloud",
                profSmith, 1200.0);
        GraduateStudent diana = new GraduateStudent(2002, "Diana", "Prince",
                "diana.p@university.edu", "Graduate Year 1", "Quantum Algorithms",
                profCurie, 800.0);

        registerStudent(alice);
        registerStudent(bob);
        registerStudent(charlie);
        registerStudent(diana);

        // 4. Enroll Students into Courses and assign initial grades
        try {
            // Alice enrolls in CS101 and CS201
            enrollmentService.enrollStudent(alice, cs101);
            enrollmentService.recordGrade(alice, "CS101", 94.5); // Grade A

            enrollmentService.enrollStudent(alice, cs201);
            enrollmentService.recordGrade(alice, cs201, Grade.B); // Grade B

            // Bob enrolls in CS101
            enrollmentService.enrollStudent(bob, cs101);
            enrollmentService.recordGrade(bob, "CS101", 82.0); // Grade B

            // Charlie (Grad) enrolls in CS201 and AI401
            enrollmentService.enrollStudent(charlie, cs201);
            enrollmentService.recordGrade(charlie, "CS201", 98.0); // Grade A

            enrollmentService.enrollStudent(charlie, ai401);
            // AI401 is in progress

            // Diana (Grad) enrolls in PHY301 and AI401
            enrollmentService.enrollStudent(diana, phy301);
            enrollmentService.recordGrade(diana, "PHY301", 91.0); // Grade A

            enrollmentService.enrollStudent(diana, ai401);
        } catch (CourseFullException | DuplicateEnrollmentException | CreditLimitExceededException e) {
            System.err.println("Seeding warning: " + e.getMessage());
        }
    }
}
