package StudentGradeCalculator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

       // System.out.println("Enter the number of subjects: ");
        int subjectsNum = 0;
//
//        do {
//            System.out.println("Enter the number of subjects: ");
//            subjectsNum = scanner.nextInt();
//
//            if (subjectsNum <= 0) {
//                System.out.println("Number of subjects must be a positive integer.");
//            }
//        } while (subjectsNum <= 0);

        //implementing try and catch
        while (subjectsNum <= 0) {
            try {
                System.out.println("Enter the number of subjects: ");
                subjectsNum = scanner.nextInt();

                if (subjectsNum <= 0) {
                    System.out.println("Number of subjects must be a positive integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); // Consume the invalid input
            }
        }

        System.out.println("Marks entered are out of 100");
        //create an array of subjects of size num of subjects
        String[] subjects = new String[subjectsNum];
        //create an array of marks for each subject
        int[] subjectMarks = new int[subjectsNum];

        //loop through each subject prompting user to enter marks
        for (int i = 0; i < subjectsNum; i++) {
            scanner.nextLine();//consume new line
            System.out.print("Enter subject" + (i + 1) + " name: ");
            subjects[i] = scanner.nextLine();
            System.out.print("Enter marks for " + subjects[i] + ":");
             subjectMarks[i] = scanner.nextInt();
            //scanner.nextLine();//consume new line
        }

        //calculate the total marks
        //iterate through all the marks
        int totalMarks = 0;
        for (int marks : subjectMarks){
            totalMarks += marks;
        }

        System.out.println();
        //display subjects and marks
        for (int i = 0; i < subjectsNum; i++) {
            System.out.printf("%-12s\t%d%n", subjects[i], subjectMarks[i]);
        }


        //calculate average, cast since subjectsNum is an integer
        int average =  totalMarks / subjectsNum;
        System.out.println("Total Marks = " + totalMarks);
        System.out.println("Average = " + average);
        System.out.print("Grade:");
        calculateGrade(average);

        scanner.close();
    }

    //method to check grade
    public static void calculateGrade(int averageMarks){

        if (averageMarks >= 90 && averageMarks <= 100) {
            System.out.println("Scored an A");
            System.out.println("Excellent, passed with flying colors");
        } else if (averageMarks >= 80 && averageMarks < 90) {
            System.out.println("Scored an A-");
            System.out.println("Congratulations, but still not yet there");
        } else if (averageMarks >= 70 && averageMarks < 80) {
            System.out.println("Scored a B+");
            System.out.println("Great, the sky is not the limit keep pressing");
        } else if (averageMarks >= 65 && averageMarks < 70) {
            System.out.println("Scored a B");
            System.out.println("Well done");
        } else if (averageMarks >= 60 && averageMarks < 65) {
            System.out.println("Scored a B-");
            System.out.println("good, keep soaring");
        } else if (averageMarks >= 55 && averageMarks < 60) {
            System.out.println("Scored a C+");
            System.out.println("Can do better, aim higher");
        } else if (averageMarks >= 50 && averageMarks < 55) {
            System.out.println("Scored a C");
            System.out.println("You have potential, average");
        } else if (averageMarks >= 45 && averageMarks < 50) {
            System.out.println("Scored a C-");
            System.out.println("Put in more effort");
        } else if (averageMarks >= 40 && averageMarks < 45) {
            System.out.println("Scored a D+");
            System.out.println("Work harder");
        } else if (averageMarks >= 35 && averageMarks < 40) {
            System.out.println("Scored a D");
            System.out.println("You can do better");
        } else if (averageMarks >= 30 && averageMarks < 35) {
            System.out.println("Scored a D-");
            System.out.println("Weak but has potential");
        } else if (averageMarks >= 0 && averageMarks < 30) {
            System.out.println("Scored an E");
            System.out.println("Failed");
        } else {
            System.out.println("Invalid grade");
        }


    }
}
