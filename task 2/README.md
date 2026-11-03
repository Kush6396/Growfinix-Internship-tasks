# Task 2: Object-Oriented Programming (Days 4–5)
### Student & Course Management System in Java

A production-quality Java application designed to demonstrate and verify core **Object-Oriented Programming (OOP)** concepts:
- **Classes and Objects**
- **Constructors and Constructor Chaining** (`super` and `this`)
- **Encapsulation & Defensive Design**
- **Inheritance Hierarchies (Multilevel & Hierarchical)**
- **Polymorphism (Dynamic Method Overriding & Static Method Overloading)**
- **Abstraction & Interface Contracts**
- **Domain Exception Handling**

---

## 1. Project Architecture & Component Organization

The application is structured into decoupled, reusable packages following clean architectural principles:

```
task 2/
├── src/
│   └── com/studentmanagement/
│       ├── model/                     <-- Core domain entities & contracts
│       │   ├── Displayable.java       <-- Interface: Abstraction contract
│       │   ├── Grade.java             <-- Enum: Grade points & letter grades
│       │   ├── Person.java            <-- Abstract Base Class (Encapsulation)
│       │   ├── Instructor.java        <-- Subclass of Person
│       │   ├── Course.java            <-- Abstract Base Course (Modality abstraction)
│       │   ├── OnlineCourse.java      <-- Subclass of Course (Online delivery)
│       │   ├── InPersonCourse.java    <-- Subclass of Course (Physical lab delivery)
│       │   ├── Enrollment.java        <-- Association Class (Student <-> Course)
│       │   ├── Student.java           <-- Abstract Subclass of Person
│       │   ├── UndergraduateStudent.java <-- Subclass of Student (18 cr limit, honors discount)
│       │   └── GraduateStudent.java   <-- Subclass of Student (12 cr limit, stipend offset)
│       ├── service/                   <-- Business logic & orchestration
│       │   ├── EnrollmentService.java     <-- Interface contract
│       │   ├── EnrollmentServiceImpl.java <-- Implementation with validation rules
│       │   └── UniversityRegistry.java    <-- Repository & data store
│       ├── exception/                 <-- Domain-specific exception handling
│       │   ├── CourseFullException.java
│       │   ├── DuplicateEnrollmentException.java
│       │   └── CreditLimitExceededException.java
│       └── app/                       <-- Application entrypoint & CLI UI
│           └── Main.java              <-- Interactive menu & automated OOP showcase
├── bin/                               <-- Compiled bytecode (.class files)
├── run.bat                            <-- One-click build & launch script (Interactive)
├── demo.bat                           <-- One-click automated OOP deep dive showcase
└── README.md                          <-- Comprehensive documentation & guide
```

---

## 2. Core OOP Concepts Demonstrated

### A. Classes & Objects
- **`Person`**, **`Instructor`**, **`Course`**, **`Student`**, and **`Enrollment`** act as blueprints encapsulating state (attributes) and behavior (methods).
- Concrete objects are instantiated throughout the application (e.g., `new UndergraduateStudent(...)`, `new OnlineCourse(...)`).

### B. Constructors & Constructor Chaining
- **Constructor Overloading**: Constructors allow creating objects with default or full sets of parameters.
- **`this(...)` Chaining**: Used to reuse constructor logic within the same class (e.g., `Person(id, first, last, email)` delegates to `Person(id, first, last, email, phone)`).
- **`super(...)` Delegation**: Subclasses (`UndergraduateStudent`, `GraduateStudent`, `Instructor`, `OnlineCourse`) invoke parent class constructors to guarantee base invariants.

### C. Encapsulation & Data Protection
- All member fields are declared `private` or `final`.
- **Validation in Setters**:
  - `setEmail(String email)` enforces valid RFC-compliant email regex.
  - `setCredits(int credits)` ensures credits stay within 1–6.
  - `setMaxCapacity(int capacity)` guarantees positive seating capacity.
  - `setNumericScore(double score)` validates percentage range (0.0 to 100.0).
- **Defensive Copying**: `Student.getEnrollments()` returns `Collections.unmodifiableList(enrollments)`, preventing external tampering with internal student enrollment lists.

### D. Inheritance Hierarchies
```
                     +-------------------+
                     |   <<Interface>>   |
                     |    Displayable    |
                     +---------+---------+
                               |
              +----------------+----------------+
              |                                 |
      +-------v-------+                 +-------v-------+
      |  <<Abstract>> |                 |  <<Abstract>> |
      |    Person     |                 |    Course     |
      +-------+-------+                 +-------+-------+
              |                                 |
      +-------+-------+                 +-------+-------+
      |               |                 |               |
+-----v------+ +------v-------+  +------v-------+ +-----v--------+
| Instructor | | <<Abstract>> |  | OnlineCourse | |InPersonCourse|
+------------+ |   Student    |  +--------------+ +--------------+
               +------+-------+
                      |
              +-------+-------+
              |               |
   +----------v-----+  +------v---------+
   | Undergraduate  |  |    Graduate    |
   |    Student     |  |    Student     |
   +----------------+  +----------------+
```
- **Multilevel Inheritance**: `Person` -> `Student` -> `UndergraduateStudent` / `GraduateStudent`.
- **Hierarchical Inheritance**: Both `Student` and `Instructor` inherit from `Person`; both `OnlineCourse` and `InPersonCourse` inherit from `Course`.

