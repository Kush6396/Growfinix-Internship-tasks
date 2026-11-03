package com.sms;

import com.sms.exception.DuplicateStudentException;
import com.sms.exception.InvalidStudentDataException;
import com.sms.exception.StudentNotFoundException;
import com.sms.model.Student;
import com.sms.service.StudentManager;
import com.sms.util.ConsoleInputReader;

import java.util.*;

/**
 * Console-based Student Management System entry point.
 * Provides an interactive menu, robust input handling, and formatted reporting.
 */
public class Main {

    private final StudentManager manager;
    private final ConsoleInputReader reader;

    public Main() {
        this.manager = new StudentManager();
        this.reader = new ConsoleInputReader(new Scanner(System.in));
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.run();
    }

    public void run() {
        printWelcomeBanner();

        boolean running = true;
        while (running) {
            try {
                printMainMenu();
                int choice = reader.readInt("Enter your choice (1-10): ");
                System.out.println();

                switch (choice) {
                    case 1:
                        handleAddStudent();
                        break;
                    case 2:
                        handleViewAllStudents();
                        break;
                    case 3:
                        handleSearchStudents();
                        break;
                    case 4:
                        handleUpdateStudent();
                        break;
                    case 5:
                        handleDeleteStudent();
                        break;
                    case 6:
                        handleViewAnalytics();
                        break;
                    case 7:
                        handleSortStudents();
                        break;
                    case 8:
                        handleLoadSampleData();
                        break;
                    case 9:
                        handleClearAll();
                        break;
                    case 10:
                        running = false;
                        printGoodbye();
                        break;
                    default:
                        System.out.println("❌ Invalid choice! Please select an option between 1 and 10.");
                }
            } catch (Exception e) {
                System.out.println("⚠️ An unexpected error occurred: " + e.getMessage());
            }

            if (running) {
                System.out.println();
                reader.readOptionalString("Press [Enter] to return to the main menu...");
                System.out.println();
            }
        }
    }

    private void printWelcomeBanner() {
        System.out.println("==========================================================================================");
        System.out.println("                         🎓 STUDENT MANAGEMENT SYSTEM (SMS) 🎓                            ");
        System.out.println("            Engineered with Java Collections Framework, ArrayList & Clean OOP            ");
        System.out.println("==========================================================================================");
        System.out.println();
    }

    private void printMainMenu() {
        System.out.println("---------------------------------- MAIN MENU ----------------------------------");
        System.out.println("  [1] Add New Student Record");
        System.out.println("  [2] View All Student Records");
        System.out.println("  [3] Search Student Records (by ID, Name, Course, GPA)");
        System.out.println("  [4] Update Existing Student Record");
        System.out.println("  [5] Delete Student Record");
        System.out.println("  [6] View Performance Analytics & Statistics");
        System.out.println("  [7] Sort Student Records");
        System.out.println("  [8] Load Sample Student Data");
        System.out.println("  [9] Clear All Records");
        System.out.println("  [10] Exit System");
        System.out.println("-------------------------------------------------------------------------------");
    }

    private void handleAddStudent() {
        System.out.println(">>> ADD NEW STUDENT RECORD <<<");
        try {
            int id = reader.readInt("Enter Student ID (positive integer): ");
            if (manager.existsById(id)) {
                System.out.printf("❌ Error: A student with ID %d already exists. Aborting addition.\n", id);
                return;
            }

            String name = reader.readString("Enter Full Name: ");
            int age = reader.readIntInRange("Enter Age (15 - 100): ", 15, 100);
            String course = reader.readString("Enter Course / Department: ");
            double gpa = reader.readDouble("Enter GPA (0.00 - 4.00): ");
            String email = reader.readString("Enter Email: ");
            String contact = reader.readString("Enter 10-digit Phone Number: ");

            Student student = new Student(id, name, age, course, gpa, email, contact);
            manager.addStudent(student);

            System.out.println();
            System.out.println("✅ Student record added successfully!");
            System.out.printf("   ID: %d | Name: %s | Grade: %s\n", student.getId(), student.getName(), student.getGrade());

        } catch (DuplicateStudentException e) {
            System.out.println("❌ Duplicate ID Error: " + e.getMessage());
        } catch (InvalidStudentDataException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        }
    }

    private void handleViewAllStudents() {
        System.out.println(">>> VIEW ALL STUDENT RECORDS <<<");
        List<Student> students = manager.getAllStudents();
        renderStudentTable(students);
    }

