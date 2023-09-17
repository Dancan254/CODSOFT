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
        boolean playAgain;

        System.out.println("Caution the game has a maximum of " + maxAttempts + " attempts!!!");

        do{
            //random number between 1 and 100
            int randomNumber = generateRandomNumber(1, 101);
            int trials = 0;

            while (trials <= maxAttempts) {
                trials++;

                int guessedNumber = guessNumber();
                //compare user guess with the random number, determine if low, equal or higher
                if (guessedNumber == randomNumber) {
                    System.out.println("Congratulation you got it!");
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
            String userAnswer = scanner.next().toLowerCase();
            playAgain = userAnswer.equals("yes");

        }while (playAgain);

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