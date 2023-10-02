package ATM_INTERFACE.Atm;

import ATM_INTERFACE.Bank.Account;
import ATM_INTERFACE.Bank.Bank;
import ATM_INTERFACE.User.User;

import java.util.Scanner;

public class ATM {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        //init bank
        Bank theBank = new Bank("I&M Bank");

        //add user, which also creates a saving account
        User user1 = new User("Ian", "Dunacn", "1234", theBank);

        //add a checking account for our user
        Account newAccount = new Account("Checking", user1, theBank);
        user1.addAccount(newAccount);
        theBank.addAccount(newAccount);

        User currentUser;
        while (true) {

            //stay in the login prompt until successful login
            currentUser = ATM.mainMenuPrompt(theBank, scanner);

            //stay in the main menu until the user quits
            ATM.printUserMenu(currentUser, scanner);
        }
    }

    /**
     * Print atm's login menu
     * @param currentUser
     * @param scanner
     */
    private static void printUserMenu(User currentUser, Scanner scanner) {
        //print a summary of the user's account
        currentUser.printAccountSummary();

        //init
        int choice;

        //user menu
        do{
            System.out.printf("Welcome %s, what would you like to do?", currentUser.getFirstName());
            System.out.println("""
                    1. Show transaction history
                    2. Withdraw
                    3. Deposit
                    4. Transfer
                    5. Quit""");
            System.out.print("\nEnter choice: ");
            choice = scanner.nextInt();

            if (choice < 1 || choice > 5){
                System.out.println("Invalid choice. Please choose(1-5)");
            }
        }while (choice < 1 || choice > 5);

        //process the choice
        switch (choice){
            case 1 -> ATM.showTransactionHistory(currentUser, scanner);
            case 2 -> ATM.withdrawFunds(currentUser, scanner);
            case 3 -> ATM.depositFunds(currentUser, scanner);
            case 4 -> ATM.transferFunds(currentUser, scanner);
            default -> System.out.println("Invalid");
        }

        //redisplay the menu unless the user wants to quit
        if (choice != 5) printUserMenu(currentUser, scanner);

    }

    public static void depositFunds(User user, Scanner scanner){
        int toAccount;
        double amount;
        double accountBalance;
        String memo;

        //get the account to transfer from
        do{
            System.out.printf("Enter the number (1 - %d) of the account\n" +
                    "to transfer from: ");
            toAccount = scanner.nextInt() - 1;
            if(toAccount < 0 || toAccount >= user.numAccounts()){
                System.out.println("Invalid amount please try again");
            }
        }while(toAccount < 0 || toAccount >= user.numAccounts());

        accountBalance = user.getAccountBalance(toAccount);

        //get amount to transfer
        do{
            System.out.printf("Enter tha amount of money to transfer (max $%.02f): $", accountBalance);
            amount = scanner.nextDouble();
            if (amount < 0){
                System.out.println("Amount must be greater than 0");
            } else if (amount > accountBalance) {
                System.out.println("Amount must not be greater than account balance: " + accountBalance);
            }
        }while(amount < 0 || amount > accountBalance);

        scanner.nextLine();
        System.out.println("Enter a memo: ");
        memo = scanner.nextLine();

        //do the withdrawal
        user.addAccountTransaction(toAccount, amount, memo);
    }
    private static void withdrawFunds(User user, Scanner scanner) {

        int fromAccount;
        double amount;
        double accountBalance;
        String memo;

        //get the account to transfer from
        do{
            System.out.printf("Enter the number (1 - %d) of the account\n" +
                    "to transfer from: ");
            fromAccount = scanner.nextInt() - 1;
            if(fromAccount < 0 || fromAccount >= user.numAccounts()){
                System.out.println("Invalid amount please try again");
            }
        }while(fromAccount < 0 || fromAccount >= user.numAccounts());

        accountBalance = user.getAccountBalance(fromAccount);

        //get amount to transfer
        do{
            System.out.printf("Enter tha amount of money to transfer (max $%.02f): $", accountBalance);
            amount = scanner.nextDouble();
            if (amount < 0){
                System.out.println("Mount must be greater than 0");
            } else if (amount > accountBalance) {
                System.out.println("Amount must not be greater than account balance: " + accountBalance);

            }
        }while(amount < 0 || amount > accountBalance);

        scanner.nextLine();
        System.out.println("Enter a memo: ");
        memo = scanner.nextLine();

        //do the withdrawal
        user.addAccountTransaction(fromAccount, -1 * amount, memo);

    }

    private static void showTransactionHistory(User currentUser, Scanner scanner) {

        int theAccount;

        //get the account whose transaction history to look at
        do {
            System.out.printf("Enter the number (1 - %d) of the account" +
                    " whose transactions you want to see: ", currentUser.numAccounts());
            theAccount = scanner.nextInt() - 1;
            if ((theAccount < 0) || theAccount >= currentUser.numAccounts()){
                System.out.println("Invalid account please try again");
            }
        }while(((theAccount < 0) || theAccount >= currentUser.numAccounts()));
        //print the transaction history
        currentUser.printTransHistory(theAccount);

    }

    /**
     * Print the ATm's login menu
     * @param theBank       the bank object whose accounts to use
     * @param scanner       the scanner object to use for the user input
     * @return              the authenticated user object
     */
    private static User mainMenuPrompt(Bank theBank, Scanner scanner) {

        //inits
        String userId;
        String pin;
        User authUser;

        //prompt the user or user ID/Pin combo until one is reached
        do {

            System.out.printf("\n\nWelcome to %s\n\n", theBank.getName());
            System.out.print("Enter user id: ");
            userId = scanner.nextLine();
            System.out.print("Enter pin: ");
            pin = scanner.nextLine();

            //try to get the user object corresponding to the ID an pin combo
            authUser =  theBank.userLogin(userId, pin);
            if (authUser == null){
                System.out.println("Incorrect user ID/pin combination." +
                        "Please try again");
            }
        }while (authUser == null); //continue looping until successful login
        return authUser;
    }

    public static  void transferFunds(User user, Scanner scanner){

        int fromAccount;
        int toAccount;
        double amount;
        double accountBalance;

        do{
            System.out.printf("Enter the number (1 - %d) of the account\n" +
                    "to transfer from: ");
            fromAccount = scanner.nextInt() - 1;
            if(fromAccount < 0 || fromAccount >= user.numAccounts()){
                System.out.println("Invalid amount please try again");
            }
        }while(fromAccount < 0 || fromAccount >= user.numAccounts());

        accountBalance = user.getAccountBalance(fromAccount);

        //get account to transfer to
        do{
            System.out.printf("Enter the number (1 - %d) of the account\n" +
                    "to transfer from: ");
            toAccount = scanner.nextInt() - 1;
            if(toAccount < 0 || toAccount >= user.numAccounts()){
                System.out.println("Invalid amount please try again");
            }
        }while(toAccount < 0 || toAccount >= user.numAccounts());

        //get amount to transfer
        do {
            System.out.printf("Enter tha amount of money to transfer (max $%.02f): $", accountBalance);
            amount = scanner.nextDouble();
            if (amount < 0){
                System.out.println("Mount must be greater than 0");
            } else if (amount > accountBalance) {
                System.out.println("Amount must not be greater than account balance: " + accountBalance);

            }
        }while(amount < 0 || amount > accountBalance);

        //finally do the transfer
        user.addAccountTransaction(fromAccount, -1*amount ,
                        String.format("Transfer to account %s",
                        user.getAccountUuid(toAccount)));
        user.addAccountTransaction(toAccount, amount,
                        String.format("transfer to account %s",
                        user.getAccountUuid(fromAccount)));
    }


}
