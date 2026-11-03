package com.studentmanagement.model;

/**
 * Concrete class representing an on-campus physical course offering.
 * Demonstrates:
 *  - Inheritance: Subclasses Course and invokes parent constructor via super().
 *  - Polymorphic method overriding: Overrides fee calculation with lab equipment overhead.
 */
public class InPersonCourse extends Course {
    private String building;
    private String roomNumber;
    private double labFee;

    public InPersonCourse(String courseCode, String courseName, int credits, int maxCapacity,
                          Instructor instructor, String building, String roomNumber, double labFee) {
        super(courseCode, courseName, credits, maxCapacity, instructor);
        setBuilding(building);
        setRoomNumber(roomNumber);
        setLabFee(labFee);
    }

    public InPersonCourse(String courseCode, String courseName, int credits, int maxCapacity,
                          String building, String roomNumber) {
        this(courseCode, courseName, credits, maxCapacity, null, building, roomNumber, 0.0);
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        if (building == null || building.trim().isEmpty()) {
            throw new IllegalArgumentException("Building name cannot be blank.");
        }
        this.building = building.trim();
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Room number cannot be blank.");
        }
        this.roomNumber = roomNumber.trim();
    }

    public double getLabFee() {
        return labFee;
    }

    public void setLabFee(double labFee) {
        if (labFee < 0) {
            throw new IllegalArgumentException("Lab fee cannot be negative.");
        }
        this.labFee = labFee;
    }

    @Override
    public String getDeliveryFormat() {
        return String.format("In-Person (%s - Room %s)", building, roomNumber);
    }

    @Override
    public double calculateCourseFees(double baseFeePerCredit) {
        // Physical course cost includes credit hours plus lab equipment/facility fee
        return (getCredits() * baseFeePerCredit) + labFee;
    }

    @Override
    public String getDetails() {
        String instructorName = (getInstructor() != null) ? getInstructor().getFullName() : "TBA";
        return String.format("In-Person Course: [%s] %s | %d Credits | Enrolled: %d/%d\n" +
                        "  Instructor: %s\n" +
                        "  Location  : %s, Room %s\n" +
                        "  Lab Fee   : $%.2f",
                getCourseCode(), getCourseName(), getCredits(), getEnrolledCount(), getMaxCapacity(),
                instructorName, building, roomNumber, labFee);
    }
}
