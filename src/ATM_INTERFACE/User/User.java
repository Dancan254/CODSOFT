package ATM_INTERFACE.User;

import ATM_INTERFACE.Bank.Account;
import ATM_INTERFACE.Bank.Bank;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class User {
    //user details
    /**
     * contains user name
     */
    private String firstName;
    /**
     * contains users last name
     */
    private String lastName;
    /**
     * the user's account number
     */
    private String accountNumber;
    /**
     * the MD5 for the user's pin number.
     */
    private String uuid;
    private byte[] userPassword;
    private String email;
    /**
     * The list of the user account
     */
    private List<Account> account;

    //default constructor
    public User() {
    }

    /**
     * Create a new user
      * @param firstName the user's first name
     * @param lastName the user's last name
     * @param pin the user's account pin
     * @param theBank the bank object that the user is a customer of
     */
    public User(String firstName, String lastName, String pin, Bank theBank) {
        //set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        //store user's pin MD5, rather than the original value or security reasons
        //use the message digest class
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.userPassword = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("error, caught NOSuchAlgorithmException");
            throw new RuntimeException(e);
        }

        //get a Unique universal id
        this.uuid = theBank.getNewUserUUID();

        //create empty list of accounts
        this.account = new ArrayList<>();
        
        //print log message
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid);
    }

    /**
     * Add an account for the user
     * @param account account to add
     */
    public void addAccount(Account account) {
        this.account.add(account);
    }
    //respective getters and setters


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public StringBuilder getUuid() {
        return new StringBuilder(uuid);
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public byte[] getUserPassword() {
       return this.userPassword;
    }

    public void setUserPassword(byte[] userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Account> getAccount() {
        return account;
    }

    public void setAccount(List<Account> account) {
        this.account = account;
    }

    public boolean validatePin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(pin.getBytes()), this.userPassword);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("error, caught NOSuchAlgorithmException");
            throw new RuntimeException(e);
        }
    }

    public void printAccountSummary(){

        System.out.printf("\n\n%s's accounts summary", this.firstName);

        for (int i = 0; i < this.account.size(); i++) {
            System.out.printf("%d) %s\n", i + 1, this.account.get(i).getSummaryLine());
        }
        System.out.println();
    }

    public int numAccounts() {
        return this.account.size();
    }

    public void printTransHistory(int accountIndex) {
        this.account.get(accountIndex).printTransHistory();
    }

    public double getAccountBalance(int fromAccount) {
        return this.account.get(fromAccount).getBalance();
    }

    public StringBuilder getAccountUuid(int toAccount) {
        return this.account.get(toAccount).getUuid();
    }

    public void addAccountTransaction(int toAccountIndex, double amount, String memo) {
        this.account.get(toAccountIndex).addTransaction(amount, memo);
    }
}

