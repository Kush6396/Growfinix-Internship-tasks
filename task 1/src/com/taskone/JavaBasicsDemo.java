package com.taskone;

/**
 * Educational Module: Java Fundamentals Interactive Tour
 *
 * Demonstrates:
 *  1. Syntax, Classes, and Comments
 *  2. Primitive and Reference Variables & Data Types
 *  3. Explicit and Implicit Type Casting
 *  4. Operators (Arithmetic, Relational, Logical, Ternary, Increment/Decrement)
 *  5. Conditional Structures (if-else, switch)
 *  6. Loops (for, enhanced for, while, do-while, break, continue)
 *  7. Arrays (creation, bounds, operations)
 *  8. Methods (parameter passing, return types, overloading)
 */
public class JavaBasicsDemo {

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        System.out.println("==================================================");
        System.out.println("          JAVA FUNDAMENTALS CONCEPT TOUR          ");
        System.out.println("==================================================");

        boolean exploring = true;

        while (exploring) {
            printTopicMenu();
            int choice = InputHelper.readInt("Select a concept to explore (1-8): ", 1, 8);

            switch (choice) {
                case 1:
                    demoVariablesAndDataTypes();
                    break;
                case 2:
                    demoOperators();
                    break;
                case 3:
                    demoTypeCasting();
                    break;
                case 4:
                    demoConditionals();
                    break;
                case 5:
                    demoLoops();
                    break;
                case 6:
                    demoArrays();
                    break;
                case 7:
                    demoMethods();
                    break;
                case 8:
                    exploring = false;
                    System.out.println("\nReturning to main menu...");
                    break;
            }

            if (exploring) {
                InputHelper.pause();
            }
        }
    }

    private static void printTopicMenu() {
        System.out.println("\n--- Topics ---");
        System.out.println("  1. Variables & Primitive Data Types");
        System.out.println("  2. Operators (Arithmetic, Relational, Logical, Ternary)");
        System.out.println("  3. Type Casting (Implicit vs Explicit)");
        System.out.println("  4. Conditional Statements (if-else, switch)");
        System.out.println("  5. Loops (for, while, do-while, enhanced for)");
        System.out.println("  6. Arrays & Traversal");
        System.out.println("  7. Methods & Overloading");
        System.out.println("  8. Back to Main Menu");
    }

    // -------------------------------------------------------------------------
    // 1. Variables & Data Types
    // -------------------------------------------------------------------------
    private static void demoVariablesAndDataTypes() {
        System.out.println("\n=== 1. VARIABLES & DATA TYPES ===");
        System.out.println("Java is a strongly-typed language. Every variable must have a declared type.");

        // 8 Primitive Data Types
        byte byteVal = 127;                          // 8-bit integer (-128 to 127)
        short shortVal = 32767;                      // 16-bit integer
        int intVal = 2147483647;                     // 32-bit integer (standard choice)
        long longVal = 9223372036854775807L;         // 64-bit integer (note 'L' suffix)
        float floatVal = 3.14159f;                   // 32-bit IEEE 754 float ('f' suffix)
        double doubleVal = 2.718281828459;           // 64-bit float (standard for decimals)
        char charVal = 'J';                          // 16-bit Unicode character (single quotes)
        boolean boolVal = true;                      // true or false

        // Reference Data Type
        String stringVal = "Welcome to Java!";       // Sequence of characters (double quotes)

        System.out.println("\nPrimitive Data Types:");
        System.out.printf("  byte    : %d (Size: 1 byte / 8 bits)%n", byteVal);
        System.out.printf("  short   : %d (Size: 2 bytes / 16 bits)%n", shortVal);
        System.out.printf("  int     : %d (Size: 4 bytes / 32 bits)%n", intVal);
        System.out.printf("  long    : %d (Size: 8 bytes / 64 bits)%n", longVal);
        System.out.printf("  float   : %f (Size: 4 bytes)%n", floatVal);
        System.out.printf("  double  : %f (Size: 8 bytes)%n", doubleVal);
        System.out.printf("  char    : %c (Unicode value: %d)%n", charVal, (int) charVal);
        System.out.printf("  boolean : %b%n", boolVal);

        System.out.println("\nReference Type:");
        System.out.printf("  String  : \"%s\" (Length: %d)%n", stringVal, stringVal.length());
    }

    // -------------------------------------------------------------------------
    // 2. Operators
    // -------------------------------------------------------------------------
    private static void demoOperators() {
        System.out.println("\n=== 2. OPERATORS ===");

        int a = 15;
        int b = 4;

        System.out.println("Given a = 15, b = 4:");

        System.out.println("\n[A] Arithmetic Operators:");
        System.out.printf("  Addition       (a + b) = %d%n", (a + b));
        System.out.printf("  Subtraction    (a - b) = %d%n", (a - b));
        System.out.printf("  Multiplication (a * b) = %d%n", (a * b));
        System.out.printf("  Integer Div    (a / b) = %d  (truncates remainder!)%n", (a / b));
        System.out.printf("  Floating Div   ((double)a / b) = %.2f%n", ((double) a / b));
        System.out.printf("  Modulus        (a %% b) = %d  (remainder)%n", (a % b));

        System.out.println("\n[B] Relational Operators (Produce boolean):");
        System.out.printf("  a == b : %b%n", (a == b));
        System.out.printf("  a != b : %b%n", (a != b));
        System.out.printf("  a > b  : %b%n", (a > b));
        System.out.printf("  a < b  : %b%n", (a < b));
        System.out.printf("  a >= b : %b%n", (a >= b));
        System.out.printf("  a <= b : %b%n", (a <= b));

        System.out.println("\n[C] Logical Operators:");
        boolean cond1 = (a > 10); // true
        boolean cond2 = (b > 10); // false
        System.out.printf("  cond1 (a > 10) = %b, cond2 (b > 10) = %b%n", cond1, cond2);
        System.out.printf("  Logical AND (cond1 && cond2) : %b%n", (cond1 && cond2));
        System.out.printf("  Logical OR  (cond1 || cond2) : %b%n", (cond1 || cond2));
        System.out.printf("  Logical NOT (!cond1)         : %b%n", (!cond1));

        System.out.println("\n[D] Ternary Operator (condition ? expr1 : expr2):");
        String maxLabel = (a > b) ? "a is strictly greater" : "b is greater or equal";
        System.out.printf("  (a > b) ? \"a is greater\" : \"b is greater\" -> %s%n", maxLabel);
    }

    // -------------------------------------------------------------------------
    // 3. Type Casting
    // -------------------------------------------------------------------------
    private static void demoTypeCasting() {
        System.out.println("\n=== 3. TYPE CASTING ===");
        System.out.println("Converting a value from one data type to another.");

        System.out.println("\n1. Implicit (Widening) Casting (Automatic, smaller to larger type):");
        System.out.println("   byte -> short -> char -> int -> long -> float -> double");
        int myInt = 42;
        double widenedDouble = myInt; // int automatically widened to double
        System.out.printf("   Original int: %d  ==>  Widened double: %.2f%n", myInt, widenedDouble);

        System.out.println("\n2. Explicit (Narrowing) Casting (Manual, larger to smaller type):");
        System.out.println("   double -> float -> long -> int -> char -> short -> byte");
        double preciseNumber = 99.9876;
        int truncatedInt = (int) preciseNumber; // fractional part discarded
        System.out.printf("   Original double: %.4f  ==>  Explicit (int) cast: %d (decimal truncated)%n",
                preciseNumber, truncatedInt);
    }

    // -------------------------------------------------------------------------
    // 4. Conditional Statements
    // -------------------------------------------------------------------------
    private static void demoConditionals() {
        System.out.println("\n=== 4. CONDITIONAL STATEMENTS ===");

        int score = 78;
        System.out.printf("Sample score = %d%n", score);

        System.out.println("\n[A] If-Else-If Ladder:");
        if (score >= 90) {
            System.out.println("  Grade: A (Honors)");
        } else if (score >= 75) {
            System.out.println("  Grade: B (Distinction) <-- MATCH");
        } else if (score >= 50) {
            System.out.println("  Grade: C (Pass)");
        } else {
            System.out.println("  Grade: F (Fail)");
        }

        System.out.println("\n[B] Switch-Case Statement:");
        int dayOfWeek = 3; // Wednesday
        String dayName;
        switch (dayOfWeek) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            case 6:
                dayName = "Saturday";
                break;
            case 7:
                dayName = "Sunday";
                break;
            default:
                dayName = "Invalid Day";
                break;
        }
        System.out.printf("  Day code %d corresponds to: %s%n", dayOfWeek, dayName);
    }

    // -------------------------------------------------------------------------
    // 5. Loops
    // -------------------------------------------------------------------------
    private static void demoLoops() {
        System.out.println("\n=== 5. LOOPS (CONTROL FLOW) ===");

        System.out.println("\n[A] Standard for Loop (1 to 5):");
        System.out.print("  ");
        for (int i = 1; i <= 5; i++) {
            System.out.printf("%d ", i);
        }
        System.out.println();

        System.out.println("\n[B] While Loop (Pre-check condition):");
        int count = 5;
        System.out.print("  Countdown: ");
        while (count > 0) {
            System.out.printf("%d... ", count);
            count--;
        }
        System.out.println("Liftoff! 🚀");

        System.out.println("\n[C] Do-While Loop (Post-check, guarantees >= 1 execution):");
        int num = 1;
        System.out.print("  Values: ");
        do {
            System.out.printf("%d ", num);
            num++;
        } while (num <= 3);
        System.out.println();

        System.out.println("\n[D] Loop Control (break and continue):");
        System.out.print("  Even numbers under 10 (skipping 6, stopping at 8): ");
        for (int i = 0; i <= 10; i += 2) {
            if (i == 6) {
                continue; // skips current iteration
            }
            if (i > 8) {
                break; // terminates the loop immediately
            }
            System.out.printf("%d ", i);
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // 6. Arrays
    // -------------------------------------------------------------------------
    private static void demoArrays() {
        System.out.println("\n=== 6. ARRAYS ===");
        System.out.println("An array is an indexed, fixed-size container holding elements of identical type.");

        // Declaration & Initialization
        int[] numbers = {10, 25, 4, 88, 37, 92, 19};

        System.out.printf("Array length: %d%n", numbers.length);
        System.out.printf("First element (index 0): %d%n", numbers[0]);
        System.out.printf("Last element  (index %d): %d%n", (numbers.length - 1), numbers[numbers.length - 1]);

        System.out.println("\nTraversing with enhanced for-each loop:");
        System.out.print("  Elements: [ ");
        for (int val : numbers) {
            System.out.printf("%d ", val);
        }
        System.out.println("]");

        // Linear Search for Maximum
        int max = numbers[0];
        int min = numbers[0];
        int sum = 0;
        for (int val : numbers) {
            if (val > max) max = val;
            if (val < min) min = val;
            sum += val;
        }
        double avg = (double) sum / numbers.length;

        System.out.printf("  Sum     : %d%n", sum);
        System.out.printf("  Average : %.2f%n", avg);
        System.out.printf("  Maximum : %d%n", max);
        System.out.printf("  Minimum : %d%n", min);
    }

    // -------------------------------------------------------------------------
    // 7. Methods
    // -------------------------------------------------------------------------
    private static void demoMethods() {
        System.out.println("\n=== 7. METHODS & OVERLOADING ===");
        System.out.println("Methods are reusable blocks of code performing a defined task.");

        System.out.println("\n[A] Method Call with Return Value:");
        int product = multiplyTwoNumbers(6, 7);
        System.out.printf("  multiplyTwoNumbers(6, 7) returned: %d%n", product);

        System.out.println("\n[B] Method Overloading (same name, distinct signatures):");
        System.out.printf("  formatGreeting(\"Alice\")           -> %s%n",
                formatGreeting("Alice"));
        System.out.printf("  formatGreeting(\"Alice\", \"Welcome\")  -> %s%n",
                formatGreeting("Alice", "Welcome back"));
    }

    public static int multiplyTwoNumbers(int x, int y) {
        return x * y;
    }

    public static String formatGreeting(String name) {
        return "Hello, " + name + "!";
    }

    public static String formatGreeting(String name, String prefix) {
        return prefix + ", " + name + "!";
    }
}
