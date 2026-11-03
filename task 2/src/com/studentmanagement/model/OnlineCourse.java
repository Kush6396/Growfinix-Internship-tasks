package com.studentmanagement.model;

/**
 * Concrete class representing an online distance learning course.
 * Demonstrates:
 *  - Inheritance: Subclasses Course and invokes parent constructor via super().
 *  - Polymorphic method overriding: Specializes fee calculation and delivery format.
 */
public class OnlineCourse extends Course {
    private String platform;
    private String meetingUrl;
    private boolean recordedSessions;

    public OnlineCourse(String courseCode, String courseName, int credits, int maxCapacity,
                        Instructor instructor, String platform, String meetingUrl, boolean recordedSessions) {
        super(courseCode, courseName, credits, maxCapacity, instructor);
        setPlatform(platform);
        this.meetingUrl = meetingUrl != null ? meetingUrl.trim() : "https://learn.university.edu";
        this.recordedSessions = recordedSessions;
    }

    public OnlineCourse(String courseCode, String courseName, int credits, int maxCapacity, String platform) {
        this(courseCode, courseName, credits, maxCapacity, null, platform, "https://learn.university.edu", true);
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        if (platform == null || platform.trim().isEmpty()) {
            throw new IllegalArgumentException("Platform name cannot be empty.");
        }
        this.platform = platform.trim();
    }

    public String getMeetingUrl() {
        return meetingUrl;
    }

    public void setMeetingUrl(String meetingUrl) {
        this.meetingUrl = meetingUrl;
    }

    public boolean isRecordedSessions() {
        return recordedSessions;
    }

    public void setRecordedSessions(boolean recordedSessions) {
        this.recordedSessions = recordedSessions;
    }

    @Override
    public String getDeliveryFormat() {
        return String.format("Online via %s (Recordings: %s)",
                platform, (recordedSessions ? "Yes" : "No"));
    }

    @Override
    public double calculateCourseFees(double baseFeePerCredit) {
        // Online courses add a flat $60 cloud technology / infrastructure fee
        double base = getCredits() * baseFeePerCredit;
        double techFee = 60.0;
        return base + techFee;
    }

    @Override
    public String getDetails() {
        String instructorName = (getInstructor() != null) ? getInstructor().getFullName() : "TBA";
        return String.format("Online Course: [%s] %s | %d Credits | Enrolled: %d/%d\n" +
                        "  Instructor : %s\n" +
                        "  Platform   : %s\n" +
                        "  Meeting URL: %s | Recordings Available: %s",
                getCourseCode(), getCourseName(), getCredits(), getEnrolledCount(), getMaxCapacity(),
                instructorName, platform, meetingUrl, recordedSessions ? "Yes" : "No");
    }
}
