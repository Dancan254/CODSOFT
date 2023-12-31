package GuessGame;

import java.util.*;

public class Main {
    private static final Random random = new Random();
    private static final Scanner scanner = new Scanner(System.in);
    private static int maxAttempts;
    private static final List<Integer> guessList = new ArrayList<>();

    public static void main(String[] args) {
        displayWelcomeMessage();
        guessGame();
    }

    public static void displayWelcomeMessage() {
        System.out.println("""
            Welcome to the Guessing Game!!!
            ================================
            Test your intuition and guessing skills as you try to guess
            the hidden number in this exciting game.
            You'll be challenged to pick the right number within a specified range,
            and the clock will be ticking!
            Get ready for an adventure of numbers and fun.
            ================================
            Rules:
            - You'll be asked to choose a difficulty level.
            - You have a limited number of attempts to guess the correct number.
            - The game will provide hints to guide your guesses.
            - Try to guess the number as quickly as possible to earn a higher score.
            ================================
            Let's get started! Good luck!)""");
    }

    public static void guessGame() {
        int score = 0;
        do {
            int trials = 0;
            int range = difficultyLevel();
            int randomNumber = generateRandomNumber(1, range);
            long startTime = System.currentTimeMillis();

            while (trials < maxAttempts) {
                System.out.println("Guessed numbers " + guessList);
                System.out.println("Number of attempts remaining: " + (maxAttempts - trials));

                int guessedNumber = guessNumber(range);

                if (guessedNumber == randomNumber) {
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;

                    System.out.println("Congratulations, you got it!");
                    System.out.println("Total time taken: " + duration + "ms");
                    score++;
                    break;
                } else if (guessedNumber < randomNumber) {
                    System.out.println("Too low, try again.");
                } else {
                    System.out.println("Too high, try again.");
                }

                trials++;
            }

            System.out.println("The correct number was " + randomNumber);
            System.out.println("Total trials: " + trials);
            if (trials <= 5){
                System.out.println("Your score is: " + (score * 5));
            }
            else {
                System.out.println("Your score is " + (score * 2));
            }
            System.out.println("Do you want to give it another attempt? (yes/no)");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")) {
                System.out.println("Thank you for playing. Final score is: " + score);
                break;
            }
        } while (true);
    }

    public static int difficultyLevel() {
        System.out.println("""
            Choose the level of difficulty:
            1. Easy (1-50)
            2. Medium (1-100)
            3. Hard (1-200)""");

        int choice = 0;
        while (true) {
            try {
                choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        maxAttempts = 15;
                        return 50;
                    }
                    case 2 -> {
                        maxAttempts = 10;
                        return 100;
                    }
                    case 3 -> {
                        maxAttempts = 8;
                        return 200;
                    }
                    default -> {
                        System.out.println("Invalid choice. Setting to medium difficulty.");
                        maxAttempts = 10;
                        return 100;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }

    public static int guessNumber(int range) {
        System.out.println("Guess a number between 1 and " + range);
        int guessedNumber;

        while (true) {
            try {
                guessedNumber = scanner.nextInt();

                if (guessedNumber < 1 || guessedNumber > range) {
                    System.out.println("Out of range. Please enter a number within the given range.");
                } else {
                    guessList.add(guessedNumber);
                    return guessedNumber;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }
    }


    public static int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
