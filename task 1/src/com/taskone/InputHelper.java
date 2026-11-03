package com.taskone;

import java.util.Scanner;

/**
 * Utility class providing robust, validated user input methods.
 * Demonstrates:
 *  - Method reusability and encapsulation
 *  - Handling the common Scanner newline consumption pitfall
 *  - Input validation and exception handling
 */
public class InputHelper {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private InputHelper() {
    }

    /**
     * Reads a non-empty string line from the console.
     *
     * @param prompt Prompt message to display to the user
     * @return Validated non-empty string
     */
    public static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("  [Error] Input cannot be blank. Please try again.");
        }
    }

    /**
     * Reads an integer within an inclusive range [min, max].
     *
     * @param prompt Prompt message to display
     * @param min    Minimum acceptable value
     * @param max    Maximum acceptable value
     * @return Validated integer
     */
    public static int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("  [Error] Please enter an integer between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("  [Error] Invalid integer. Please enter digits only.");
            }
        }
    }

    /**
     * Reads any valid integer.
     *
     * @param prompt Prompt message
     * @return Validated integer
     */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  [Error] Invalid integer. Please enter numeric digits.");
            }
        }
    }

    /**
     * Reads a double value within an inclusive range [min, max].
     *
     * @param prompt Prompt message
     * @param min    Minimum acceptable value
     * @param max    Maximum acceptable value
     * @return Validated double value
     */
    public static double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("  [Error] Value must be between %.2f and %.2f.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("  [Error] Invalid number. Please enter a valid decimal number.");
            }
        }
    }

    /**
     * Reads any valid double value.
     *
     * @param prompt Prompt message
     * @return Validated double value
     */
    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("  [Error] Invalid number. Please enter a valid decimal number.");
            }
        }
    }

    /**
     * Reads a yes/no confirmation from the user.
     *
     * @param prompt Prompt message
     * @return true if user answered 'y' or 'yes', false if 'n' or 'no'
     */
    public static boolean readConfirmation(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("  [Error] Please answer 'y' for yes or 'n' for no.");
        }
    }

    /**
     * Pauses console execution until user presses Enter.
     */
    public static void pause() {
        System.out.println("\nPress [Enter] to continue...");
        scanner.nextLine();
    }
}