### E. Polymorphism

#### 1. Runtime / Dynamic Polymorphism (Method Overriding `@Override`)
- **Tuition Calculation (`calculateTuition`)**:
  - `UndergraduateStudent`: Base tuition calculated as `credits * baseRate`, with an automatic **15% discount** for honors scholarship students.
  - `GraduateStudent`: Advanced rate (`baseRate * 1.25`) + `$350` lab facility fee, offset by the student's research stipend.
  - The caller executes `student.calculateTuition(rate)` on a generic `Student` reference without knowing the concrete subtype!
- **Credit Capacity Constraints (`getMaxAllowedCredits`)**:
  - Undergraduates: Maximum `18` credits.
  - Graduate students: Maximum `12` credits (due to research workload).
- **Course Modality Fee (`calculateCourseFees`)**:
  - `OnlineCourse`: Adds `$60` cloud infrastructure fee.
  - `InPersonCourse`: Adds course-specific laboratory/equipment fee.
- **`displayDetails()` & `getDetails()`**:
  - Implemented polymorphically across all entities through the `Displayable` contract.

#### 2. Compile-Time / Static Polymorphism (Method Overloading)
- **`Student.enroll(Course)`** vs **`Student.enroll(Course, Grade)`**
- **`Student.assignGrade(Course, Grade)`** vs **`Student.assignGrade(String code, Grade)`** vs **`Student.assignGrade(String code, double score)`**
- **`UniversityRegistry.findStudent(int id)`** vs **`UniversityRegistry.findStudent(String email)`** vs **`UniversityRegistry.findStudentsByName(String name)`**

### F. Abstraction & Interfaces
- **`Displayable`**: Standardizes display formatting for decoupled reporting.
- **`EnrollmentService`**: Isolates enrollment validation rules and business logic from data storage.
- **`Person` and `Course`**: Marked `abstract` so incomplete base entities cannot be instantiated directly.

### G. Domain-Specific Custom Exception Handling
- **`CourseFullException`**: Thrown when enrollment exceeds `course.getMaxCapacity()`.
- **`DuplicateEnrollmentException`**: Thrown when a student attempts to register for the same course twice.
- **`CreditLimitExceededException`**: Thrown when adding a course causes the student's registered credits to exceed their degree limit (18 for Undergrad, 12 for Grad).

---

## 3. How to Run the Application

### Option 1: One-Click Launch (Windows)
- Double-click **`demo.bat`** to run the complete automated OOP showcase.
- Double-click **`run.bat`** to launch the interactive management console.

### Option 2: Command Line (CLI)
From the project root directory (`task 2/`):

1. **Compile all Java source files:**
   ```powershell
   javac -d bin src\com\studentmanagement\exception\*.java src\com\studentmanagement\model\*.java src\com\studentmanagement\service\*.java src\com\studentmanagement\app\*.java
   ```

2. **Run the Automated OOP Showcase:**
   ```powershell
   java -cp bin com.studentmanagement.app.Main --demo
   ```

3. **Run the Interactive Application:**
   ```powershell
   java -cp bin com.studentmanagement.app.Main
   ```

---

## 4. Opening in IDEs

### IntelliJ IDEA
1. Open IntelliJ IDEA -> Select **File > Open**.
2. Navigate to and select the `task 2` directory.
3. In the Project tool window, right-click on the `src` folder -> **Mark Directory as > Sources Root**.
4. Navigate to `src/com/studentmanagement/app/Main.java`.
5. Right-click `Main.java` and select **Run 'Main.main()'**.

### Eclipse IDE
1. Open Eclipse -> Select **File > New > Java Project**.
2. Enter Project Name (e.g., `Task2_OOP`).
3. Uncheck "Use default location", click **Browse**, and choose `c:\Users\omraj\Downloads\task 2`.
4. Ensure the `src` folder is recognized as the source folder.
5. Click **Finish**.
6. Expand `src/com/studentmanagement/app/Main.java` -> Right-click -> **Run As > Java Application**.

---

## 5. AI Assistance & OOP Engineering Insights

As part of the assignment requirements ("*Use AI to understand OOP principles and organize the application into reusable components*"):
1. **Model Decomposition**: AI analysis helped separate `Person` as a general superclass, allowing `Instructor` and `Student` to share common identity validation while decoupling academic responsibilities.
2. **Defensive Encapsulation**: AI was used to identify subtle leakages in collection encapsulation (e.g., returning direct list references), resolved by introducing `Collections.unmodifiableList(...)`.
3. **Polymorphic Rule Engine**: Used AI prompting to formulate realistic business differences between Undergraduate and Graduate student programs (differing credit limits, differing fee calculations, and research stipend offsets).
