package com.taskone;

/**
 * Program 3: Student Marks Calculator & Grade Report Generator
 *
 * Concepts Demonstrated:
 *  - Primitive and Reference Data Types (double, int, String, arrays)
 *  - Array Declaration, Dynamic Allocation, and Traversal (1D Arrays)
 *  - Iterative Loops (standard for loop and enhanced for-each loop)
 *  - Conditional Statements (if-else if-else grading ladder, boundary validation)
 *  - Statistical Calculations (Sum, Average, Min, Max algorithms)
 *  - Formatted String Output (System.out.printf, tabular ASCII presentation)
 *  - Modular Method Design
 */
public class StudentMarksCalculator {

    public static final double PASSING_MARK_THRESHOLD = 40.0;
    public static final double MAX_MARK_PER_SUBJECT = 100.0;

    /**
     * Standalone main method to run this module directly.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        run();
    }

    /**
     * Main execution flow for the Student Marks Calculator.
     */
    public static void run() {
        System.out.println("==================================================");
        System.out.println("        STUDENT MARKS & GRADE CALCULATOR          ");
        System.out.println("==================================================");

        boolean processAnother = true;

        while (processAnother) {
            // Collect Student Metadata
            String studentName = InputHelper.readString("Enter Student Full Name: ");
            String rollNumber = InputHelper.readString("Enter Roll / Student ID Number: ");
            int subjectCount = InputHelper.readInt("Enter Number of Subjects (1 - 10): ", 1, 10);

            // Initialize 1D Arrays to hold subject names and marks
            String[] subjectNames = new String[subjectCount];
            double[] marks = new double[subjectCount];

            System.out.println("\n--- Enter Subject Details ---");
            for (int i = 0; i < subjectCount; i++) {
                subjectNames[i] = InputHelper.readString(String.format("Enter name of Subject %d: ", (i + 1)));
                marks[i] = InputHelper.readDouble(
                        String.format("Enter marks obtained for '%s' (0 - %.0f): ", subjectNames[i], MAX_MARK_PER_SUBJECT),
                        0.0,
                        MAX_MARK_PER_SUBJECT
                );
            }

            // Calculations
            double totalMarks = calculateTotal(marks);
            double maxPossibleMarks = subjectCount * MAX_MARK_PER_SUBJECT;
            double averagePercentage = calculatePercentage(totalMarks, maxPossibleMarks);
            String letterGrade = determineGrade(averagePercentage);
            String performanceRemark = getPerformanceRemark(letterGrade);

            int highestIndex = findIndexOfHighestMark(marks);
            int lowestIndex = findIndexOfLowestMark(marks);
            boolean hasFailedAnySubject = checkAnySubjectFailed(marks);

            // Display Beautiful ASCII Report Card
            displayReportCard(
                    studentName,
                    rollNumber,
                    subjectNames,
                    marks,
                    totalMarks,
                    maxPossibleMarks,
                    averagePercentage,
                    letterGrade,
                    performanceRemark,
                    highestIndex,
                    lowestIndex,
                    hasFailedAnySubject
            );

            processAnother = InputHelper.readConfirmation("\nCalculate marks for another student?");
            System.out.println();
        }

        System.out.println("Student Marks Calculator session ended.");
    }

    /**
     * Calculates the sum total of all marks in the array using an enhanced for-each loop.
     *
     * @param marks Array of subject marks
     * @return Sum total of marks
     */
    public static double calculateTotal(double[] marks) {
        double sum = 0.0;
        // Demonstrating enhanced for-each loop
        for (double mark : marks) {
            sum += mark;
        }
        return sum;
    }

    /**
     * Calculates the average percentage score.
     *
     * @param totalMarks       Sum of marks obtained
     * @param maxPossibleMarks Maximum total marks achievable
     * @return Percentage value [0 - 100]
     */
    public static double calculatePercentage(double totalMarks, double maxPossibleMarks) {
        if (maxPossibleMarks == 0) return 0.0;
        return (totalMarks / maxPossibleMarks) * 100.0;
    }

