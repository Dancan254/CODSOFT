package ATM_INTERFACE.Atm;

import ATM_INTERFACE.Bank.Account;
import ATM_INTERFACE.Bank.Bank;
import ATM_INTERFACE.User.User;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AtmInterface {
    private Bank bankAccount;

    private User userAccount;
    private Account account;
    protected double initialBalance;
    static  Scanner scanner = new Scanner(System.in);

    //default constructor
    public AtmInterface() {
    }

    public AtmInterface(User userAccount) {
        this.userAccount = userAccount;
        //initialBalance = 0;
    }

    //method to deposit
    //takes in a certain amount
    //checks: amount <= 0, validate user input
    public void depositCash(double depositAmount){
        //check if amount > 0
        while (true) {
            try {
                if (depositAmount > 0) {
                    initialBalance += depositAmount;
                    System.out.printf("Successfully deposited $%.2f%n", depositAmount);
                    showAccountBalance();
                    break;
                } else {
                    System.out.println("Invalid amount");
                }
            } catch (InputMismatchException e) {
                System.out.println("Enter a numerical value");
                scanner.nextLine();
            }
        }
    }

    public double getInitialBalance() {
        return initialBalance;
    }

    //method to withdraw cash
    public void withdrawCash(double withdrawAmount){
        while (true) {
            try {
                //check if amount > 0
                if (withdrawAmount > 0 && withdrawAmount <= initialBalance) {
                    initialBalance -= withdrawAmount;
                    System.out.printf("Successfully withdrawn $%.2f%n", withdrawAmount);
                    showAccountBalance();
                    System.out.println("Collect your cash");
                } else {
                    System.out.println("Insufficient funds");
                    showAccountBalance();
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Enter a numerical value");
                scanner.nextLine();
            }
        }
    }

    //method to check balance
    public void showAccountBalance(){
        System.out.println("Account balance: " + initialBalance);
    }
    //show account info
    public void showInfo(){
        System.out.println(userAccount.toString());
    }

    //show menu
    public void showMenu(){
        System.out.println("""
                1. Deposit
                2. Withdraw
                3. Check Balance
                4. Exit""");
        System.out.println("Enter choice: ");
    }
    public void Run(){

        while(true){
            showMenu();

            try {
                int choice = scanner.nextInt();
                switch (choice){
                    case 1 -> {
                        System.out.println("Enter amount you wish to withdraw: ");
                        double depositAmount = scanner.nextDouble();
                        depositCash(depositAmount);
                    }
                    case 2 -> {
                        System.out.println("Enter amount you wish to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        withdrawCash(withdrawAmount);
                    }
                    case 3 -> {
                        showAccountBalance();
                    }
                    case 4 -> {
                        System.out.println("Thanking for valuing our services");
                        System.exit(0);
                    }
                    default -> {
                        System.out.println("Invalid choice, enter a number in the choice list");
                    }
                }
            }catch (InputMismatchException e){
                System.out.println("Invalid input enter a number in the choice list");
                scanner.nextLine();
            }
            System.out.println();
        }
    }
}
