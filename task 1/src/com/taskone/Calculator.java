package com.taskone;

/**
 * Program 1: Console-based Arithmetic and Scientific Calculator
 *
 * Concepts Demonstrated:
 *  - Variables and Primitive Data Types (double, int, boolean)
 *  - Arithmetic and Relational Operators (+, -, *, /, %, Math functions)
 *  - Conditional Statements (switch-case, if-else)
 *  - Loops (do-while loop for repetitive operations)
 *  - Methods and Method Overloading (modular arithmetic functions)
 *  - Exception Prevention (handling division by zero & negative roots)
 */
public class Calculator {

    /**
     * Entry point to launch the Calculator application standalone.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        run();
    }

    /**
     * Main execution loop for the Calculator.
     */
    public static void run() {
        boolean continueCalculating = true;

        System.out.println("==================================================");
        System.out.println("             JAVA CONSOLE CALCULATOR              ");
        System.out.println("==================================================");

        do {
            printMenu();
            int choice = InputHelper.readInt("Select an option (1-9): ", 1, 9);

            if (choice == 9) {
                System.out.println("\nReturning to main menu...");
                break;
            }

            executeOperation(choice);

            continueCalculating = InputHelper.readConfirmation("\nPerform another calculation?");
            System.out.println();
        } while (continueCalculating);

        System.out.println("Thank you for using Java Console Calculator!");
    }

    /**
     * Displays available calculator operations.
     */
    private static void printMenu() {
        System.out.println("\n--- Calculator Operations ---");
        System.out.println("  1. Addition (+)");
        System.out.println("  2. Subtraction (-)");
        System.out.println("  3. Multiplication (*)");
        System.out.println("  4. Division (/)");
        System.out.println("  5. Modulus / Remainder (%)");
        System.out.println("  6. Exponentiation / Power (x^y)");
        System.out.println("  7. Square Root (√x)");
        System.out.println("  8. Sum of Three Numbers (Method Overloading Demo)");
        System.out.println("  9. Back to Main Menu");
    }

    /**
     * Executes the chosen arithmetic operation based on user selection.
     * Demonstrates the switch-case control structure.
     *
     * @param choice User's menu choice
     */
    private static void executeOperation(int choice) {
        switch (choice) {
            case 1: {
                double a = InputHelper.readDouble("Enter first number: ");
                double b = InputHelper.readDouble("Enter second number: ");
                double result = add(a, b);
                System.out.printf(">> Result: %.4f + %.4f = %.4f%n", a, b, result);
                break;
            }
            case 2: {
                double a = InputHelper.readDouble("Enter first number: ");
                double b = InputHelper.readDouble("Enter second number: ");
                double result = subtract(a, b);
                System.out.printf(">> Result: %.4f - %.4f = %.4f%n", a, b, result);
                break;
            }
            case 3: {
                double a = InputHelper.readDouble("Enter first number: ");
                double b = InputHelper.readDouble("Enter second number: ");
                double result = multiply(a, b);
                System.out.printf(">> Result: %.4f * %.4f = %.4f%n", a, b, result);
                break;
            }
            case 4: {
                double a = InputHelper.readDouble("Enter numerator (dividend): ");
                double b = InputHelper.readDouble("Enter denominator (divisor): ");
                if (b == 0.0) {
                    System.out.println(">> [Math Error] Division by zero is undefined in mathematics!");
                } else {
                    double result = divide(a, b);
                    System.out.printf(">> Result: %.4f / %.4f = %.4f%n", a, b, result);
                }
                break;
            }
            case 5: {
                double a = InputHelper.readDouble("Enter first number: ");
                double b = InputHelper.readDouble("Enter second number: ");
                if (b == 0.0) {
                    System.out.println(">> [Math Error] Modulo by zero is undefined!");
                } else {
                    double result = modulus(a, b);
                    System.out.printf(">> Result: %.4f %% %.4f = %.4f%n", a, b, result);
                }
                break;
            }
            case 6: {
                double base = InputHelper.readDouble("Enter base (x): ");
                double exponent = InputHelper.readDouble("Enter exponent (y): ");
                double result = power(base, exponent);
                System.out.printf(">> Result: %.4f ^ %.4f = %.4f%n", base, exponent, result);
                break;
            }
            case 7: {
                double num = InputHelper.readDouble("Enter a non-negative number: ");
                if (num < 0) {
                    System.out.println(">> [Math Error] Square root of a negative number is imaginary (complex).");
                } else {
                    double result = squareRoot(num);
                    System.out.printf(">> Result: √%.4f = %.4f%n", num, result);
                }
                break;
            }
            case 8: {
                System.out.println("[Demonstrating Method Overloading: add(a, b, c)]");
                double a = InputHelper.readDouble("Enter first number: ");
                double b = InputHelper.readDouble("Enter second number: ");
                double c = InputHelper.readDouble("Enter third number: ");
                double result = add(a, b, c);
                System.out.printf(">> Result: %.4f + %.4f + %.4f = %.4f%n", a, b, c, result);
                break;
            }
            default:
                System.out.println(">> Invalid choice.");
        }
    }

    // ========================================================
    // Modular Arithmetic Methods
    // ========================================================

    /**
     * Adds two double numbers.
     */
    public static double add(double a, double b) {
        return a + b;
    }

    /**
     * Method Overload: Adds three double numbers.
     */
    public static double add(double a, double b, double c) {
        return a + b + c;
    }

    /**
     * Subtracts b from a.
     */
    public static double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Multiplies two numbers.
     */
    public static double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Divides a by b.
     */
    public static double divide(double a, double b) {
        return a / b;
    }

    /**
     * Computes the remainder of a divided by b.
     */
    public static double modulus(double a, double b) {
        return a % b;
    }

    /**
     * Computes base raised to the power exponent.
     */
    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    /**
     * Computes the square root of a number.
     */
    public static double squareRoot(double number) {
        return Math.sqrt(number);
    }
}