    private void handleSearchStudents() {
        System.out.println(">>> SEARCH STUDENT RECORDS <<<");
        System.out.println("  [1] Search by Student ID");
        System.out.println("  [2] Search by Name (contains keyword)");
        System.out.println("  [3] Search by Course");
        System.out.println("  [4] Search by GPA Range");
        int subChoice = reader.readIntInRange("Select search type (1-4): ", 1, 4);
        System.out.println();

        switch (subChoice) {
            case 1:
                int id = reader.readInt("Enter Student ID to find: ");
                try {
                    Student student = manager.searchById(id);
                    System.out.println("✅ Record Found:");
                    System.out.println("----------------------------------------");
                    System.out.println(student);
                    System.out.println("----------------------------------------");
                } catch (StudentNotFoundException e) {
                    System.out.println("❌ " + e.getMessage());
                }
                break;

            case 2:
                String query = reader.readString("Enter student name or partial keyword: ");
                List<Student> nameMatches = manager.searchByName(query);
                renderStudentTable(nameMatches);
                break;

            case 3:
                String courseQuery = reader.readString("Enter course or department name: ");
                List<Student> courseMatches = manager.searchByCourse(courseQuery);
                renderStudentTable(courseMatches);
                break;

            case 4:
                double minGpa = reader.readDouble("Enter Minimum GPA (e.g. 3.0): ");
                double maxGpa = reader.readDouble("Enter Maximum GPA (e.g. 4.0): ");
                if (minGpa > maxGpa) {
                    System.out.println("❌ Minimum GPA cannot exceed Maximum GPA.");
                    return;
                }
                List<Student> gpaMatches = manager.searchByGpaRange(minGpa, maxGpa);
                renderStudentTable(gpaMatches);
                break;
        }
    }

    private void handleUpdateStudent() {
        System.out.println(">>> UPDATE STUDENT RECORD <<<");
        int id = reader.readInt("Enter Student ID to update: ");

        try {
            Student existing = manager.searchById(id);
            System.out.println("Current Student Details:");
            System.out.println("----------------------------------------");
            System.out.println(existing);
            System.out.println("----------------------------------------");
            System.out.println("💡 Note: Press [Enter] to leave any field unchanged.");
            System.out.println();

            String name = reader.readOptionalString(String.format("Enter New Name [%s]: ", existing.getName()));
            Integer age = reader.readOptionalInt(String.format("Enter New Age [%d]: ", existing.getAge()));
            String course = reader.readOptionalString(String.format("Enter New Course [%s]: ", existing.getCourse()));
            Double gpa = reader.readOptionalDouble(String.format("Enter New GPA [%.2f]: ", existing.getGpa()));
            String email = reader.readOptionalString(String.format("Enter New Email [%s]: ", existing.getEmail()));
            String contact = reader.readOptionalString(String.format("Enter New Phone [%s]: ", existing.getContactNumber()));

            Student updated = manager.updateStudent(
                    id,
                    name.isEmpty() ? null : name,
                    age,
                    course.isEmpty() ? null : course,
                    gpa,
                    email.isEmpty() ? null : email,
                    contact.isEmpty() ? null : contact
            );

            System.out.println();
            System.out.println("✅ Student record successfully updated!");
            System.out.println("Updated Profile:");
            System.out.println(updated);

        } catch (StudentNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        } catch (InvalidStudentDataException e) {
            System.out.println("❌ Update Validation Error: " + e.getMessage());
        }
    }

    private void handleDeleteStudent() {
        System.out.println(">>> DELETE STUDENT RECORD <<<");
        int id = reader.readInt("Enter Student ID to delete: ");

        try {
            Student existing = manager.searchById(id);
            System.out.printf("Target Student: %s (ID: %d, Course: %s)\n",
                    existing.getName(), existing.getId(), existing.getCourse());

            boolean confirm = reader.readConfirmation("Are you sure you want to permanently delete this record?");
            if (confirm) {
                Student removed = manager.deleteStudent(id);
                System.out.println("✅ Student record successfully deleted for: " + removed.getName());
            } else {
                System.out.println("ℹ️ Deletion canceled by user.");
            }
        } catch (StudentNotFoundException e) {
            System.out.println("❌ " + e.getMessage());
        }
    }

