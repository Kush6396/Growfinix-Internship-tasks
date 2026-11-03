package com.sms.test;

import com.sms.exception.DuplicateStudentException;
import com.sms.exception.InvalidStudentDataException;
import com.sms.exception.StudentNotFoundException;
import com.sms.model.Student;
import com.sms.service.StudentManager;

import java.util.Comparator;
import java.util.List;

/**
 * Automated test suite generated and structured to test the Student Management System.
 * Covers positive, negative, edge-case, and boundary-value scenarios for ArrayList operations
 * and Exception Handling.
 */
public class StudentManagerTestSuite {

    private int passedTests = 0;
    private int failedTests = 0;

    public static void main(String[] args) {
        StudentManagerTestSuite suite = new StudentManagerTestSuite();
        suite.runAllTests();
    }

    public void runAllTests() {
        System.out.println("========================================================================");
        System.out.println("          🧪 RUNNING AI-GENERATED AUTOMATED TEST SUITE 🧪              ");
        System.out.println("========================================================================");

        // Group 1: Add & Retrieve Operations
        testAddStudentSuccess();
        testGetAllStudentsEncapsulation();

        // Group 2: Exception Handling - Duplicates & Not Found
        testDuplicateStudentException();
        testStudentNotFoundOnSearch();
        testStudentNotFoundOnDelete();
        testStudentNotFoundOnUpdate();

        // Group 3: Input Validation & Boundary Values
        testInvalidAgeBoundaries();
        testValidAgeBoundaries();
        testInvalidGpaBoundaries();
        testValidGpaBoundaries();
        testInvalidEmailValidation();
        testInvalidPhoneValidation();
        testEmptyNameValidation();

        // Group 4: Search & Filtering
        testSearchByNameCaseInsensitive();
        testSearchByCourse();
        testSearchByGpaRange();

        // Group 5: Update & Delete
        testUpdateStudentPartialFields();
        testDeleteStudentSuccess();

        // Group 6: Sorting & Analytics
        testSortingByGpaDescending();
        testAverageGpaAndExtremes();
        testGradeCalculation();

        System.out.println("========================================================================");
        System.out.printf("Test Execution Summary: %d PASSED, %d FAILED, Total: %d\n",
                passedTests, failedTests, (passedTests + failedTests));
        if (failedTests == 0) {
            System.out.println("🎉 ALL TEST CASES PASSED WITH 100% SUCCESS!");
        } else {
            System.out.println("❌ SOME TESTS FAILED. PLEASE REVIEW LOGS.");
        }
        System.out.println("========================================================================");
    }

    // --- Helper Assertion Methods ---
    private void assertTrue(String testName, boolean condition, String message) {
        if (condition) {
            System.out.printf("  [PASS] %-50s\n", testName);
            passedTests++;
        } else {
            System.err.printf("  [FAIL] %-50s -> Reason: %s\n", testName, message);
            failedTests++;
        }
    }

    private void assertEquals(String testName, Object expected, Object actual) {
        boolean match = (expected == null && actual == null) || (expected != null && expected.equals(actual));
        assertTrue(testName, match, "Expected: <" + expected + ">, but was: <" + actual + ">");
    }

    // --- Test Cases ---

    private void testAddStudentSuccess() {
        StudentManager manager = new StudentManager();
        try {
            Student s = new Student(1, "John Doe", 20, "Computer Science", 3.8, "john@test.com", "9876543210");
            manager.addStudent(s);
            assertEquals("testAddStudentSuccess - Count", 1, manager.getStudentCount());
            Student fetched = manager.searchById(1);
            assertEquals("testAddStudentSuccess - Name", "John Doe", fetched.getName());
        } catch (Exception e) {
            assertTrue("testAddStudentSuccess", false, "Unexpected exception: " + e.getMessage());
        }
    }

