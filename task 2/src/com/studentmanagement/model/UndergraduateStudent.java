package com.studentmanagement.model;

/**
 * Concrete class representing an Undergraduate student pursuing a bachelor's degree.
 * Demonstrates:
 *  - Multilevel Inheritance: Person -> Student -> UndergraduateStudent.
 *  - Runtime Polymorphism: Overrides credit limit (18 credits) and specialized tuition calculation.
 */
public class UndergraduateStudent extends Student {
    private String major;
    private String minor;
    private boolean hasHonorsScholarship;

    public UndergraduateStudent(int id, String firstName, String lastName, String email,
                                String academicYear, String major, String minor, boolean hasHonorsScholarship) {
        super(id, firstName, lastName, email, academicYear);
        setMajor(major);
        setMinor(minor);
        this.hasHonorsScholarship = hasHonorsScholarship;
    }

    public UndergraduateStudent(int id, String firstName, String lastName, String email, String academicYear, String major) {
        this(id, firstName, lastName, email, academicYear, major, "None", false);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException("Major cannot be blank.");
        }
        this.major = major.trim();
    }

    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = (minor != null && !minor.trim().isEmpty()) ? minor.trim() : "None";
    }

    public boolean isHasHonorsScholarship() {
        return hasHonorsScholarship;
    }

    public void setHasHonorsScholarship(boolean hasHonorsScholarship) {
        this.hasHonorsScholarship = hasHonorsScholarship;
    }

    @Override
    public int getMaxAllowedCredits() {
        // Undergraduates can enroll up to 18 credits per semester
        return 18;
    }

    @Override
    public double calculateTuition(double baseRatePerCredit) {
        double rawTuition = getTotalEnrolledCredits() * baseRatePerCredit;
        // Honors students receive a 15% scholarship reduction on base tuition
        if (hasHonorsScholarship) {
            rawTuition *= 0.85;
        }
        return rawTuition;
    }

    @Override
    public String getRole() {
        return "Undergraduate Student";
    }

    @Override
    public String getDetails() {
        return super.getDetails() +
                String.format("  Major: %s | Minor: %s | Honors Scholarship: %s\n",
                        major, minor, hasHonorsScholarship ? "Yes (15% off)" : "No");
    }
}
