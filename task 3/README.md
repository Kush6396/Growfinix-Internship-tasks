# 🎓 Console-Based Student Management System (SMS)

A console-based Student Management System developed in **Java** leveraging the **Java Collections Framework (`ArrayList`)**, robust **Custom Exception Handling**, and strict input validation. Designed, debugged, structured, and tested with **ChatGPT / Gemini AI** assistance.

---

## 🚀 Key Features

1. **Add Student Record**:
   - Collects ID, Full Name, Age, Course/Department, GPA, Email, and Phone Number.
   - Enforces unique ID constraints and rejects duplicates.
2. **View All Students**:
   - Displays student records in an ASCII table with calculated letter grades.
3. **Search Student Records**:
   - **By ID**: Exact lookup.
   - **By Name**: Case-insensitive substring search.
   - **By Course**: Department / course filter.
   - **By GPA Range**: Search students between custom minimum and maximum GPAs.
4. **Update Student Details**:
   - Prompts for each field individually.
   - Pressing `[Enter]` retains current field value without overwriting it.
5. **Delete Student Record**:
   - Look up by ID with user confirmation (`y/n`) prior to permanent deletion.
6. **Performance Analytics & Statistics**:
   - Total enrollment count.
   - Class average GPA.
   - Highest and lowest GPA achievers.
   - Letter grade breakdown (A+, A, B+, B, C, D, F).
   - Enrollment distribution across courses.
7. **Sorting**:
   - Sort by Student ID (ascending).
   - Sort by Name (alphabetical A-Z).
   - Sort by GPA (highest to lowest).
   - Sort by Age (youngest to oldest).
8. **Sample Data Generator**:
   - One-click load of 5 pre-configured student records for demonstration.
9. **Automated Zero-Dependency Test Suite**:
   - 44 automated test cases covering edge cases, boundary values, and custom exceptions.

---

## 🛠️ Tech Stack & Concepts

- **Language:** Java SE (JDK 23 / 17+)
- **Data Structure:** Java Collections Framework (`java.util.ArrayList`, `java.util.List`)
- **Algorithms & Utilities:** `java.util.Comparator`, Java Streams & Collectors (`groupingBy`)
- **Exception Handling:** Custom checked exceptions (`StudentNotFoundException`, `DuplicateStudentException`, `InvalidStudentDataException`)
- **Input Stream Safety:** Custom `ConsoleInputReader` eliminating `Scanner` buffer overflows and `InputMismatchException` crashes
- **AI Tooling:** Gemini AI / ChatGPT for code structuring, edge case generation, debugging regex, and test suite synthesis

---

## 📁 Project Structure

```
task 3/
├── src/
│   └── com/
│       └── sms/
│           ├── model/
│           │   └── Student.java                   # Student entity model
│           ├── exception/
│           │   ├── DuplicateStudentException.java    # Thrown when duplicate ID is detected
│           │   ├── StudentNotFoundException.java     # Thrown when student record is missing
│           │   └── InvalidStudentDataException.java  # Thrown on validation failure
│           ├── util/
│           │   ├── InputValidator.java               # Regex & boundary validation rules
│           │   └── ConsoleInputReader.java           # Safe Scanner reader preventing crashes
│           ├── service/
│           │   └── StudentManager.java               # Business logic & ArrayList operations
│           ├── test/
│           │   └── StudentManagerTestSuite.java      # 44 automated test cases
│           └── Main.java                             # Console UI and interactive menu
├── bin/                                              # Compiled .class files
├── run.bat                                           # Windows one-click runner for the app
├── test.bat                                          # Windows one-click runner for test suite
├── AI_ASSISTANCE_REPORT.md                           # AI debugging & architecture documentation
└── README.md                                         # Project documentation
```

---

## ⚡ How to Run

### Method 1: One-Click Batch Scripts (Windows)
- **To run the application:** Double click `run.bat` or run:
  ```cmd
  run.bat
  ```
- **To run the automated test suite:** Double click `test.bat` or run:
  ```cmd
  test.bat
  ```

---

### Method 2: Manual Terminal Commands (Cross-Platform)

1. **Compile all Java source files:**
   ```bash
   javac -d bin src/com/sms/model/*.java src/com/sms/exception/*.java src/com/sms/util/*.java src/com/sms/service/*.java src/com/sms/test/*.java src/com/sms/*.java
   ```

2. **Launch the interactive application:**
   ```bash
   java -cp bin com.sms.Main
   ```

