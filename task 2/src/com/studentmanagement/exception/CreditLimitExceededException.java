package com.studentmanagement.exception;

/**
 * Exception thrown when adding a course would exceed the student's maximum allowed credit limit.
 */
public class CreditLimitExceededException extends Exception {
    private final int studentId;
    private final int currentCredits;
    private final int attemptedCredits;
    private final int maxAllowedCredits;

    public CreditLimitExceededException(int studentId, int currentCredits, int attemptedCredits, int maxAllowedCredits) {
        super(String.format("Enrollment failed for Student ID %d: Adding %d credits to current %d credits " +
                        "exceeds the maximum limit of %d credits.",
                studentId, attemptedCredits, currentCredits, maxAllowedCredits));
        this.studentId = studentId;
        this.currentCredits = currentCredits;
        this.attemptedCredits = attemptedCredits;
        this.maxAllowedCredits = maxAllowedCredits;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getCurrentCredits() {
        return currentCredits;
    }

    public int getAttemptedCredits() {
        return attemptedCredits;
    }

    public int getMaxAllowedCredits() {
        return maxAllowedCredits;
    }
}
