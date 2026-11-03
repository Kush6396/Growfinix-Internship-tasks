package com.studentmanagement.model;

/**
 * Enumeration representing academic letter grades and their corresponding GPA grade points.
 */
public enum Grade {
    A(4.0, "Excellent"),
    B(3.0, "Good"),
    C(2.0, "Satisfactory"),
    D(1.0, "Pass"),
    F(0.0, "Fail"),
    IN_PROGRESS(-1.0, "Course In Progress");

    private final double gradePoint;
    private final String description;

    Grade(double gradePoint, String description) {
        this.gradePoint = gradePoint;
        this.description = description;
    }

    public double getGradePoint() {
        return gradePoint;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return this != IN_PROGRESS;
    }

    /**
     * Converts a percentage mark (0-100) to standard letter grade.
     *
     * @param score Percentage score between 0 and 100.
     * @return Corresponding Grade enum.
     */
    public static Grade fromNumericScore(double score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100. Received: " + score);
        }
        if (score >= 90.0) return A;
        if (score >= 80.0) return B;
        if (score >= 70.0) return C;
        if (score >= 60.0) return D;
        return F;
    }
}
