package ATM_INTERFACE.Bank;

import ATM_INTERFACE.Transactions.Transaction;
import ATM_INTERFACE.User.User;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private String name;
    private double balance;
    private String uuid;
    private User holder;
    private List<Transaction> transactionList;


    public Account(String name, User holder, Bank theBank) {
        //set account name and holder
        this.name = name;
        this.holder = holder;

        //get new account uuid
        this.uuid = theBank.getNewAccountUUID();

        //initialize transactions
        this.transactionList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    public StringBuilder getUuid() {
        return new StringBuilder(uuid);
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getHolder() {
        return holder;
    }

    public void setHolder(User holder) {
        this.holder = holder;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    /**
     * Get the summary line for the account
     * @return  the string value
     */
    public String getSummaryLine(){

        //get the account balance
        double balance = this.getBalance();

        //format the summary line depending on whether the balance is negative
        if (balance >= 0){
            return String.format("%s : $%.02f : %s", this.uuid, balance, this.name);
        }else {
            return String.format("%s : $(%.02f) : %s", this.uuid, balance, this.name);
        }
    }

    public double getBalance(){
        double balance = 0;

        for (Transaction t : this.transactionList){

            balance += t.getAmount();
        }
        return balance;
    }

    public void printTransHistory() {
        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for (int i = this.transactionList.size() - 1; i > 0; i--) {
            System.out.printf(this.transactionList.get(i).getSummaryLine());
        }
        System.out.println();
    }

    public void addTransaction(double amount, String memo) {

        //create new transaction object and add to the list
        Transaction newTrans = new Transaction(amount, memo, this);
        this.transactionList.add(newTrans);
    }
}
