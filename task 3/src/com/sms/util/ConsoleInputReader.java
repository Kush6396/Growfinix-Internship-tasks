package com.sms.util;

import java.util.Scanner;

/**
 * Robust console input reader that handles edge cases, prevents scanner buffer leaks,
 * and retries until valid input or cancellation is provided.
 */
public class ConsoleInputReader {
    private final Scanner scanner;

    public ConsoleInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Reads a non-empty string line from console.
     */
    public String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    return line;
                }
            }
            System.out.println("  [Error] Input cannot be blank. Please try again.");
        }
    }

    /**
     * Reads an optional string line. Returns empty string if the user just presses Enter.
     */
    public String readOptionalString(String prompt) {
        System.out.print(prompt);
        String line = scanner.nextLine();
        return line != null ? line.trim() : "";
    }

    /**
     * Safely reads an integer with retry on InputMismatchException / NumberFormatException.
     */
    public int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  [Error] Invalid input. Please enter a valid whole number.");
            }
        }
    }

    /**
     * Safely reads an integer within a specified range [min, max].
     */
    public int readIntInRange(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.printf("  [Error] Value must be between %d and %d. Please try again.\n", min, max);
        }
    }

    /**
     * Safely reads an optional integer. If blank, returns null.
     */
    public Integer readOptionalInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("  [Error] Invalid input. Please enter a valid whole number or press Enter to skip.");
            }
        }
    }

    /**
     * Safely reads a double with retry on format errors.
     */
    public double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("  [Error] Invalid input. Please enter a valid decimal number (e.g., 3.75).");
            }
        }
    }

    /**
     * Safely reads an optional double. If blank, returns null.
     */
    public Double readOptionalDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("  [Error] Invalid input. Please enter a valid decimal number or press Enter to skip.");
            }
        }
    }

    /**
     * Safely reads a boolean confirmation (yes/no, y/n).
     */
    public boolean readConfirmation(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.println("  [Error] Please answer 'y' (yes) or 'n' (no).");
        }
    }
}