    private void handleViewAnalytics() {
        System.out.println(">>> PERFORMANCE ANALYTICS & STATISTICS <<<");
        int total = manager.getStudentCount();
        if (total == 0) {
            System.out.println("ℹ️ No student records available to analyze. Add records or load sample data.");
            return;
        }

        System.out.printf("Total Students Enrolled : %d\n", total);
        System.out.printf("Class Average GPA       : %.2f\n", manager.getAverageGpa());

        manager.getHighestGpaStudent().ifPresent(s ->
                System.out.printf("Top Performer (Highest) : %s (ID: %d, GPA: %.2f, Grade: %s)\n",
                        s.getName(), s.getId(), s.getGpa(), s.getGrade()));

        manager.getLowestGpaStudent().ifPresent(s ->
                System.out.printf("Lowest GPA Record       : %s (ID: %d, GPA: %.2f, Grade: %s)\n",
                        s.getName(), s.getId(), s.getGpa(), s.getGrade()));

        System.out.println("\nGrade Distribution:");
        Map<String, Long> gradeDist = manager.getGradeDistribution();
        for (Map.Entry<String, Long> entry : gradeDist.entrySet()) {
            System.out.printf("  Grade %-3s : %d student(s)\n", entry.getKey(), entry.getValue());
        }

        System.out.println("\nDepartment/Course Enrollment:");
        Map<String, Long> courseDist = manager.getCourseDistribution();
        for (Map.Entry<String, Long> entry : courseDist.entrySet()) {
            System.out.printf("  %-22s : %d student(s)\n", entry.getKey(), entry.getValue());
        }
    }

    private void handleSortStudents() {
        System.out.println(">>> SORT STUDENT RECORDS <<<");
        if (manager.getStudentCount() == 0) {
            System.out.println("ℹ️ No records available to sort.");
            return;
        }

        System.out.println("  [1] Sort by Student ID (Ascending)");
        System.out.println("  [2] Sort by Name (A -> Z)");
        System.out.println("  [3] Sort by GPA (Highest to Lowest)");
        System.out.println("  [4] Sort by Age (Youngest to Oldest)");
        int sortChoice = reader.readIntInRange("Choose sorting criterion (1-4): ", 1, 4);

        switch (sortChoice) {
            case 1:
                manager.sortStudents(Comparator.comparingInt(Student::getId));
                System.out.println("✅ Sorted by Student ID (Ascending).");
                break;
            case 2:
                manager.sortStudents(Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER));
                System.out.println("✅ Sorted by Name (Alphabetical).");
                break;
            case 3:
                manager.sortStudents(Comparator.comparingDouble(Student::getGpa).reversed());
                System.out.println("✅ Sorted by GPA (Highest to Lowest).");
                break;
            case 4:
                manager.sortStudents(Comparator.comparingInt(Student::getAge));
                System.out.println("✅ Sorted by Age (Ascending).");
                break;
        }

        renderStudentTable(manager.getAllStudents());
    }

    private void handleLoadSampleData() {
        System.out.println(">>> LOAD SAMPLE DATA <<<");
        int countBefore = manager.getStudentCount();
        manager.loadSampleData();
        int added = manager.getStudentCount() - countBefore;

        if (added > 0) {
            System.out.printf("✅ Successfully loaded %d sample student records!\n", added);
        } else {
            System.out.println("ℹ️ Sample student records are already loaded.");
        }
        renderStudentTable(manager.getAllStudents());
    }

    private void handleClearAll() {
        System.out.println(">>> CLEAR ALL RECORDS <<<");
        if (manager.getStudentCount() == 0) {
            System.out.println("ℹ️ The database is already empty.");
            return;
        }

        boolean confirm = reader.readConfirmation("⚠️ WARNING: This will permanently delete ALL student records! Continue?");
        if (confirm) {
            manager.clearAllStudents();
            System.out.println("✅ All student records have been cleared.");
        } else {
            System.out.println("ℹ️ Operation cancelled.");
        }
    }

    private void renderStudentTable(List<Student> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("ℹ️ No records found matching the criteria.");
            return;
        }

        String border = "+--------+----------------------+------+------------------------+-------+-------+----------------------------+--------------+";
        System.out.println(border);
        System.out.println("| ID     | Name                 | Age  | Course                 | GPA   | Grade | Email                      | Contact      |");
        System.out.println(border);
        for (Student s : list) {
            System.out.println(s.toFormattedRow());
        }
        System.out.println(border);
        System.out.printf("Total records displayed: %d\n", list.size());
    }

    private void printGoodbye() {
        System.out.println("==========================================================================================");
        System.out.println("         Thank you for using the Student Management System. Have a great day! 👋          ");
        System.out.println("==========================================================================================");
    }
}
