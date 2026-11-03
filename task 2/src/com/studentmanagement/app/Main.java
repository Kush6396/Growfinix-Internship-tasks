package com.studentmanagement.app;

import com.studentmanagement.exception.CourseFullException;
import com.studentmanagement.exception.CreditLimitExceededException;
import com.studentmanagement.exception.DuplicateEnrollmentException;
import com.studentmanagement.model.*;
import com.studentmanagement.service.UniversityRegistry;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

/**
 * Main application class providing:
 * 1. An automated OOP Concepts Showcase highlighting Classes, Objects, Constructors,
 *    Encapsulation, Inheritance, Polymorphism, Abstraction, and Exception Handling.
 * 2. An interactive console menu for hands-on exploration.
 */
public class Main {
    private static final double BASE_TUITION_RATE_PER_CREDIT = 300.0;
    private final UniversityRegistry registry;
    private final Scanner scanner;

    public Main() {
        this.registry = new UniversityRegistry();
        this.registry.seedInitialData();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Main app = new Main();
        // If run with '--demo' argument, run demo directly
        if (args.length > 0 && args[0].equalsIgnoreCase("--demo")) {
            app.runAutomatedOopShowcase();
            return;
        }
        app.runInteractiveMenu();
    }

    /**
     * Interactive console loop for user input.
     */
    public void runInteractiveMenu() {
        boolean exit = false;
        System.out.println("==================================================================");
        System.out.println("  WELCOME TO UNIVERSITY OOP STUDENT & COURSE MANAGEMENT SYSTEM   ");
        System.out.println("==================================================================");

        while (!exit) {
            printMainMenu();
            System.out.print("Select an option [1-9]: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    runAutomatedOopShowcase();
                    break;
                case "2":
                    viewAllStudents();
                    break;
                case "3":
                    viewAllCourses();
                    break;
                case "4":
                    viewAllInstructors();
                    break;
                case "5":
                    handleEnrollStudent();
                    break;
                case "6":
                    handleAssignGrade();
                    break;
                case "7":
                    handleTuitionSummary();
                    break;
                case "8":
                    handleSearchStudent();
                    break;
                case "9":
                    exit = true;
                    System.out.println("\nThank you for using the University Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose between 1 and 9.\n");
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n----------------------- MAIN MENU -----------------------");
        System.out.println(" [1] Run Automated OOP Demonstration (Complete Concept Showcase)");
        System.out.println(" [2] View All Students (Polymorphic Details & GPA)");
        System.out.println(" [3] View All Courses (Online & In-Person Offerings)");
        System.out.println(" [4] View All Instructors");
        System.out.println(" [5] Enroll Student into Course");
        System.out.println(" [6] Assign / Update Course Grade");
        System.out.println(" [7] View Tuition Breakdown (Polymorphism in Action)");
        System.out.println(" [8] Search Student (by ID or Name)");
        System.out.println(" [9] Exit");
        System.out.println("---------------------------------------------------------");
    }

    // ========================================================================
    // OOP DEMONSTRATION SHOWCASE
    // ========================================================================

    public void runAutomatedOopShowcase() {
        System.out.println("\n==================================================================");
        System.out.println("     OOP PRINCIPLES IN ACTION: DEEP DIVE SHOWCASE & VERIFICATION  ");
        System.out.println("==================================================================");

        demonstrateClassesAndObjects();
        demonstrateEncapsulation();
        demonstrateInheritanceAndConstructors();
        demonstratePolymorphism();
        demonstrateAbstractionAndInterfaces();
        demonstrateExceptionHandling();

        System.out.println("==================================================================");
        System.out.println("        END OF OOP DEMONSTRATION SHOWCASE                        ");
        System.out.println("==================================================================\n");
    }

    private void demonstrateClassesAndObjects() {
        System.out.println("\n>>> 1. CLASSES AND OBJECTS");
        System.out.println("Classes define state (fields) and behavior (methods). Objects are concrete instances.");

        Instructor inst = new Instructor(999, "Grace", "Hopper", "grace.hopper@univ.edu", "CS");
        Course course = new OnlineCourse("CS999", "Compilers", 4, 30, "Zoom");
        UndergraduateStudent student = new UndergraduateStudent(9001, "Emma", "Watson", "emma.w@univ.edu", "Senior", "CS");

        System.out.println("Created Instructor object : " + inst);
        System.out.println("Created Course object     : " + course);
        System.out.println("Created Student object    : " + student);
    }

    private void demonstrateEncapsulation() {
        System.out.println("\n>>> 2. ENCAPSULATION & DATA INTEGRITY");
        System.out.println("Fields are private. Mutators validate arguments to enforce business invariants.");

        UndergraduateStudent s = new UndergraduateStudent(9002, "Tom", "Riddle", "tom@univ.edu", "Junior", "Math");

        // Test invalid email
        try {
            System.out.print("Testing invalid email setting: ");
            s.setEmail("invalid-email-address");
        } catch (IllegalArgumentException e) {
            System.out.println("[BLOCKED by Encapsulation]: " + e.getMessage());
        }

        // Test defensive copying of collections
        try {
            System.out.print("Testing external tampering of student enrollments list: ");
            s.getEnrollments().add(null);
        } catch (UnsupportedOperationException e) {
            System.out.println("[BLOCKED by Defensive Copy]: Attempt to directly modify unmodifiable list rejected.");
        }
    }

    private void demonstrateInheritanceAndConstructors() {
        System.out.println("\n>>> 3. INHERITANCE & CONSTRUCTOR CHAINING");
        System.out.println("Hierarchy: Person (Base) -> Student -> UndergraduateStudent / GraduateStudent");
        System.out.println("           Person (Base) -> Instructor");
        System.out.println("Subclasses invoke super(...) to initialize base fields and this(...) for defaults.");

        UndergraduateStudent u = new UndergraduateStudent(1001, "Alice", "Johnson", "alice.j@univ.edu", "Sophomore", "CS");
        GraduateStudent g = new GraduateStudent(2001, "Charlie", "Davis", "charlie.d@univ.edu", "Grad Year 2", "AI");

        System.out.println("Undergraduate Role: " + u.getRole() + " (Inherits Person & Student)");
        System.out.println("Graduate Role     : " + g.getRole() + " (Inherits Person & Student)");
    }

    private void demonstratePolymorphism() {
        System.out.println("\n>>> 4. POLYMORPHISM (RUNTIME & COMPILE-TIME)");

        System.out.println("\n--- A. Dynamic / Runtime Polymorphism (Method Overriding) ---");
        System.out.println("Iterating through List<Student> with different concrete types at runtime:");

        List<Student> sampleStudents = List.of(
                registry.findStudent(1001), // Undergraduate (Alice)
                registry.findStudent(2001)  // Graduate (Charlie)
        );

        for (Student s : sampleStudents) {
            System.out.println("\n[Polymorphic Type: " + s.getClass().getSimpleName() + "]");
            System.out.printf("  Full Name          : %s\n", s.getFullName());
            System.out.printf("  Max Allowed Credits: %d credits (Overridden per student type)\n", s.getMaxAllowedCredits());
            System.out.printf("  Calculated Tuition : $%.2f (Base rate: $%.2f/credit)\n",
                    s.calculateTuition(BASE_TUITION_RATE_PER_CREDIT), BASE_TUITION_RATE_PER_CREDIT);
        }

        System.out.println("\nPolymorphic Course fee calculation:");
        for (Course c : registry.getAllCourses()) {
            System.out.printf("  [%s] Modality: %-35s Fee: $%.2f\n",
                    c.getCourseCode(), c.getDeliveryFormat(), c.calculateCourseFees(BASE_TUITION_RATE_PER_CREDIT));
        }

        System.out.println("\n--- B. Static / Compile-Time Polymorphism (Method Overloading) ---");
        System.out.println("Methods with same name but varying signatures:");
        System.out.println("  1. enroll(Course)");
        System.out.println("  2. enroll(Course, Grade)");
        System.out.println("  3. assignGrade(Course, Grade)");
        System.out.println("  4. assignGrade(String courseCode, Grade grade)");
        System.out.println("  5. assignGrade(String courseCode, double numericScore)");
        System.out.println("  6. registry.findStudent(int id) vs registry.findStudent(String email)");
    }

    private void demonstrateAbstractionAndInterfaces() {
        System.out.println("\n>>> 5. ABSTRACTION & INTERFACES");
        System.out.println("1. Interface 'Displayable' contracts 'displayDetails()' across disparate classes.");
        System.out.println("2. Interface 'EnrollmentService' abstracts enrollment business operations.");
        System.out.println("3. Abstract classes 'Person' and 'Course' cannot be directly instantiated.");

        Displayable item = registry.findCourse("CS101");
        System.out.println("\nCalling displayDetails() via Displayable interface reference:");
        item.displayDetails();
    }

    private void demonstrateExceptionHandling() {
        System.out.println("\n>>> 6. CUSTOM EXCEPTION HANDLING & DOMAIN VALIDATION");

        // Scenario 1: Duplicate Enrollment
        System.out.println("\nCase A: Attempting duplicate enrollment for Alice in CS101:");
        try {
            Student alice = registry.findStudent(1001);
            Course cs101 = registry.findCourse("CS101");
            registry.getEnrollmentService().enrollStudent(alice, cs101);
        } catch (DuplicateEnrollmentException e) {
            System.out.println("  [CAUGHT EXPECTED EXCEPTION]: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  Unexpected: " + e.getMessage());
        }

        // Scenario 2: Course Full Exception
        System.out.println("\nCase B: Attempting enrollment into full course CS201 (Capacity: 2):");
        try {
            Course cs201 = registry.findCourse("CS201");
            Student bob = registry.findStudent(1002);
            // Bob tries to enroll in CS201 which already has Alice and Charlie (2/2)
            registry.getEnrollmentService().enrollStudent(bob, cs201);
        } catch (CourseFullException e) {
            System.out.println("  [CAUGHT EXPECTED EXCEPTION]: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  Unexpected: " + e.getMessage());
        }

        // Scenario 3: Credit Limit Exceeded Exception
        System.out.println("\nCase C: Attempting enrollment exceeding Graduate student credit limit (12 credits):");
        try {
            GraduateStudent grad = new GraduateStudent(9099, "Overachiever", "Student", "over@univ.edu", "Grad 1", "AI");
            Course c1 = new OnlineCourse("BIG1", "Course 1", 5, 50, "Canvas");
            Course c2 = new OnlineCourse("BIG2", "Course 2", 5, 50, "Canvas");
            Course c3 = new OnlineCourse("BIG3", "Course 3", 4, 50, "Canvas");

            registry.getEnrollmentService().enrollStudent(grad, c1); // 5 credits
            registry.getEnrollmentService().enrollStudent(grad, c2); // 10 credits
            registry.getEnrollmentService().enrollStudent(grad, c3); // 10 + 4 = 14 > 12 -> FAILS
        } catch (CreditLimitExceededException e) {
            System.out.println("  [CAUGHT EXPECTED EXCEPTION]: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("  Unexpected: " + e.getMessage());
        }
    }

    // ========================================================================
    // INTERACTIVE HANDLERS
    // ========================================================================

    private void viewAllStudents() {
        System.out.println("\n================ REGISTERED STUDENTS ================");
        Collection<Student> students = registry.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : students) {
            s.displayDetails();
        }
    }

    private void viewAllCourses() {
        System.out.println("\n================ REGISTERED COURSES ================");
        Collection<Course> courses = registry.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
            return;
        }
        for (Course c : courses) {
            c.displayDetails();
            System.out.println();
        }
    }

    private void viewAllInstructors() {
        System.out.println("\n================ REGISTERED INSTRUCTORS ================");
        Collection<Instructor> instructors = registry.getAllInstructors();
        if (instructors.isEmpty()) {
            System.out.println("No instructors found.");
            return;
        }
        for (Instructor inst : instructors) {
            inst.displayDetails();
        }
    }

    private void handleEnrollStudent() {
        System.out.println("\n--- Enroll Student into Course ---");
        System.out.print("Enter Student ID: ");
        String idInput = scanner.nextLine().trim();

        int studentId;
        try {
            studentId = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("Error: Student ID must be an integer.");
            return;
        }

        Student student = registry.findStudent(studentId);
        if (student == null) {
            System.out.println("Error: No student found with ID " + studentId);
            return;
        }

        System.out.print("Enter Course Code (e.g., CS101, PHY301): ");
        String courseCode = scanner.nextLine().trim().toUpperCase();

        Course course = registry.findCourse(courseCode);
        if (course == null) {
            System.out.println("Error: No course found with code " + courseCode);
            return;
        }

        try {
            Enrollment enrollment = registry.getEnrollmentService().enrollStudent(student, course);
            System.out.println("SUCCESS! Enrolled " + student.getFullName() + " in " + course.getCourseName());
            System.out.println("Enrollment ID: " + enrollment.getEnrollmentId());
            System.out.println("Student's updated credit load: " + student.getTotalEnrolledCredits() + "/" + student.getMaxAllowedCredits());
        } catch (CourseFullException | DuplicateEnrollmentException | CreditLimitExceededException e) {
            System.out.println("FAILED: " + e.getMessage());
        }
    }

    private void handleAssignGrade() {
        System.out.println("\n--- Assign / Update Course Grade ---");
        System.out.print("Enter Student ID: ");
        String idInput = scanner.nextLine().trim();

        int studentId;
        try {
            studentId = Integer.parseInt(idInput);
        } catch (NumberFormatException e) {
            System.out.println("Error: Student ID must be an integer.");
            return;
        }

        Student student = registry.findStudent(studentId);
        if (student == null) {
            System.out.println("Error: No student found with ID " + studentId);
            return;
        }

        System.out.print("Enter Course Code (e.g., CS101): ");
        String courseCode = scanner.nextLine().trim().toUpperCase();

        Course course = registry.findCourse(courseCode);
        if (course == null || !student.isEnrolledIn(course)) {
            System.out.println("Error: Student is not enrolled in course " + courseCode);
            return;
        }

        System.out.print("Enter grading mode: [1] Numeric Percentage (0-100) or [2] Letter Grade (A,B,C,D,F): ");
        String mode = scanner.nextLine().trim();

        if ("1".equals(mode)) {
            System.out.print("Enter numeric score (0-100): ");
            try {
                double score = Double.parseDouble(scanner.nextLine().trim());
                boolean success = registry.getEnrollmentService().recordGrade(student, courseCode, score);
                if (success) {
                    System.out.printf("SUCCESS: Score %.1f recorded. Letter Grade: %s. New GPA: %.2f\n",
                            score, Grade.fromNumericScore(score), student.calculateGPA());
                } else {
                    System.out.println("Failed to update score.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid score: " + e.getMessage());
            }
        } else if ("2".equals(mode)) {
            System.out.print("Enter letter grade (A, B, C, D, F): ");
            String letter = scanner.nextLine().trim().toUpperCase();
            try {
                Grade grade = Grade.valueOf(letter);
                boolean success = registry.getEnrollmentService().recordGrade(student, course, grade);
                if (success) {
                    System.out.printf("SUCCESS: Grade %s assigned. New GPA: %.2f\n", grade, student.calculateGPA());
                } else {
                    System.out.println("Failed to update grade.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid letter grade entered.");
            }
        } else {
            System.out.println("Invalid mode selected.");
        }
    }

    private void handleTuitionSummary() {
        System.out.println("\n================ STUDENT TUITION & FINANCIAL SUMMARY ================");
        System.out.printf("Base Tuition Rate: $%.2f per credit hour\n\n", BASE_TUITION_RATE_PER_CREDIT);

        for (Student s : registry.getAllStudents()) {
            double tuition = s.calculateTuition(BASE_TUITION_RATE_PER_CREDIT);
            System.out.printf("[%s] ID: %d | %-20s | Credits: %2d | Tuition Due: $%,9.2f\n",
                    s.getRole(), s.getId(), s.getFullName(), s.getTotalEnrolledCredits(), tuition);
        }

        double totalRevenue = registry.calculateTotalTuitionRevenue(BASE_TUITION_RATE_PER_CREDIT);
        System.out.println("---------------------------------------------------------------------");
        System.out.printf("TOTAL PROJECTED TUITION REVENUE: $%,.2f\n", totalRevenue);
        System.out.println("---------------------------------------------------------------------");
    }

    private void handleSearchStudent() {
        System.out.println("\n--- Search Student ---");
        System.out.print("Search by [1] ID or [2] Name: ");
        String mode = scanner.nextLine().trim();

        if ("1".equals(mode)) {
            System.out.print("Enter Student ID: ");
            try {
                int id = Integer.parseInt(scanner.nextLine().trim());
                Student s = registry.findStudent(id);
                if (s != null) {
                    s.displayDetails();
                } else {
                    System.out.println("No student found with ID " + id);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format.");
            }
        } else if ("2".equals(mode)) {
            System.out.print("Enter student name to search: ");
            String query = scanner.nextLine().trim();
            List<Student> results = registry.findStudentsByName(query);
            if (results.isEmpty()) {
                System.out.println("No students found matching '" + query + "'");
            } else {
                System.out.println("Found " + results.size() + " matching student(s):");
                for (Student s : results) {
                    s.displayDetails();
                }
            }
        } else {
            System.out.println("Invalid option.");
        }
    }
}
