package com.taskone;

/**
 * Task 1: Master Launcher & Application Dashboard
 *
 * Tech Stack:
 *  - Language: Java (JDK 17 / 21 / 23+)
 *  - IDE: IntelliJ IDEA / Eclipse / VS Code
 *  - AI Tooling: ChatGPT / Gemini AI (Concepts, Debugging, Readability)
 *
 * Core Topics Demonstrated:
 *  - Java Syntax & Class Structures
 *  - Variables & Data Types (Primitive & Reference)
 *  - Operators (Arithmetic, Relational, Logical, Ternary)
 *  - Conditional Statements (if-else ladders, switch-case)
 *  - Loops (for, enhanced for, while, do-while, break, continue)
 *  - Arrays (1D arrays, indexing, bounds, aggregations)
 *  - Methods (modular decomposition, parameters, return types, overloading)
 *  - Robust Error Handling & Console I/O
 */
public class Main {

    public static void main(String[] args) {
        displayWelcomeBanner();

        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = InputHelper.readInt("Enter your choice (1-6): ", 1, 6);

            System.out.println();
            switch (choice) {
                case 1:
                    Calculator.run();
                    break;
                case 2:
                    NumberGuessingGame.run();
                    break;
                case 3:
                    StudentMarksCalculator.run();
                    break;
                case 4:
                    JavaBasicsDemo.run();
                    break;
                case 5:
                    displayProjectInfo();
                    break;
                case 6:
                    running = false;
                    displayExitMessage();
                    break;
                default:
                    System.out.println("Invalid selection. Please choose an option between 1 and 6.");
            }

            if (running) {
                System.out.println();
            }
        }
    }

    private static void displayWelcomeBanner() {
        System.out.println("****************************************************************");
        System.out.println("*                                                              *");
        System.out.println("*          JAVA FUNDAMENTALS & PRACTICAL MINI-PROJECTS         *");
        System.out.println("*            Task 1: Core Java Programming Showcase            *");
        System.out.println("*                                                              *");
        System.out.println("****************************************************************");
    }

    private static void displayMainMenu() {
        System.out.println("================================================================");
        System.out.println("                         MAIN DASHBOARD                         ");
        System.out.println("================================================================");
        System.out.println("  [1] Scientific & Arithmetic Calculator");
        System.out.println("  [2] Number Guessing Game");
        System.out.println("  [3] Student Marks & Grade Calculator");
        System.out.println("  [4] Java Fundamentals Concept Tour (Live Demos)");
        System.out.println("  [5] Project Architecture & Tech Stack Info");
        System.out.println("  [6] Exit Application");
        System.out.println("================================================================");
    }

    private static void displayProjectInfo() {
        System.out.println("================================================================");
        System.out.println("                  PROJECT & TECH STACK INFO                     ");
        System.out.println("================================================================");
        System.out.println("• Tech Stack: Java, JDK 23, IntelliJ IDEA / Eclipse, Gemini AI");
        System.out.println("• Objectives Covered:");
        System.out.println("   ✓ Java Syntax: Clean structure, packages, naming conventions");
        System.out.println("   ✓ Variables & Types: byte, int, long, double, char, boolean, String");
        System.out.println("   ✓ Operators: +, -, *, /, %, ==, !=, <, >, &&, ||, ?, ++, --");
        System.out.println("   ✓ Conditionals: if-else ladders, switch-case with fall-through safety");
        System.out.println("   ✓ Loops: for, while, do-while, enhanced for-each, break, continue");
        System.out.println("   ✓ Arrays: 1D arrays for subject marks, linear search for min/max");
        System.out.println("   ✓ Methods: Modular functions, overloading, parameter passing");
        System.out.println("   ✓ AI Integration: Concept clarification, bug detection, refactoring");
        System.out.println("================================================================");
        InputHelper.pause();
    }

    private static void displayExitMessage() {
        System.out.println("================================================================");
        System.out.println("Thank you for using the Java Fundamentals Showcase Suite!");
        System.out.println("Happy Coding! ✨");
        System.out.println("================================================================");
    }
}
