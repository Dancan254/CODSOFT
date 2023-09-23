package GuessGame;

import java.util.Random;
import java.util.Scanner;

public class Main {

    private static  Random random = new Random();
    private static  Scanner scanner = new Scanner(System.in);
    private static int maxAttempts = 10;

    public static void main(String[] args) {

        guessGame();
    }

    //method for the game
    public static void guessGame() {

        int score = 0;
        //boolean playAgain;

        System.out.println("Caution the game has a maximum of " + maxAttempts + " attempts!!!");

        do{
            //random number between 1 and 100
            int randomNumber = generateRandomNumber(1, 100);
            //System.out.println(randomNumber);
            int trials = 0;
            long startTime = System.currentTimeMillis();
            while (trials <= maxAttempts) {
                trials++;

                int guessedNumber = guessNumber();
                //compare user guess with the random number, determine if low, equal or higher
                if (guessedNumber == randomNumber) {

                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;

                    System.out.println("Congratulation you got it!");
                    System.out.println("Total time taken: " + duration);
                    score++;
                    break;
                } else if (guessedNumber < randomNumber) {
                    System.out.println("Too low, Try again");
                } else {
                    System.out.println("Too high, Try again");
                }
            }
            System.out.println("Your score is: " + score);
            System.out.println("total trials:" + trials);

            //ask if the player wants to play again
            System.out.println("Do you want to give it another attempt? (yes/no)");
            String playAgain = scanner.next().toLowerCase();
            if (!playAgain.equals("yes")){
                System.out.println("Thank you for playing, Final score is = " + score);
                break;
            }

        }while (true);

    }

    //method to prompt user to enter number, returns the number
    public static int guessNumber(){
        System.out.println("Guess a number between 1 and 100: ");
       return scanner.nextInt();
    }

    //method to generate random number, returns the number
    public static int generateRandomNumber(int min, int max){
        //determine the boundaries
        return random.nextInt(max - min) + min;
    }
}