package com.studentmanagement.model;

/**
 * Concrete class representing a Graduate student pursuing a Master's or Ph.D. degree.
 * Demonstrates:
 *  - Multilevel Inheritance: Person -> Student -> GraduateStudent.
 *  - Aggregation/Association: References an Instructor as research supervisor.
 *  - Runtime Polymorphism: Overrides credit ceiling (12 credits) and graduate tuition formula with research stipend offset.
 */
public class GraduateStudent extends Student {
    private String researchArea;
    private Instructor thesisSupervisor;
    private double researchStipend;

    public GraduateStudent(int id, String firstName, String lastName, String email,
                           String academicYear, String researchArea, Instructor thesisSupervisor,
                           double researchStipend) {
        super(id, firstName, lastName, email, academicYear);
        setResearchArea(researchArea);
        this.thesisSupervisor = thesisSupervisor;
        setResearchStipend(researchStipend);
    }

    public GraduateStudent(int id, String firstName, String lastName, String email,
                           String academicYear, String researchArea) {
        this(id, firstName, lastName, email, academicYear, researchArea, null, 0.0);
    }

    public String getResearchArea() {
        return researchArea;
    }

    public void setResearchArea(String researchArea) {
        if (researchArea == null || researchArea.trim().isEmpty()) {
            throw new IllegalArgumentException("Research area cannot be blank.");
        }
        this.researchArea = researchArea.trim();
    }

    public Instructor getThesisSupervisor() {
        return thesisSupervisor;
    }

    public void setThesisSupervisor(Instructor thesisSupervisor) {
        this.thesisSupervisor = thesisSupervisor;
    }

    public double getResearchStipend() {
        return researchStipend;
    }

    public void setResearchStipend(double researchStipend) {
        if (researchStipend < 0) {
            throw new IllegalArgumentException("Research stipend cannot be negative.");
        }
        this.researchStipend = researchStipend;
    }

    @Override
    public int getMaxAllowedCredits() {
        // Graduate students focus heavily on research; maximum course load is 12 credits
        return 12;
    }

    @Override
    public double calculateTuition(double baseRatePerCredit) {
        // Graduate courses carry a 1.25x tier rate plus a $350 research facility fee, offset by stipend
        double graduateRate = baseRatePerCredit * 1.25;
        double rawTuition = (getTotalEnrolledCredits() * graduateRate) + 350.0;
        double netTuition = rawTuition - researchStipend;
        return Math.max(0.0, netTuition);
    }

    @Override
    public String getRole() {
        return "Graduate Student";
    }

    @Override
    public String getDetails() {
        String supervisorName = (thesisSupervisor != null) ? thesisSupervisor.getFullName() : "None Assigned";
        return super.getDetails() +
                String.format("  Research Area     : %s\n" +
                              "  Thesis Supervisor : %s\n" +
                              "  Research Stipend  : $%.2f\n",
                        researchArea, supervisorName, researchStipend);
    }
}