    /**
     * Determines letter grade using an if-else if-else conditional ladder.
     *
     * @param percentage Average percentage
     * @return Letter grade as String
     */
    public static String determineGrade(double percentage) {
        if (percentage >= 90.0) {
            return "A+";
        } else if (percentage >= 80.0) {
            return "A";
        } else if (percentage >= 70.0) {
            return "B";
        } else if (percentage >= 60.0) {
            return "C";
        } else if (percentage >= 50.0) {
            return "D";
        } else {
            return "F";
        }
    }

    /**
     * Provides a descriptive academic remark based on the grade.
     */
    public static String getPerformanceRemark(String grade) {
        switch (grade) {
            case "A+":
                return "Outstanding Academic Excellence!";
            case "A":
                return "Excellent Performance! Keep it up.";
            case "B":
                return "Very Good! Strive for greater heights.";
            case "C":
                return "Good effort, but substantial room for improvement.";
            case "D":
                return "Pass. Needs consistent hard work and focus.";
            case "F":
                return "Fail. Urgent academic intervention required.";
            default:
                return "No remark available.";
        }
    }

    /**
     * Finds index of subject with highest mark.
     */
    public static int findIndexOfHighestMark(double[] marks) {
        int bestIndex = 0;
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] > marks[bestIndex]) {
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    /**
     * Finds index of subject with lowest mark.
     */
    public static int findIndexOfLowestMark(double[] marks) {
        int lowestIndex = 0;
        for (int i = 1; i < marks.length; i++) {
            if (marks[i] < marks[lowestIndex]) {
                lowestIndex = i;
            }
        }
        return lowestIndex;
    }

    /**
     * Checks if any single subject has marks below the passing threshold.
     */
    public static boolean checkAnySubjectFailed(double[] marks) {
        for (double mark : marks) {
            if (mark < PASSING_MARK_THRESHOLD) {
                return true;
            }
        }
        return false;
    }

    /**
     * Prints a formatted ASCII report card.
     */
    private static void displayReportCard(
            String studentName,
            String rollNumber,
            String[] subjectNames,
            double[] marks,
            double totalMarks,
            double maxPossibleMarks,
            double percentage,
            String grade,
            String remark,
            int highestIndex,
            int lowestIndex,
            boolean hasFailedAnySubject
    ) {
        System.out.println("\n================================================================");
        System.out.println("                   OFFICIAL ACADEMIC REPORT CARD                ");
        System.out.println("================================================================");
        System.out.printf("  Student Name : %-25s Roll / ID : %s%n", studentName, rollNumber);
        System.out.println("----------------------------------------------------------------");
        System.out.printf("  %-4s | %-24s | %-12s | %-10s%n", "#", "Subject", "Score / 100", "Status");
        System.out.println("----------------------------------------------------------------");

        for (int i = 0; i < marks.length; i++) {
            String status = (marks[i] >= PASSING_MARK_THRESHOLD) ? "PASS" : "FAIL";
            System.out.printf("  %-4d | %-24s | %10.2f   | %-10s%n",
                    (i + 1), subjectNames[i], marks[i], status);
        }

        System.out.println("================================================================");
        System.out.printf("  Total Marks Obtained : %6.2f / %.0f%n", totalMarks, maxPossibleMarks);
        System.out.printf("  Average Percentage   : %6.2f%%%n", percentage);
        System.out.printf("  Assigned Grade       : %s%n", grade);

        String overallStatus = (!hasFailedAnySubject && percentage >= PASSING_MARK_THRESHOLD)
                ? "PASSED CONGRATULATIONS! 🎉"
                : "FAILED / RE-EXAM REQUIRED ⚠️";

        System.out.printf("  Overall Result       : %s%n", overallStatus);
        System.out.printf("  Best Subject         : %s (%.2f/100)%n",
                subjectNames[highestIndex], marks[highestIndex]);
        System.out.printf("  Lowest Subject       : %s (%.2f/100)%n",
                subjectNames[lowestIndex], marks[lowestIndex]);
        System.out.printf("  Remarks              : %s%n", remark);
        System.out.println("================================================================");
    }
}
