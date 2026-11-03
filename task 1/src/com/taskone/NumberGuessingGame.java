package com.taskone;

import java.util.Random;

/**
 * Program 2: Interactive Number Guessing Game
 *
 * Concepts Demonstrated:
 *  - Variables, Constants, and Data Types (int, boolean, Random)
 *  - Random Number Generation (java.util.Random)
 *  - Relational and Logical Operators (==, !=, <, >, &&, ||)
 *  - Conditional Statements (if-else if-else chains, ternary operator)
 *  - Loops (while loops for guess validation, do-while for replay)
 *  - Game State Tracking (attempts, rounds won, best score)
 *  - Modular Method Architecture
 */
public class NumberGuessingGame {

    private static final Random random = new Random();

    // Game session metrics
    private static int totalRounds = 0;
    private static int roundsWon = 0;
    private static int bestScore = Integer.MAX_VALUE; // lowest attempts to win

    /**
     * Standalone main method to run the game directly.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        run();
    }

    /**
     * Main launcher for the Number Guessing Game.
     */
    public static void run() {
        System.out.println("==================================================");
        System.out.println("            NUMBER GUESSING ADVENTURE             ");
        System.out.println("==================================================");
        System.out.println("Welcome! Test your intuition against the computer.");

        boolean keepPlaying = true;

        while (keepPlaying) {
            printDifficultyMenu();
            int difficulty = InputHelper.readInt("Select difficulty (1-4): ", 1, 4);

            if (difficulty == 4) {
                System.out.println("\nReturning to main menu...");
                break;
            }

            // Configure game range and attempt limits based on difficulty
            int min = 1;
            int max;
            int maxAttempts;

            switch (difficulty) {
                case 1: // Easy
                    max = 50;
                    maxAttempts = 10;
                    System.out.println("\n[Mode: Easy] Range: 1 to 50 | Max Attempts: 10");
                    break;
                case 2: // Medium
                    max = 100;
                    maxAttempts = 7;
                    System.out.println("\n[Mode: Medium] Range: 1 to 100 | Max Attempts: 7");
                    break;
                case 3: // Hard
                    max = 200;
                    maxAttempts = 5;
                    System.out.println("\n[Mode: Hard] Range: 1 to 200 | Max Attempts: 5");
                    break;
                default:
                    max = 100;
                    maxAttempts = 7;
            }

            playRound(min, max, maxAttempts);

            displaySessionSummary();

            keepPlaying = InputHelper.readConfirmation("\nWould you like to play another round?");
            System.out.println();
        }

        System.out.println("Thanks for playing Number Guessing Game!");
    }

    /**
     * Displays difficulty levels.
     */
    private static void printDifficultyMenu() {
        System.out.println("\n--- Choose Difficulty ---");
        System.out.println("  1. Easy   (Range: 1 - 50,  Attempts: 10)");
        System.out.println("  2. Medium (Range: 1 - 100, Attempts: 7)");
        System.out.println("  3. Hard   (Range: 1 - 200, Attempts: 5)");
        System.out.println("  4. Back to Main Menu");
    }

    /**
     * Executes a single round of the game.
     *
     * @param min         Lower bound of the target range
     * @param max         Upper bound of the target range
     * @param maxAttempts Allowed number of guesses
     */
    private static void playRound(int min, int max, int maxAttempts) {
        // Generate target secret number: [min, max]
        int secretNumber = generateRandomNumber(min, max);
        int attemptsTaken = 0;
        boolean hasWon = false;

        System.out.println("A secret number has been generated. Let's begin!");

        while (attemptsTaken < maxAttempts) {
            int remaining = maxAttempts - attemptsTaken;
            System.out.printf("%nAttempt %d of %d (Remaining: %d)%n",
                    (attemptsTaken + 1), maxAttempts, remaining);

            int guess = InputHelper.readInt(String.format("Enter your guess [%d - %d]: ", min, max), min, max);
            attemptsTaken++;

            // Evaluation using conditional statements
            if (guess == secretNumber) {
                System.out.println("🎉 BINGO! You guessed the correct number!");
                System.out.printf("You won in %d %s!%n",
                        attemptsTaken, (attemptsTaken == 1 ? "attempt" : "attempts"));
                hasWon = true;

                // Update best score
                if (attemptsTaken < bestScore) {
                    bestScore = attemptsTaken;
                    System.out.println("🌟 New Personal Best Score recorded!");
                }
                break;
            } else if (guess < secretNumber) {
                System.out.print("📉 Too LOW! ");
                printProximityHint(secretNumber, guess);
            } else {
                System.out.print("📈 Too HIGH! ");
                printProximityHint(secretNumber, guess);
            }
        }

        totalRounds++;
        if (hasWon) {
            roundsWon++;
        } else {
            System.out.printf("%n😢 Out of attempts! Game Over for this round.%n");
            System.out.printf("The secret number was: %d%n", secretNumber);
        }
    }

    /**
     * Generates a random integer between min and max (inclusive).
     */
    public static int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * Gives an intuitive hot/warm/cold proximity hint to the player.
     *
     * @param secret Secret target number
     * @param guess  Player's guess
     */
    private static void printProximityHint(int secret, int guess) {
        int difference = Math.abs(secret - guess);
        if (difference <= 3) {
            System.out.println("(🔥 Boiling hot! You are extremely close!)");
        } else if (difference <= 10) {
            System.out.println("(♨️ Warm! Within 10 numbers.)");
        } else if (difference <= 25) {
            System.out.println("(❄️ Cool. Still a bit far.)");
        } else {
            System.out.println("(🧊 Freezing cold! Way off.)");
        }
    }

    /**
     * Displays cumulative game session metrics.
     */
    private static void displaySessionSummary() {
        System.out.println("\n----------------- SESSION STATS -----------------");
        System.out.printf("  Total Rounds Played : %d%n", totalRounds);
        System.out.printf("  Rounds Won          : %d%n", roundsWon);
        double winRate = totalRounds > 0 ? ((double) roundsWon / totalRounds) * 100 : 0.0;
        System.out.printf("  Win Rate            : %.1f%%%n", winRate);
        if (bestScore != Integer.MAX_VALUE) {
            System.out.printf("  Best Score (Fewest) : %d attempts%n", bestScore);
        } else {
            System.out.println("  Best Score          : None yet");
        }
        System.out.println("-------------------------------------------------");
    }
}
