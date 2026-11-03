# ☕ Java Fundamentals & Practical Mini-Projects (Task 1)

[![Java](https://img.shields.io/badge/Java-JDK%2017%20|%2021%20|%2023-orange.svg)](https://www.oracle.com/java/)
[![IDE](https://img.shields.io/badge/IDE-IntelliJ%20IDEA%20|%20Eclipse-blue.svg)](https://www.jetbrains.com/idea/)
[![AI Assistant](https://img.shields.io/badge/AI-ChatGPT%20|%20Gemini-purple.svg)](https://gemini.google.com/)

A comprehensive, modular core Java project built to master foundational programming concepts, error debugging, and clean code practices. Developed with modern Java standards and powered by AI-assisted learning.

---

## 📌 Project Overview

This repository covers **Task 1** of the Java Development curriculum:
- **Core Concepts Mastered:** Java syntax, variables, primitive & reference data types, operators, conditional statements, iterative loops, 1D arrays, and modular methods.
- **Interactive Mini-Projects:**
  1. **Arithmetic & Scientific Calculator** (`Calculator.java`)
  2. **Number Guessing Game** (`NumberGuessingGame.java`)
  3. **Student Marks & Grade Calculator** (`StudentMarksCalculator.java`)
  4. **Java Fundamentals Interactive Tour** (`JavaBasicsDemo.java`)
  5. **Master Application Dashboard** (`Main.java`)
- **AI-Assisted Development:** Utilizing ChatGPT and Gemini AI for conceptual understanding, debugging common runtime errors, and refactoring for clean code principles.

---

## 🛠️ Tech Stack & Prerequisites

| Component | Specification |
| :--- | :--- |
| **Programming Language** | Java (JDK 17, 21, or 23+) |
| **Integrated Development Environments** | IntelliJ IDEA, Eclipse IDE, or VS Code |
| **Build / Execution** | JDK Compiler (`javac`), Java Runtime (`java`), Batch (`run.bat`), PowerShell (`run.ps1`) |
| **AI Mentorship & Debugging** | ChatGPT (OpenAI) / Gemini AI (Google) |

---

## 📂 Project Architecture

```text
task 1/
├── src/
│   └── com/
│       └── taskone/
│           ├── Main.java                   # Master launcher dashboard menu
│           ├── Calculator.java             # Mini-Project 1: Arithmetic & Scientific Calculator
│           ├── NumberGuessingGame.java     # Mini-Project 2: Interactive Guessing Game
│           ├── StudentMarksCalculator.java # Mini-Project 3: Array-based Grade Report Generator
│           ├── JavaBasicsDemo.java         # Live educational tour of all core Java concepts
│           └── InputHelper.java            # Robust input validation & Scanner utility
├── bin/                                    # Compiled bytecode .class files
├── run.bat                                 # Windows 1-click compile & launch script
├── run.ps1                                 # PowerShell compile & launch script
├── AI_LEARNING_GUIDE.md                    # Dedicated AI prompting, debugging & refactoring guide
└── README.md                               # Project documentation & execution guide
```

---

## 🚀 How to Run the Project

### Option 1: One-Click Execution (Windows)
Double-click `run.bat` in File Explorer, or open your terminal in the project directory and run:
```cmd
run.bat
```
Or via PowerShell:
```powershell
.\run.ps1
```

---

### Option 2: IntelliJ IDEA
1. Open **IntelliJ IDEA**.
2. Click **File -> Open...** and select the `task 1` folder.
3. Ensure Project SDK is configured:
   - Navigate to **File -> Project Structure -> Project**.
   - Select your installed JDK (e.g., JDK 23, 21, or 17).
4. In the Project Explorer pane on the left, navigate to:
   `src/com/taskone/Main.java`.
5. Right-click `Main.java` and select **Run 'Main.main()'** (or press `Shift + F10`).
6. You can also run individual classes (`Calculator.java`, `NumberGuessingGame.java`, `StudentMarksCalculator.java`) directly by right-clicking each file.

---

### Option 3: Eclipse IDE
1. Open **Eclipse IDE**.
2. Click **File -> New -> Java Project**.
3. Name the project `Task1` and uncheck "Create module-info.java".
4. Copy the `src` folder from `task 1` into the Eclipse project `src` directory (or choose **File -> Open Projects from File System...**).
5. Right-click `src/com/taskone/Main.java` -> **Run As -> Java Application**.

---

### Option 4: Manual Terminal (CLI)
Open Command Prompt or PowerShell in the `task 1` directory:
```bash
# 1. Create output bin directory (if not exists)
mkdir bin

# 2. Compile all source files
javac -d bin src/com/taskone/*.java

# 3. Launch the Master Application
java -cp bin com.taskone.Main
```

---

## 💻 Mini-Projects Detailed Breakdown

### 1. Arithmetic & Scientific Calculator (`Calculator.java`)
- **Features:**
  - Addition (`+`), Subtraction (`-`), Multiplication (`*`), Division (`/`), Modulo (`%`)
  - Exponentiation ($x^y$), Square Root ($\sqrt{x}$)
  - Demonstration of **Method Overloading** with 3-operand addition
  - Math safety: Zero-division detection, negative square root prevention
  - Continuous calculation loop (`do-while`)

### 2. Number Guessing Game (`NumberGuessingGame.java`)
- **Features:**
  - Dynamic pseudo-random target generation (`java.util.Random`)
  - Three difficulty settings:
    - **Easy:** 1 – 50 (10 attempts)
    - **Medium:** 1 – 100 (7 attempts)
    - **Hard:** 1 – 200 (5 attempts)
  - Proximity temperature hints (*"Boiling Hot!"*, *"Warm"*, *"Cool"*, *"Freezing Cold"*)
  - Real-time attempt counter, round win rate, and personal best score tracking

### 3. Student Marks & Grade Calculator (`StudentMarksCalculator.java`)
- **Features:**
  - Dynamic subject input using **1D Arrays** (`String[] subjectNames`, `double[] marks`)
  - Input validation: verifies scores are within $0.0 \dots 100.0$
  - Computes:
    - Total marks obtained and maximum possible marks
    - Average percentage score
    - Letter Grade (`A+`, `A`, `B`, `C`, `D`, `F`) via `if-else if-else` grading ladder
    - Highest and lowest scoring subjects using array linear scan
    - Pass/Fail status per subject and overall
  - Formatted ASCII tabular report card output

### 4. Java Fundamentals Concept Tour (`JavaBasicsDemo.java`)
- An interactive walk-through module presenting clean, live console demonstrations of:
  - All 8 primitive data types & reference types
  - Implicit (widening) and explicit (narrowing) type casting
  - Arithmetic, relational, logical, ternary, and increment operators
  - `if-else` and `switch` statements
  - `for`, `while`, `do-while`, and enhanced `for-each` loops
  - Array memory layout and linear search
  - Method parameters, return types, and method overloading

---

## 🧠 Core Concepts Learned & Demonstrated

| Concept | Implementation in Code |
| :--- | :--- |
| **Java Syntax & Classes** | Package declarations (`package com.taskone`), imports, PascalCase class naming, camelCase methods/variables. |
| **Variables & Types** | `int`, `double`, `boolean`, `char`, `String`, arrays (`double[]`, `String[]`). |
| **Type Casting** | Widening `int -> double` for accurate decimal percentages; Narrowing `double -> int` with `(int)` cast. |
| **Operators** | Arithmetic (`+ - * / %`), Relational (`== != < > <= >=`), Logical (`&& || !`), Ternary (`cond ? expr1 : expr2`). |
| **Conditionals** | `if-else if-else` ladders for grades, `switch-case` for menu routing and operations. |
| **Loops** | `do-while` for replay loops; `while` for input validation & guessing rounds; `for` & enhanced `for-each` for array aggregation. |
| **Arrays** | 1D arrays for subject names and marks, boundary protection, finding min/max score. |
| **Methods** | Modular decomposition, single-responsibility principle, parameter passing, return values, method overloading. |
| **Exception Handling** | `try-catch` with `NumberFormatException` in `InputHelper` to eliminate application crashes from invalid inputs. |

---

## 🤖 AI-Assisted Learning Highlights

Refer to [`AI_LEARNING_GUIDE.md`](file:///c:/Users/omraj/Downloads/task%201/AI_LEARNING_GUIDE.md) for full documentation on:
1. **Prompts used with ChatGPT / Gemini** to break down concepts into intuitive mental models.
2. **Error Debugging:** Fixing the classic `Scanner.nextInt()` newline skipping pitfall, `InputMismatchException`, and off-by-one `ArrayIndexOutOfBoundsException`.
3. **Clean Code Refactoring:** Moving procedural spaghetti code into reusable, modular methods adhering to clean code standards.

---

## 👨‍💻 Author & Acknowledgments
- **Project:** Task 1 - Java Fundamentals
- **Tools:** Java 23, IntelliJ IDEA / Eclipse, Google Gemini AI / ChatGPT
