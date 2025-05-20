import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static int totalRounds = 0;
    private static int totalScore = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("I'm thinking of a number between " + MIN_NUMBER + " and " + MAX_NUMBER);

        while (playAgain) {
            playRound(scanner);
            System.out.print("\nWould you like to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        }

        displayFinalScore();
        scanner.close();
    }

    private static void playRound(Scanner scanner) {
        Random random = new Random();
        int targetNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
        int attempts = 0;
        boolean hasWon = false;

        System.out.println("\nRound " + (totalRounds + 1));
        System.out.println("You have " + MAX_ATTEMPTS + " attempts to guess the number.");

        while (attempts < MAX_ATTEMPTS && !hasWon) {
            System.out.print("\nEnter your guess: ");
            
            try {
                int guess = scanner.nextInt();
                attempts++;

                if (guess < MIN_NUMBER || guess > MAX_NUMBER) {
                    System.out.println("Please enter a number between " + MIN_NUMBER + " and " + MAX_NUMBER);
                    attempts--;
                    continue;
                }

                if (guess == targetNumber) {
                    hasWon = true;
                    int roundScore = MAX_ATTEMPTS - attempts + 1;
                    totalScore += roundScore;
                    System.out.println("\nCongratulations! You've guessed the number in " + attempts + " attempts!");
                    System.out.println("Round score: " + roundScore);
                } else if (guess < targetNumber) {
                    System.out.println("Too low! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                } else {
                    System.out.println("Too high! Attempts remaining: " + (MAX_ATTEMPTS - attempts));
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number!");
                scanner.nextLine(); // Clear the invalid input
                attempts--;
            }
        }

        if (!hasWon) {
            System.out.println("\nGame Over! The number was " + targetNumber);
        }

        totalRounds++;
    }

    private static void displayFinalScore() {
        System.out.println("\n=== Game Summary ===");
        System.out.println("Total rounds played: " + totalRounds);
        System.out.println("Total score: " + totalScore);
        System.out.println("Average score per round: " + (totalRounds > 0 ? (double) totalScore / totalRounds : 0));
        System.out.println("Thank you for playing!");
    }
} 