3. **Run the automated test suite:**
   ```bash
   java -cp bin com.sms.test.StudentManagerTestSuite
   ```

---

## 🛡️ Validation Rules & Exception Handling

| Field | Validation Constraint | Exception Thrown on Failure |
| :--- | :--- | :--- |
| **Student ID** | Positive whole integer > 0; Must be unique in system | `InvalidStudentDataException` / `DuplicateStudentException` |
| **Name** | 2 to 50 characters; letters, spaces, hyphens, and apostrophes | `InvalidStudentDataException` |
| **Age** | Integer between 15 and 100 | `InvalidStudentDataException` |
| **Course** | 2 to 60 non-empty characters | `InvalidStudentDataException` |
| **GPA** | Decimal between 0.00 and 4.00 | `InvalidStudentDataException` |
| **Email** | Valid RFC-compliant email (e.g., `user@domain.edu`) | `InvalidStudentDataException` |
| **Phone** | Exactly 10 numeric digits | `InvalidStudentDataException` |
| **Query ID** | ID must exist when updating, deleting, or searching | `StudentNotFoundException` |

---

## 🧪 Automated Test Suite Output

```text
========================================================================
          🧪 RUNNING AI-GENERATED AUTOMATED TEST SUITE 🧪              
========================================================================
  [PASS] testAddStudentSuccess - Count                     
  [PASS] testAddStudentSuccess - Name                      
  [PASS] testGetAllStudentsEncapsulation                   
  [PASS] testDuplicateStudentException                     
  [PASS] testStudentNotFoundOnSearch                       
  [PASS] testStudentNotFoundOnDelete                       
  [PASS] testStudentNotFoundOnUpdate                       
  [PASS] testInvalidAgeBoundaries (Age 14)                 
  [PASS] testInvalidAgeBoundaries (Age 101)                
  [PASS] testValidAgeBoundaries                            
  [PASS] testInvalidGpaBoundaries (GPA -0.1)               
  [PASS] testInvalidGpaBoundaries (GPA 4.01)               
  [PASS] testValidGpaBoundaries                            
  [PASS] testInvalidEmailValidation (plainaddress)         
  [PASS] testInvalidEmailValidation (@missingusername.com) 
  [PASS] testInvalidEmailValidation (user@.com.my)         
  [PASS] testInvalidEmailValidation (user@site)            
  [PASS] testInvalidPhoneValidation (123)                  
  [PASS] testInvalidPhoneValidation (abcdefghij)           
  [PASS] testInvalidPhoneValidation (123456789012)         
  [PASS] testInvalidPhoneValidation ()                     
  [PASS] testEmptyNameValidation                           
  [PASS] testSearchByNameCaseInsensitive - Count           
  [PASS] testSearchByCourse - Count                        
  [PASS] testSearchByGpaRange - Count                      
  [PASS] testUpdateStudentPartialFields - GPA              
  [PASS] testUpdateStudentPartialFields - Email            
  [PASS] testUpdateStudentPartialFields - Name Unchanged   
  [PASS] testUpdateStudentPartialFields - Age Unchanged    
  [PASS] testDeleteStudentSuccess - Count Before           
  [PASS] testDeleteStudentSuccess - Removed ID             
  [PASS] testDeleteStudentSuccess - Count After            
  [PASS] testSortingByGpaDescending - Top                  
  [PASS] testSortingByGpaDescending - Mid                  
  [PASS] testSortingByGpaDescending - Last                 
  [PASS] testAverageGpa                                    
  [PASS] testHighestGpa                                    
  [PASS] testLowestGpa                                     
  [PASS] Grade 3.85 -> A+                                  
  [PASS] Grade 3.40 -> A                                   
  [PASS] Grade 3.10 -> B+                                  
  [PASS] Grade 2.50 -> C                                   
  [PASS] Grade 1.50 -> D                                   
  [PASS] Grade 0.80 -> F                                   
========================================================================
Test Execution Summary: 44 PASSED, 0 FAILED, Total: 44
🎉 ALL TEST CASES PASSED WITH 100% SUCCESS!
========================================================================
```

---

## 🤖 Role of AI in this Project

For an in-depth breakdown of how AI was used to restructure classes, solve scanner edge cases, debug regular expressions, and formulate test suites, refer to the [AI_ASSISTANCE_REPORT.md](file:///c:/Users/omraj/Downloads/task%203/AI_ASSISTANCE_REPORT.md).
