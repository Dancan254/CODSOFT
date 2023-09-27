package GuessGame;

import java.util.*;

public class Main {
    private static Random random = new Random();
    private static Scanner scanner = new Scanner(System.in);
    private static int maxAttempts = 10;

    public static void main(String[] args) {
        displayWelcomeMessage();
        guessGame();
    }

    //method to display welcome message and instuctions
    public static void displayWelcomeMessage() {

     System.out.println("""
                                Welcome to the Guessing Game!!!
                                ================================
              Test your intuition and guessing skills as you try to guess
              the hidden number in this exciting game
              You'll be challenged to pick the right number within a specified range
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

    //method for the game
    public static void guessGame() {

        int score = 0;
        //System.out.println("Guess a number between 1 and " + range);

        List<Integer> guessList = new ArrayList<>();

        do{
            int trials = 0;
            int range = difficultyLevel();
            //random number between 1 and given range
            int randomNumber = generateRandomNumber(1, range);
            long startTime = System.currentTimeMillis();
            while (trials < maxAttempts) {
                System.out.println("Guessed numbers " + guessList);
                System.out.println("Number of attempts remaining: " + (maxAttempts - trials));
                int guessedNumber = guessNumber(range);
                guessList.add(guessedNumber);
                trials++;
                try {
                    //compare user guess with the random number, determine if low, equal or higher
                    if (guessedNumber == randomNumber) {
                        long endTime = System.currentTimeMillis();
                        long duration = endTime - startTime;

                        System.out.println("Congratulation you got it!");
                        System.out.println("Total time taken: " + duration + "ms");
                        score++;
                        break;
                    } else if (guessedNumber < randomNumber) {
                        System.out.println("Too low, Try again");
                    } else {
                        System.out.println("Too high, Try again");
                    }

                }catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("The correct number was " + randomNumber);
            System.out.println("Your score is: " + score);
            System.out.println("total trials:" + trials);

            System.out.println("Do you want to give it another attempt? (yes/no)");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")){
                System.out.println("Thank you for playing, Final score is = " + score);
                break;
            }
        }while (true);

    }
    //method for users to choose the difficulty they want
    //returns an integer
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
                System.out.println("Invalid input. try again");
                scanner.nextLine();
            }
        }
    }

    //method to prompt user to enter number, returns the number
    public static int guessNumber(int range) {
        System.out.println("Guess a number between 1 and " + range);
        int guessedNumb = 0;
            try {
                guessedNumb = scanner.nextInt();
                if (guessedNumb < 1 || guessedNumb > range){
                    //throw new IllegalArgumentException("Enter a number within the range");
                    System.out.println("Out of range. Please enter a number within the given range");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage() + " try again");
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume the invalid input
                guessedNumb = guessNumber(range); // Recursive call to retry
            }
        return guessedNumb;
    }

    //method to generate random number, returns the number
    public static int generateRandomNumber(int min, int max){
        //determine the boundaries
        return random.nextInt(max - min) + min;
    }
}