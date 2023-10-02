package ATM_INTERFACE.Bank;

import ATM_INTERFACE.Transactions.Transaction;
import ATM_INTERFACE.User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {

    private String name;
    private List<User> user;
    private List<Account> accounts;

    /**
     * Create a new bank object with empty lists of users and accounts
     * @param name      name of the bank
     */
    public Bank(String name) {
        this.name = name;
        this.user = new ArrayList<>();
        this.accounts = new ArrayList<>();
    }

    /**
     * Generate a new universally unique id for the user
     * @return the uuid
     */
    public String getNewUserUUID() {
        //inits
        StringBuilder uuid;
        Random random = new Random();
        int length = 6;
        boolean nonUnique;

        //continue looping until we get a unique id
        do {

            //generate the number
            uuid = new StringBuilder();
            for (int c = 0; c < length; c++) {
                uuid.append(((Integer) random.nextInt(10)).toString());
            }

            //ascertain that the uuid is unique
            nonUnique = false;
            for (User u : this.user){
                if (uuid.compareTo(u.getUuid()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        }while(nonUnique);

        return uuid.toString();
    }

    /**
     *
     * @return
     */
    public String getNewAccountUUID() {

        //inits
        StringBuilder uuid;
        Random random = new Random();
        int length = 10;
        boolean nonUnique;

        //continue looping until we get a unique id
        do {

            //generate the number
            uuid = new StringBuilder();
            for (int c = 0; c < length; c++) {
                uuid.append(((Integer) random.nextInt(10)).toString());
            }

            //ascertain that the uuid is unique
            nonUnique = false;
            for (Account a : this.accounts){
                if (uuid.compareTo(a.getUuid()) == 0){
                    nonUnique = true;
                    break;
                }
            }
        }while(nonUnique);

        return uuid.toString();
    }

    /**
     * Add account for the user
     * @param account account to be added
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * Create a new user for the bank
     * @param firstName user's first name
     * @param lastName user's last name
     * @param pin      user's pin
     * @return          new user object
     */
    public User addUser(String firstName, String lastName, String pin){
        //create a new user object and add it to our list
        User newuser = new User(firstName, lastName, pin, this);
        this.user.add(newuser);

        //create a savings account for the user
        //accounts list
        Account newAccount = new Account("Savings", newuser, this);
        newuser.addAccount(newAccount);
        this.addAccount(newAccount);

        return newuser;
    }

    /**
     * Get the user object associated with a particular userId and pin, if
     * they are valid
     * @param userId  the UUID of the user to login
     * @param pin   the pin of the user
     * @return      the user object, if the login is successful, or null if
     *              it is not
     */
    public User userLogin(String userId, String pin){

        //search through the list of users
        for(User u : this.user){

            //check user id is correct
            if (u.getUuid().compareTo(new StringBuilder(userId)) == 0 && u.validatePin(pin)){
                return u;
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public List<User> getUser() {
        return user;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