    private void testGetAllStudentsEncapsulation() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(1, "Test User", 20, "IT", 3.5, "user@test.com", "9876543210"));
            List<Student> list = manager.getAllStudents();
            try {
                list.clear(); // Attempting mutation on unmodifiable view
                assertTrue("testGetAllStudentsEncapsulation", false, "Should throw UnsupportedOperationException on mutation");
            } catch (UnsupportedOperationException e) {
                assertTrue("testGetAllStudentsEncapsulation", true, "");
            }
        } catch (Exception e) {
            assertTrue("testGetAllStudentsEncapsulation", false, e.getMessage());
        }
    }

    private void testDuplicateStudentException() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(10, "Student One", 21, "CS", 3.5, "s1@test.com", "9876543210"));
            manager.addStudent(new Student(10, "Duplicate ID", 22, "Mech", 3.2, "s2@test.com", "9876543211"));
            assertTrue("testDuplicateStudentException", false, "Expected DuplicateStudentException not thrown");
        } catch (DuplicateStudentException e) {
            assertTrue("testDuplicateStudentException", true, "");
        } catch (Exception e) {
            assertTrue("testDuplicateStudentException", false, "Wrong exception type: " + e.getClass().getSimpleName());
        }
    }

    private void testStudentNotFoundOnSearch() {
        StudentManager manager = new StudentManager();
        try {
            manager.searchById(999);
            assertTrue("testStudentNotFoundOnSearch", false, "Expected StudentNotFoundException not thrown");
        } catch (StudentNotFoundException e) {
            assertTrue("testStudentNotFoundOnSearch", true, "");
        } catch (Exception e) {
            assertTrue("testStudentNotFoundOnSearch", false, "Wrong exception: " + e.getMessage());
        }
    }

    private void testStudentNotFoundOnDelete() {
        StudentManager manager = new StudentManager();
        try {
            manager.deleteStudent(888);
            assertTrue("testStudentNotFoundOnDelete", false, "Expected StudentNotFoundException not thrown");
        } catch (StudentNotFoundException e) {
            assertTrue("testStudentNotFoundOnDelete", true, "");
        } catch (Exception e) {
            assertTrue("testStudentNotFoundOnDelete", false, "Wrong exception: " + e.getMessage());
        }
    }

    private void testStudentNotFoundOnUpdate() {
        StudentManager manager = new StudentManager();
        try {
            manager.updateStudent(777, "New Name", null, null, null, null, null);
            assertTrue("testStudentNotFoundOnUpdate", false, "Expected StudentNotFoundException not thrown");
        } catch (StudentNotFoundException e) {
            assertTrue("testStudentNotFoundOnUpdate", true, "");
        } catch (Exception e) {
            assertTrue("testStudentNotFoundOnUpdate", false, "Wrong exception: " + e.getMessage());
        }
    }

    private void testInvalidAgeBoundaries() {
        StudentManager manager = new StudentManager();
        // Age below 15
        try {
            manager.addStudent(new Student(11, "Too Young", 14, "CS", 3.5, "young@test.com", "9876543210"));
            assertTrue("testInvalidAgeBoundaries (Age 14)", false, "Expected InvalidStudentDataException");
        } catch (InvalidStudentDataException e) {
            assertTrue("testInvalidAgeBoundaries (Age 14)", true, "");
        } catch (Exception e) {
            assertTrue("testInvalidAgeBoundaries (Age 14)", false, e.getMessage());
        }

        // Age above 100
        try {
            manager.addStudent(new Student(12, "Too Old", 101, "CS", 3.5, "old@test.com", "9876543210"));
            assertTrue("testInvalidAgeBoundaries (Age 101)", false, "Expected InvalidStudentDataException");
        } catch (InvalidStudentDataException e) {
            assertTrue("testInvalidAgeBoundaries (Age 101)", true, "");
        } catch (Exception e) {
            assertTrue("testInvalidAgeBoundaries (Age 101)", false, e.getMessage());
        }
    }

    private void testValidAgeBoundaries() {
        StudentManager manager = new StudentManager();
        try {
            // Boundary: 15
            manager.addStudent(new Student(13, "Min Age", 15, "CS", 3.5, "min@test.com", "9876543210"));
            // Boundary: 100
            manager.addStudent(new Student(14, "Max Age", 100, "CS", 3.5, "max@test.com", "9876543210"));
            assertEquals("testValidAgeBoundaries", 2, manager.getStudentCount());
        } catch (Exception e) {
            assertTrue("testValidAgeBoundaries", false, "Valid age rejected: " + e.getMessage());
        }
    }

    private void testInvalidGpaBoundaries() {
        StudentManager manager = new StudentManager();
        // Negative GPA
        try {
            manager.addStudent(new Student(15, "Negative GPA", 20, "CS", -0.1, "neg@test.com", "9876543210"));
            assertTrue("testInvalidGpaBoundaries (GPA -0.1)", false, "Expected InvalidStudentDataException");
        } catch (InvalidStudentDataException e) {
            assertTrue("testInvalidGpaBoundaries (GPA -0.1)", true, "");
        } catch (Exception e) {
            assertTrue("testInvalidGpaBoundaries (GPA -0.1)", false, e.getMessage());
        }

        // GPA > 4.0
        try {
            manager.addStudent(new Student(16, "Exceed GPA", 20, "CS", 4.01, "high@test.com", "9876543210"));
            assertTrue("testInvalidGpaBoundaries (GPA 4.01)", false, "Expected InvalidStudentDataException");
        } catch (InvalidStudentDataException e) {
            assertTrue("testInvalidGpaBoundaries (GPA 4.01)", true, "");
        } catch (Exception e) {
            assertTrue("testInvalidGpaBoundaries (GPA 4.01)", false, e.getMessage());
        }
    }

    private void testValidGpaBoundaries() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(17, "Zero GPA", 20, "CS", 0.00, "zero@test.com", "9876543210"));
            manager.addStudent(new Student(18, "Perfect GPA", 20, "CS", 4.00, "perfect@test.com", "9876543210"));
            assertEquals("testValidGpaBoundaries", 2, manager.getStudentCount());
        } catch (Exception e) {
            assertTrue("testValidGpaBoundaries", false, "Valid GPA rejected: " + e.getMessage());
        }
    }

    private void testInvalidEmailValidation() {
        StudentManager manager = new StudentManager();
        String[] badEmails = {"plainaddress", "@missingusername.com", "user@.com.my", "user@site"};
        for (String bad : badEmails) {
            try {
                manager.addStudent(new Student(20, "Bad Email", 20, "CS", 3.0, bad, "9876543210"));
                assertTrue("testInvalidEmailValidation (" + bad + ")", false, "Should reject " + bad);
            } catch (InvalidStudentDataException e) {
                assertTrue("testInvalidEmailValidation (" + bad + ")", true, "");
            } catch (Exception e) {
                assertTrue("testInvalidEmailValidation (" + bad + ")", false, e.getMessage());
            }
        }
    }

    private void testInvalidPhoneValidation() {
        StudentManager manager = new StudentManager();
        String[] badPhones = {"123", "abcdefghij", "123456789012", ""};
        for (String bad : badPhones) {
            try {
                manager.addStudent(new Student(25, "Bad Phone", 20, "CS", 3.0, "p@test.com", bad));
                assertTrue("testInvalidPhoneValidation (" + bad + ")", false, "Should reject " + bad);
            } catch (InvalidStudentDataException e) {
                assertTrue("testInvalidPhoneValidation (" + bad + ")", true, "");
            } catch (Exception e) {
                assertTrue("testInvalidPhoneValidation (" + bad + ")", false, e.getMessage());
            }
        }
    }

    private void testEmptyNameValidation() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(30, "   ", 20, "CS", 3.0, "blank@test.com", "9876543210"));
            assertTrue("testEmptyNameValidation", false, "Should reject blank name");
        } catch (InvalidStudentDataException e) {
            assertTrue("testEmptyNameValidation", true, "");
        } catch (Exception e) {
            assertTrue("testEmptyNameValidation", false, e.getMessage());
        }
    }

    private void testSearchByNameCaseInsensitive() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(1, "Alice Wonderland", 20, "CS", 3.5, "alice@test.com", "9876543210"));
            manager.addStudent(new Student(2, "Bob Alice", 21, "IT", 3.2, "bob@test.com", "9876543211"));
            manager.addStudent(new Student(3, "Charlie Brown", 22, "Mech", 3.9, "charlie@test.com", "9876543212"));

            List<Student> results = manager.searchByName("ALICE");
            assertEquals("testSearchByNameCaseInsensitive - Count", 2, results.size());
        } catch (Exception e) {
            assertTrue("testSearchByNameCaseInsensitive", false, e.getMessage());
        }
    }

    private void testSearchByCourse() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(1, "Liam Neeson", 20, "Computer Science", 3.5, "liam@test.com", "9876543210"));
            manager.addStudent(new Student(2, "Maya Angelou", 21, "Mechanical Eng", 3.2, "maya@test.com", "9876543211"));
            manager.addStudent(new Student(3, "Noah Smith", 22, "Computer Science", 3.9, "noah@test.com", "9876543212"));

            List<Student> results = manager.searchByCourse("computer science");
            assertEquals("testSearchByCourse - Count", 2, results.size());
        } catch (Exception e) {
            assertTrue("testSearchByCourse", false, e.getMessage());
        }
    }

    private void testSearchByGpaRange() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(1, "Arthur Pendelton", 20, "CS", 2.5, "art@test.com", "9876543210"));
            manager.addStudent(new Student(2, "Brenda Vance", 21, "CS", 3.5, "brenda@test.com", "9876543211"));
            manager.addStudent(new Student(3, "Carlos Ray", 22, "CS", 3.8, "carlos@test.com", "9876543212"));

            List<Student> results = manager.searchByGpaRange(3.0, 4.0);
            assertEquals("testSearchByGpaRange - Count", 2, results.size());
        } catch (Exception e) {
            assertTrue("testSearchByGpaRange", false, e.getMessage());
        }
    }

    private void testUpdateStudentPartialFields() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(1, "Original Name", 20, "CS", 3.0, "orig@test.com", "9876543210"));
            // Update only GPA and Email
            manager.updateStudent(1, null, null, null, 3.9, "updated@test.com", null);

            Student updated = manager.searchById(1);
            assertEquals("testUpdateStudentPartialFields - GPA", 3.9, updated.getGpa());
            assertEquals("testUpdateStudentPartialFields - Email", "updated@test.com", updated.getEmail());
            assertEquals("testUpdateStudentPartialFields - Name Unchanged", "Original Name", updated.getName());
            assertEquals("testUpdateStudentPartialFields - Age Unchanged", 20, updated.getAge());
        } catch (Exception e) {
            assertTrue("testUpdateStudentPartialFields", false, e.getMessage());
        }
    }

    private void testDeleteStudentSuccess() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(1, "To Delete", 20, "CS", 3.0, "del@test.com", "9876543210"));
            assertEquals("testDeleteStudentSuccess - Count Before", 1, manager.getStudentCount());

            Student removed = manager.deleteStudent(1);
            assertEquals("testDeleteStudentSuccess - Removed ID", 1, removed.getId());
            assertEquals("testDeleteStudentSuccess - Count After", 0, manager.getStudentCount());
        } catch (Exception e) {
            assertTrue("testDeleteStudentSuccess", false, e.getMessage());
        }
    }

    private void testSortingByGpaDescending() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(1, "Low GPA", 20, "CS", 2.0, "low@test.com", "9876543210"));
            manager.addStudent(new Student(2, "High GPA", 21, "CS", 3.9, "high@test.com", "9876543211"));
            manager.addStudent(new Student(3, "Mid GPA", 22, "CS", 3.2, "mid@test.com", "9876543212"));

            manager.sortStudents(Comparator.comparingDouble(Student::getGpa).reversed());
            List<Student> list = manager.getAllStudents();
            assertEquals("testSortingByGpaDescending - Top", 3.9, list.get(0).getGpa());
            assertEquals("testSortingByGpaDescending - Mid", 3.2, list.get(1).getGpa());
            assertEquals("testSortingByGpaDescending - Last", 2.0, list.get(2).getGpa());
        } catch (Exception e) {
            assertTrue("testSortingByGpaDescending", false, e.getMessage());
        }
    }

    private void testAverageGpaAndExtremes() {
        StudentManager manager = new StudentManager();
        try {
            manager.addStudent(new Student(1, "Aaron Paul", 20, "CS", 3.0, "aaron@test.com", "9876543210"));
            manager.addStudent(new Student(2, "Betty White", 21, "CS", 4.0, "betty@test.com", "9876543211"));

            assertEquals("testAverageGpa", 3.5, manager.getAverageGpa());
            assertEquals("testHighestGpa", 4.0, manager.getHighestGpaStudent().get().getGpa());
            assertEquals("testLowestGpa", 3.0, manager.getLowestGpaStudent().get().getGpa());
        } catch (Exception e) {
            assertTrue("testAverageGpaAndExtremes", false, e.getMessage());
        }
    }

    private void testGradeCalculation() {
        Student s1 = new Student(1, "Student Alpha", 20, "CS", 3.85, "s1@test.com", "9876543210");
        Student s2 = new Student(2, "Student Bravo", 20, "CS", 3.40, "s2@test.com", "9876543211");
        Student s3 = new Student(3, "Student Charlie", 20, "CS", 3.10, "s3@test.com", "9876543212");
        Student s4 = new Student(4, "Student Delta", 20, "CS", 2.50, "s4@test.com", "9876543213");
        Student s5 = new Student(5, "Student Echo", 20, "CS", 1.50, "s5@test.com", "9876543214");
        Student s6 = new Student(6, "Student Foxtrot", 20, "CS", 0.80, "s6@test.com", "9876543215");

        assertEquals("Grade 3.85 -> A+", "A+", s1.getGrade());
        assertEquals("Grade 3.40 -> A", "A", s2.getGrade());
        assertEquals("Grade 3.10 -> B+", "B+", s3.getGrade());
        assertEquals("Grade 2.50 -> C", "C", s4.getGrade());
        assertEquals("Grade 1.50 -> D", "D", s5.getGrade());
        assertEquals("Grade 0.80 -> F", "F", s6.getGrade());
    }
}
